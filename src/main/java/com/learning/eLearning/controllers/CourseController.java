package com.learning.eLearning.controllers;

import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path="/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(path = "/new")
    public String createCourse(@RequestBody CourseVO courseVO) {
        courseService.createCourse(courseVO);
        return "created";
    }

    @GetMapping(path = "/all")
    public List<CourseVO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping(path="/findById/{id}")
    public CourseVO getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

}
