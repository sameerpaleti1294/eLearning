package com.learning.eLearning.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.eLearning.entities.Course;
import com.learning.eLearning.entities.Schedule;
import com.learning.eLearning.entities.User;
import com.learning.eLearning.models.AuthenticationVO;
import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.models.ScheduleVO;
import com.learning.eLearning.models.UserVO;
import com.learning.eLearning.repositories.CourseRepository;
import com.learning.eLearning.repositories.ScheduleRepository;
import com.learning.eLearning.repositories.UserRepository;
import com.learning.eLearning.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public void createUser(UserVO userVO) {
		User user = convertUserVOToUser(userVO);

		userRepository.save(user);
	}

	@Override
	public List<UserVO> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserVO> studentVOS = new ArrayList<>();
		for (User u : users) {
			UserVO userVO = convertUserToUserVO(u);
			studentVOS.add(userVO);
		}
		return studentVOS;
	}

	@Override
	public UserVO getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		UserVO userVO = null;
		if (user.isPresent()) {
			userVO = convertUserToUserVO(user.get());
		}
		return userVO;
	}

	@Override
	public List<CourseVO> getCoursesByUserId(Long id) {
		Optional<User> user = userRepository.findById(id);
		List<Course> courselist = new ArrayList<>();
		if (user.isPresent()) {
			courselist = user.get().getCourseList();
		}

		List<CourseVO> courseVOS = new ArrayList<>();

		for (Course c : courselist) {
			CourseVO courseVO = convertCourseToCourseVO(c);
			courseVOS.add(courseVO);
		}
		return courseVOS;
	}

	@Override
	public UserVO authenticateUser(AuthenticationVO authenticationVO) {
		User user = userRepository.findByNameAndPasswordAndRole(authenticationVO.getUserName(), authenticationVO.getPassword(), authenticationVO.getRole());
		UserVO userVO = null;
		if (user != null) {
			userVO = new UserVO();
			userVO.setName(user.getName());
			userVO.setRole(user.getRole());
			userVO.setUserId(user.getUserId());
		}

		return userVO;
	}

	@Override
	public List<ScheduleVO> getSchedulesByUserIdAndDate(Long userId, Date date) {
		Optional<User> user = userRepository.findById(userId);
		List<Course> courselist = new ArrayList<>();
		if (user.isPresent()) {
			courselist = user.get().getCourseList();
		}
		List<ScheduleVO> scheduleVOS = new ArrayList<>();
		for (Course c : courselist) {
			List<Schedule> schedules = scheduleRepository.findByCourseId(c.getId());
			for (Schedule schedule : schedules) {
				if (asLocalDate(schedule.getStartTime()).equals(asLocalDate(date))) {
					ScheduleVO scheduleVO = new ScheduleVO();
					scheduleVO.setScheduleId(schedule.getScheduleId());
					scheduleVO.setStartTime(schedule.getStartTime());
					scheduleVO.setEndTime(schedule.getEndTime());
					scheduleVO.setCourseId(schedule.getCourse().getId());
					scheduleVO.setDescription(schedule.getDescription());
					scheduleVO.setCourseName(schedule.getCourse().getName());
					scheduleVOS.add(scheduleVO);
				}
			}

		}
		return scheduleVOS;
	}

	private User convertUserVOToUser(UserVO userVO) {
		User user = new User();
		user.setUserId(userVO.getUserId());
		user.setName(userVO.getName());
		user.setPassword(userVO.getPassword());
		user.setRole(userVO.getRole());

		return user;
	}

	private UserVO convertUserToUserVO(User user) {

		UserVO userVO = new UserVO();
		userVO.setUserId(user.getUserId());
		userVO.setName(user.getName());
		userVO.setRole(user.getRole());

		return userVO;

	}

	private CourseVO convertCourseToCourseVO(Course course) {
		CourseVO courseVO = new CourseVO();
		courseVO.setId(course.getId());
		courseVO.setName(course.getName());

		return courseVO;
	}

}
