package com.ssafy.Dreamy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.BoardDto;
import com.ssafy.Dreamy.model.service.MylistService;

@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://i4a306.p.ssafy.io" })
@RestController
@RequestMapping("/mylist")
public class MylistController {

	public static final Logger logger = LoggerFactory.getLogger(MylistController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MylistService mylistService;
	
	//////////마이리스트 내가 작성한 게시물 조회///////////
	@GetMapping("/getMyBoardList/{uid}/{limit}")
	public ResponseEntity<Map<String, Object>> getMyBoardList(@PathVariable("uid") int uid, @PathVariable("limit") int limit, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<BoardDto> list = mylistService.getMyBoardList(uid, limit);
			
			int totalSize = mylistService.getMyBoardTotal(uid);
			
			resultMap.put("boardlist", list);
			resultMap.put("totalSize", totalSize);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			
		}catch(Exception e) {
			logger.error("목록 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
				
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	//////////마이리스트 내가 참여한 게시물 조회///////////
	@GetMapping("/getMyParticipateList/{uid}/{limit}")
	public ResponseEntity<Map<String, Object>> getMyParticipateList(@PathVariable("uid") int uid, @PathVariable("limit") int limit, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<BoardDto> list = mylistService.getMyParticipateList(uid, limit);
			
			int totalSize = mylistService.getMyParticipateTotal(uid);
			
			resultMap.put("boardlist", list);
			resultMap.put("totalSize", totalSize);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			
		}catch(Exception e) {
			logger.error("목록 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;	
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//////////마이리스트 내가 성공한 게시물 조회///////////
	@GetMapping("/getMySuccessList/{uid}/{limit}")
	public ResponseEntity<Map<String, Object>> getMySuccessList (@PathVariable("uid") int uid, @PathVariable("limit") int limit, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<BoardDto> list = mylistService.getMySuccessList(uid, limit);
			
			int totalSize = mylistService.getMySuccessTotal(uid);
			
			resultMap.put("boardlist", list);
			resultMap.put("totalSize", totalSize);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;

		}catch(Exception e) {
			logger.error("목록 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
