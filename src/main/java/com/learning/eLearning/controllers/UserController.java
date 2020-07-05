package com.learning.eLearning.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.models.ScheduleVO;
import com.learning.eLearning.models.UserVO;
import com.learning.eLearning.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(path = "/new")
	public String createStudent(@RequestBody UserVO userVO) {
		userService.createUser(userVO);
		return "created";
	}

	@GetMapping(path = "/all")
	public List<UserVO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(path="/findById/{userId}")
	public UserVO getUserById(@PathVariable Long userId){
		return userService.getUserById(userId);
	}

	@GetMapping(path="/getCourses/{userId}")
	public List<CourseVO> getCoursesByUserId(@PathVariable Long userId){
		return userService.getCoursesByUserId(userId);
	}

	@GetMapping(path="/getSchedules/{userId}/{date}")
	public List<ScheduleVO> getSchedulesByUserIdAndDate(@PathVariable Long userId,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
		return userService.getSchedulesByUserIdAndDate(userId, date);
	}
}
