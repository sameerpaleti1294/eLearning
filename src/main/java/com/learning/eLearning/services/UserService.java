package com.learning.eLearning.services;

import java.util.Date;
import java.util.List;

import com.learning.eLearning.models.AuthenticationVO;
import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.models.ScheduleVO;
import com.learning.eLearning.models.UserVO;

public interface UserService {

	void createUser(UserVO userVO);

	List<UserVO> getAllUsers();

	UserVO getUserById(Long id);

	List<CourseVO> getCoursesByUserId(Long id);

	UserVO authenticateUser(AuthenticationVO authenticationVO);

	List<ScheduleVO> getSchedulesByUserIdAndDate(Long userId, Date date);
}
