package com.learning.eLearning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.models.StudentVO;
import com.learning.eLearning.services.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/api/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping(path = "/new")
	public String createStudent(@RequestBody StudentVO studentVO) {
		studentService.createStudent(studentVO);
		return "created";
	}

	@GetMapping(path = "/all")
	public List<StudentVO> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping(path="/findByid/{id}")
	public StudentVO getStudentById(@PathVariable Long id){
		return studentService.getStudentById(id);
	}

	@GetMapping(path="/getCourses/{id}")
	public List<CourseVO> getCoursesByStudentId(@PathVariable Long id){
		return studentService.getCoursesByStudentId(id);
	}
}
