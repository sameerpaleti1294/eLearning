package com.learning.eLearning.models;

import java.util.List;


public class StudentVO {
	private Long studentId;
	private List<CourseVO> courseList;
	private String name;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public List<CourseVO> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseVO> courseList) {
		this.courseList = courseList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
