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
import org.springframework.web.bind.annotation.PutMapping;
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

	//////////게시물 수정(내용만)///////////
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> update(@RequestBody BoardDto boardDto){
		Map<String, Object> resultMap = new HashMap<>();
		
		HttpStatus status = null;
		
		try {
			System.out.println("-- 게시물 수정 시도");
			
			int pid = boardDto.getPid();
			String content = boardDto.getContent();
			
			boardService.update(pid, content);
			status = HttpStatus.ACCEPTED;
			System.out.println("-- 게시물 수정 성공");
		}catch(Exception e) {
			logger.error("게시물 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("-- 게시물 수정 실패");
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
}
