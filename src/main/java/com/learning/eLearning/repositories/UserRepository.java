package com.learning.eLearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.eLearning.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByNameAndPasswordAndRole(String userName, String password, String role);
}
