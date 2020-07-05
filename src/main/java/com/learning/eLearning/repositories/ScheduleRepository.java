package com.learning.eLearning.repositories;

import com.learning.eLearning.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByCourseId(Long courseId);

	List<Schedule> findByCourseIdAndStartTime(Long courseId, Date startTime);

}
