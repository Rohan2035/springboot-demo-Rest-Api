package com.course.rest_api_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.course.rest_api_basics.uploads.FileUploadManager;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadManager fileUploadManager;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		
		try {
			
			if(file.isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is empty");
				
			} 
			
			if(!file.getContentType().equals("image/jpeg")) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("JPEG file only !!");
				
			}
			
			Boolean uploadFile = fileUploadManager.uploadFile(file);
			
			if(uploadFile) {
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		
	}
	

}
