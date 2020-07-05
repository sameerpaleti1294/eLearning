package com.learning.eLearning.models;

import java.util.Date;

public class ScheduleVO {
	private Long scheduleId;

	private Date startTime;

	private Date endTime;

	private String description;

	private Long courseId;

	private String courseName;

	private boolean hasUploadedFile;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public boolean isHasUploadedFile() {
		return hasUploadedFile;
	}

	public void setHasUploadedFile(boolean hasUploadedFile) {
		this.hasUploadedFile = hasUploadedFile;
	}
}
