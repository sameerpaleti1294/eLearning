package com.learning.eLearning.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.eLearning.entities.Course;
import com.learning.eLearning.entities.Student;
import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.models.StudentVO;
import com.learning.eLearning.repositories.CourseRepository;
import com.learning.eLearning.repositories.StudentRepository;
import com.learning.eLearning.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public void createStudent(StudentVO studentVO) {
		Student student = convertStudentVOToStudent(studentVO);

		studentRepository.save(student);
	}

	@Override
	public List<StudentVO> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		List<StudentVO> studentVOS = new ArrayList<>();
		for(Student s: students) {
			StudentVO studentVO = convertStudentToStudentVO(s);
			studentVOS.add(studentVO);
		}
		return studentVOS;
	}

	@Override
	public StudentVO getStudentById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		StudentVO studentVO = null;
		if(student.isPresent()) {
			studentVO = convertStudentToStudentVO(student.get());
		}
		return studentVO;
	}

	@Override
	public List<CourseVO> getCoursesByStudentId(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		List<Course> courselist = new ArrayList<>();
		if(student.isPresent()) {
			courselist = student.get().getCourseList();
		}

		List<CourseVO> courseVOS = new ArrayList<>();

		for(Course c : courselist){
			CourseVO courseVO = convertCourseToCourseVO(c);
			courseVOS.add(courseVO);
		}
		return courseVOS;
	}

	private Student convertStudentVOToStudent(StudentVO studentVO) {
		Student student = new Student();
		student.setStudentId(studentVO.getStudentId());
		student.setName(studentVO.getName());
		List<CourseVO> courseVOS = studentVO.getCourseList();
		List<Course> courses = new ArrayList<>();

		for(CourseVO c : courseVOS){
			Optional<Course> course = courseRepository.findById(c.getId());
			if(course.isPresent()) {
				courses.add(course.get());
			}
		}

		student.setCourseList(courses);

		return student;
	}

	private StudentVO convertStudentToStudentVO(Student student) {

		StudentVO studentVO = new StudentVO();
		studentVO.setStudentId(student.getStudentId());
		studentVO.setName(student.getName());
		List<Course> courses = student.getCourseList();
		List<CourseVO> courseVOS = new ArrayList<>();

		for(Course c : courses){
			CourseVO courseVO = convertCourseToCourseVO(c);
			courseVOS.add(courseVO);
		}

		studentVO.setCourseList(courseVOS);

		return studentVO;

	}

	private CourseVO convertCourseToCourseVO(Course course) {
		CourseVO courseVO = new CourseVO();
		courseVO.setId(course.getId());
		courseVO.setName(course.getName());

		return courseVO;
	}

}
