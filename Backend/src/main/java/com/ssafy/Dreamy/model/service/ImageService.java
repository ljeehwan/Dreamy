package com.ssafy.Dreamy.model.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	public void setS3Client() throws Exception;
	public String upload(MultipartFile file, String typeString) throws Exception;
	
	//public String upload(MultipartFile file) throws Exception;
}
