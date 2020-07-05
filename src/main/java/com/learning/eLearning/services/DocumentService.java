package com.learning.eLearning.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

	ResponseEntity uploadToDB(MultipartFile file, Long scheduleId);

	ResponseEntity downloadFromDB(Long scheduleId);
}
