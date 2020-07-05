package com.learning.eLearning.controllers;

import com.learning.eLearning.models.ScheduleVO;
import com.learning.eLearning.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/api/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

	@GetMapping(value = "/all")
	public List<ScheduleVO> getAllSchedules() {
		return scheduleService.getAllSchedule();
	}

	@GetMapping(value = "/forCourseId/{courseId}")
	public List<ScheduleVO> getScheduleForCourseId(@PathVariable Long courseId) {
		return scheduleService.getScheduleForCourseId(courseId);
	}

	@PostMapping(value = "/new")
	public String createSchedule(@RequestBody ScheduleVO scheduleVO) {
		scheduleService.createSchedule(scheduleVO);
		return "created";
	}


}
