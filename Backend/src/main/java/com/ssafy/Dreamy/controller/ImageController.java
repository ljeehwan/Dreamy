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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.Dreamy.model.service.ImageServiceImpl;
import com.ssafy.Dreamy.model.service.UserServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
//@RequestMapping("/board")
public class ImageController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String CloudFrontDomain = "d2dmrocw1z3urn.cloudfront.net";
	
	@Autowired
	private ImageServiceImpl imageService;

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/board/imageupload")
	public ResponseEntity<Map<String, Object>> boardImageUpload(@RequestBody MultipartFile files) throws IOException {
		System.out.println(files);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String typeString = "board";
			String imgPath = "https://" + CloudFrontDomain + "/"
					+ imageService.upload(files, typeString);
			
			System.out.println(imgPath);
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

	@PostMapping("/user/imageupload/{uid}")
	public ResponseEntity<Map<String, Object>> profileImageUpload(@PathVariable("uid") int uid, @RequestBody MultipartFile files) throws IOException { 
		System.out.println(files); 
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			String typeString = "profile";
			String imgPath = "https://" + CloudFrontDomain + "/" + imageService.upload(files, typeString);

			System.out.println(imgPath);
			
			if(userService.profileUpload(uid, imgPath) < 1) {
				resultMap.put("message", FAIL);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			
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
}
