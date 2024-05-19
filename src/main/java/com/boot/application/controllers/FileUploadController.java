package com.boot.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.application.helper.FileUploadHelper;

@RestController
@RequestMapping(value = "/api/v1")
public class FileUploadController {
	
	@Autowired
	FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println("Uploaded file: " + file.getOriginalFilename() + "/" + file.getContentType());
		try {
			// Validation
			if(file.isEmpty()) {
				return new ResponseEntity<>("Please select a file", HttpStatus.BAD_REQUEST);
			}
			if(!(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
				return new ResponseEntity<>("Only .jpeg or .png file type supported.", HttpStatus.BAD_REQUEST);
			}
			
			// Upload file
			boolean isUploaded = this.fileUploadHelper.uploadFileNio(file);
			if(!isUploaded) {
				throw new RuntimeException("File upload failed. Please try again.");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Successfully uploaded.", HttpStatus.OK);
	}
}
