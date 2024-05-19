package com.boot.application.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
//	public static final String UPLOAD_DIR = "D:\\WS\\SPRING-BOOT\\7\\application\\src\\main\\resources\\static\\uploads";
	public final String UPLOAD_DIR = new ClassPathResource("static/uploads").getFile().getAbsolutePath();

	public FileUploadHelper() throws IOException{}

	public boolean uploadFileNio(MultipartFile file) {
		boolean isUploaded = false;
		try {
			String outputPath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
			Files.copy(file.getInputStream(), Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
			isUploaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUploaded;
	}
	
	public boolean uploadFileUsingInputOutputStream(MultipartFile file) {
		boolean isUploaded = false;
		try {
			FileInputStream fis = (FileInputStream) file.getInputStream();
			byte[] bytes = new byte[fis.available()];
			
			fis.read(bytes);
			
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "\\" + file.getOriginalFilename());
			fos.write(bytes);
			fos.flush();
			fos.close();
			isUploaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUploaded;
	}
}
