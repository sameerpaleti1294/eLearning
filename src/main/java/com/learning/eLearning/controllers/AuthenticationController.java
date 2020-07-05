package com.learning.eLearning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.eLearning.models.AuthenticationVO;
import com.learning.eLearning.models.UserVO;
import com.learning.eLearning.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/authentication")
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@PostMapping
	public UserVO authenticateUser(@RequestBody AuthenticationVO authenticationVO) {
		return userService.authenticateUser(authenticationVO);
	}
}
