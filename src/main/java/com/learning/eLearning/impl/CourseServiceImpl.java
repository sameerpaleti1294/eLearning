package com.learning.eLearning.impl;

import com.learning.eLearning.entities.Course;
import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.repositories.CourseRepository;
import com.learning.eLearning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public void createCourse(CourseVO courseVO) {
        Course course = convertCourseVOToCourse(courseVO);

        courseRepository.save(course);
    }

    @Override
    public List<CourseVO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseVO> courseVOS = new ArrayList<>();
        for(Course course: courses) {
            CourseVO courseVO = convertCourseToCourseVO(course);
            courseVOS.add(courseVO);
        }
        return courseVOS;
    }

    @Override
    public CourseVO getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        CourseVO courseVO = null;
        if(course.isPresent()){
             courseVO = convertCourseToCourseVO(course.get());
        }
        return courseVO;
    }

    private CourseVO convertCourseToCourseVO(Course course) {
        CourseVO courseVO = new CourseVO();
        courseVO.setId(course.getId());
        courseVO.setName(course.getName());

        return courseVO;
    }

    private Course convertCourseVOToCourse(CourseVO courseVO) {
        Course course = new Course();
        course.setId(courseVO.getId());
        course.setName(courseVO.getName());

        return course;
    }
}
