package com.learning.eLearning.services;

import com.learning.eLearning.models.ScheduleVO;

import java.util.List;

public interface ScheduleService {
	List<ScheduleVO> getScheduleForCourseId(Long courseId);

	void createSchedule(ScheduleVO scheduleVO);

	List<ScheduleVO> getAllSchedule();
}
