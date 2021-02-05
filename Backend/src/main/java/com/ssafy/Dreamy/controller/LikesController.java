package com.ssafy.Dreamy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.LikesDto;
import com.ssafy.Dreamy.model.service.LikesService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/likes")
public class LikesController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	LikesService likesservice;

	////////// 좋아요 추가 ///////////
	@PostMapping("/addlikes")
	public ResponseEntity<Map<String, Object>> addlikes(@RequestBody LikesDto likesdto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int user_id = likesdto.getUserid();
		int post_id = likesdto.getPostid();

		try {
			System.out.println("좋아요 추가함수 시작");
			likesservice.addLike(user_id, post_id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("좋아요 추가 실패"); //
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	

}
