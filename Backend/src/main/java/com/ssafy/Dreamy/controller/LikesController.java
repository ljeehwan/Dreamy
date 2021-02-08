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

	public static final Logger logger = LoggerFactory.getLogger(LikesController.class);
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
		System.out.println(user_id);
		System.out.println(post_id);

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

	////////// 좋아요 취소 ///////////
	@DeleteMapping("/unlikes")
	public ResponseEntity<Map<String, Object>> unlikes(@RequestBody LikesDto likesdto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int user_id = likesdto.getUserid();
		int post_id = likesdto.getPostid();
		
		System.out.println(user_id);
		System.out.println(post_id);

		try {
			System.out.println("좋아요 취소함수 시작");
			likesservice.unLike(user_id, post_id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("좋아요 취소 실패"); //
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// 좋아요 수 카운트 ///////////
	@GetMapping("/countlikes/{pid}")
	public ResponseEntity<Map<String, Object>> likescount(@PathVariable("pid") int pid, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(pid);
		logger.info("좋아요 수 카운트 시작");
		try {
			int count = likesservice.countLikes(pid);
			resultMap.put("count", count);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("좋아요 수 카운트 실패");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// 좋아요 상태 체크 ///////////
	@GetMapping("/checklikes")
	public ResponseEntity<Map<String, Object>> likescheck(@RequestBody LikesDto likesdto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int user_id = likesdto.getUserid();
		int post_id = likesdto.getPostid();

		try {
			System.out.println("좋아요 상태 체크 함수 시작");
			if (likesservice.checkLikes(user_id, post_id)) {
				System.out.println("좋아요 누른적이 있다");
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				System.out.println("좋아요 누른적이 없다");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("좋아요 상태 체크 실패");
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
