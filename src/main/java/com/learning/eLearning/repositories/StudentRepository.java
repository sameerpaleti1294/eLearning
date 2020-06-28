package com.learning.eLearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.eLearning.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
