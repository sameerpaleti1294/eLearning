package com.learning.eLearning.impl;

import com.learning.eLearning.entities.Course;
import com.learning.eLearning.entities.Document;
import com.learning.eLearning.entities.Schedule;
import com.learning.eLearning.models.ScheduleVO;
import com.learning.eLearning.repositories.CourseRepository;
import com.learning.eLearning.repositories.DocumentRepository;
import com.learning.eLearning.repositories.ScheduleRepository;
import com.learning.eLearning.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public List<ScheduleVO> getScheduleForCourseId(Long courseId) {
		List<Schedule> schedules = scheduleRepository.findByCourseId(courseId);
		List<ScheduleVO> scheduleVOS = new ArrayList<>();
		for (Schedule schedule: schedules) {
			ScheduleVO scheduleVO = new ScheduleVO();
			scheduleVO.setScheduleId(schedule.getScheduleId());
			scheduleVO.setStartTime(schedule.getStartTime());
			scheduleVO.setEndTime(schedule.getEndTime());
			scheduleVO.setCourseId(schedule.getCourse().getId());
			scheduleVO.setDescription(schedule.getDescription());
			List<Document> documents = documentRepository.findByScheduleScheduleId(schedule.getScheduleId());
			if(documents.isEmpty()) {
				scheduleVO.setHasUploadedFile(false);
			} else {
				scheduleVO.setHasUploadedFile(true);
			}
			scheduleVOS.add(scheduleVO);
		}
		return scheduleVOS;
	}

	@Override
	public void createSchedule(ScheduleVO scheduleVO) {
		Schedule schedule = new Schedule();
		schedule.setStartTime(scheduleVO.getStartTime());
		schedule.setEndTime(scheduleVO.getEndTime());
		schedule.setDescription(scheduleVO.getDescription());
		Optional<Course> course = courseRepository.findById(scheduleVO.getCourseId());
		if(course.isPresent()) {
			schedule.setCourse(course.get());
		}
		scheduleRepository.save(schedule);
	}

	@Override
	public List<ScheduleVO> getAllSchedule() {
		List<Schedule> schedules = scheduleRepository.findAll();
		List<ScheduleVO> scheduleVOS = new ArrayList<>();
		for (Schedule schedule: schedules) {
			ScheduleVO scheduleVO = new ScheduleVO();
			scheduleVO.setScheduleId(schedule.getScheduleId());
			scheduleVO.setStartTime(schedule.getStartTime());
			scheduleVO.setEndTime(schedule.getEndTime());
			scheduleVO.setCourseId(schedule.getCourse().getId());
			scheduleVO.setDescription(schedule.getDescription());
			scheduleVOS.add(scheduleVO);
		}
		return scheduleVOS;
	}
}
