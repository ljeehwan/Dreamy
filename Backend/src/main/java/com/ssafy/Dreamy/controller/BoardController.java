package com.ssafy.Dreamy.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.BoardDto;
import com.ssafy.Dreamy.model.service.BoardService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/board")	// 매핑주소 변경가능
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	//////////게시물 등록///////////
	@PostMapping("/create")	// 매핑주소 변경가능
	public ResponseEntity<Map<String, Object>> create(@RequestBody BoardDto boardDto) {
		Map<String, Object> resultMap = new HashMap<>();
		
		HttpStatus status = null;
		try {
			int ret = (int)boardService.create(boardDto);
			// if
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("게시물 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
