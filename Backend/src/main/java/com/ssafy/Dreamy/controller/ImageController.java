package com.ssafy.Dreamy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.Dreamy.model.service.ImageServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/board")
public class ImageController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String CloudFrontDomain = "d2dmrocw1z3urn.cloudfront.net";
	
	@Autowired
	private ImageServiceImpl imageService;

	@PostMapping("/imageupload")
	public ResponseEntity<Map<String, Object>> boardImageUpload(MultipartFile files) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String typeString = "board";
			String imgPath = "https://" + CloudFrontDomain + "/"
					+ imageService.upload(files, typeString);
			
			resultMap.put("imgPath", imgPath);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("이미지 업로드 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/*
	 * @PostMapping("/user/imageupload")// account/imageupload?? user/profile ??
	 * public ResponseEntity<Map<String, Object>> profileImageUpload(MultipartFile
	 * files) throws IOException { System.out.println(files); // for (int i = 0; i <
	 * files.length; i++) { String imgPath =
	 * imageService.upload(galleryDto.getFilePath(), files);
	 * galleryDto.setFilePath("https://" + "d2dmrocw1z3urn.cloudfront.net" + "/" +
	 * imgPath); // } return galleryService.upload(galleryDto) > 0 ? "OK" : "FAIL";
	 * }
	 */
}
