package com.course.rest_api_basics.uploads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadManager {
	
	// private String path = "C:\\Users\\rohan_wnlz3pl\\Desktop\\Goal_2021\\Springboot\\rest_api_basics\\src\\main\\resources\\static\\image";
	private String path = new ClassPathResource("static/image").getFile().getAbsolutePath();
	
	// Constructor
	public FileUploadManager() throws IOException { }
	
	
	public boolean uploadFile(MultipartFile file) {
		
		boolean f = false;
		
		try {
			
			Files.copy(file.getInputStream(), Paths.get(path+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			f = true;
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return f;
		
	}
	
}
