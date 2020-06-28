package com.learning.eLearning.services;

import com.learning.eLearning.entities.Course;
import com.learning.eLearning.models.CourseVO;

import java.util.List;

public interface CourseService {
    void createCourse(CourseVO courseVO);

    List<CourseVO> getAllCourses();

    CourseVO getCourseById(Long id);
}
