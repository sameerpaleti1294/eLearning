package com.learning.eLearning.controllers;

import com.learning.eLearning.services.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/files")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@PostMapping("/upload")
	public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file, @RequestParam("scheduleId") Long scheduleId) {
		return documentService.uploadToDB(file, scheduleId);
	}

	@GetMapping("/download/{scheduleId}")
	public ResponseEntity downloadFromDB(@PathVariable Long scheduleId) {
		return documentService.downloadFromDB(scheduleId);
	}
}
