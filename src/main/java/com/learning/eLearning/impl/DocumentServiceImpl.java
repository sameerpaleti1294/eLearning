package com.learning.eLearning.impl;

import com.learning.eLearning.entities.Document;
import com.learning.eLearning.entities.Schedule;
import com.learning.eLearning.repositories.DocumentRepository;
import com.learning.eLearning.repositories.ScheduleRepository;
import com.learning.eLearning.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public ResponseEntity uploadToDB(MultipartFile file, Long scheduleId) {

		Document doc = new Document();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		doc.setDocName(fileName);
		try {
			doc.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
		if (schedule.isPresent()) {
			doc.setSchedule(schedule.get());
		}
		documentRepository.save(doc);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/files/download/")
				.path(fileName)

				.toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}

	@Override
	public ResponseEntity downloadFromDB(Long scheduleId) {
		List<Document> documents = documentRepository.findByScheduleScheduleId(scheduleId);
		String contentType = "application/octet-stream";
		Document document = null;
		if(documents.isEmpty()) {
			return ResponseEntity.ok().body(null);
		}
		int length = documents.size();
		document = documents.get(length-1);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getDocName() + "\"")
				.body(document.getFile());
	}

}
