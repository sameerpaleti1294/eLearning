package com.learning.eLearning.services;

import java.util.List;

import com.learning.eLearning.models.CourseVO;
import com.learning.eLearning.models.StudentVO;

public interface StudentService {

	void createStudent(StudentVO studentVO);

	List<StudentVO> getAllStudents();

	StudentVO getStudentById(Long id);

	List<CourseVO> getCoursesByStudentId(Long id);
}
