package com.ssafy.Dreamy.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// 게시물 등록
	@PostMapping("/insert")	// 매핑주소 변경가능
	public ResponseEntity<Map<String, Object>> create(@RequestBody BoardDto boardDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		int boardType = boardDto.getBoardType();
		int ret = 0;
		
		try {
			if (boardType == 1)			// 버킷리스트
				ret = boardService.insertBucket(boardDto);
			else if (boardType == 2)	// 챌린지
				ret = boardService.insertChallenge(boardDto);
			
			// insert 성공하면 AI값을 return
			if (ret > 0) {	// 등록 성공
				resultMap.put("message", SUCCESS);
				status = HttpStatus.CREATED;
			} else {		// 등록 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
			}
		} catch (Exception e) {
			logger.error("게시물 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 게시물 전체목록(뉴스피드)
	@GetMapping("/list/{limit}")
	public ResponseEntity<Map<String, Object>> getInfo(@RequestParam("uid") int uid, @PathVariable("limit") int limit, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("전체목록");
		try {
			List<BoardDto> list = new ArrayList<>();
			int totalSize = boardService.getListTotalSize(uid);	// 전체목록 게시물 개수
			list = boardService.getList(uid, limit);
			if (totalSize > limit) {	// 리스트가 있을 때
				resultMap.put("list", list);
				resultMap.put("totalSize", totalSize);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {				// 리스트가 없을 때
				resultMap.put("list", null);
				resultMap.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	// 버킷리스트 목록
	@GetMapping("/bucketList")
	public ResponseEntity<Map<String, Object>> bucketList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("전체목록");
		try {
			List<BoardDto> list = new ArrayList<>();
			int totalSize = boardService.getBucketOrChallengeTotalSize(1);	// 버킷리스트 게시물 개수
			list = boardService.getList(uid, limit);
			if (totalSize > limit) {	// 리스트가 있을 때
				resultMap.put("list", list);
				resultMap.put("totalSize", totalSize);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {				// 리스트가 없을 때
				resultMap.put("list", null);
				resultMap.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	*/

	/*
	// 챌린지 목록
	@GetMapping("/challengeList")
	public ResponseEntity<Map<String, Object>> challengeList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	*/
	
	// 게시물 수정(내용만)
	@PutMapping("/update") 
	public ResponseEntity<Map<String, Object>> update(@RequestBody BoardDto boardDto) {
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
