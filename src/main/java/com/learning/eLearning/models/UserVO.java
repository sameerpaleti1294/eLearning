package com.learning.eLearning.models;

import java.util.List;


public class UserVO {
	private Long userId;
//	private List<CourseVO> courseList;
	private String name;
	private String password;
	private String role;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public List<CourseVO> getCourseList() {
//		return courseList;
//	}
//
//	public void setCourseList(List<CourseVO> courseList) {
//		this.courseList = courseList;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
