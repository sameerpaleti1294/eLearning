package com.learning.eLearning.repositories;

import com.learning.eLearning.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	Document findByDocName(String fileName);

	List<Document> findByScheduleScheduleId(Long scheduleId);
}
