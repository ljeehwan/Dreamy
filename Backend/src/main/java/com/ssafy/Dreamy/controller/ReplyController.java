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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.ReplyDto;
import com.ssafy.Dreamy.model.service.ReplyService;

@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://i4a306.p.ssafy.io" })
@RestController
@RequestMapping("/reply")
public class ReplyController {

	public static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ReplyService replyService;

	// 댓글 작성
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insertReply(@RequestBody ReplyDto replyDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int ret = replyService.insert(replyDto);
			if (ret > 0) {	// 등록 성공
				resultMap.put("message", SUCCESS);
				status = HttpStatus.CREATED;
			} else {		// 등록 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
			}
		} catch (Exception e) {
			logger.error("댓글 작성 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 댓글 목록
	@GetMapping("/list/{pid}")
	public ResponseEntity<Map<String, Object>> getList(@PathVariable("pid") int pid, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<ReplyDto> list = replyService.getList(pid);
			if (list.size() > 0) {	// 리스트가 있을 때
				resultMap.put("list", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {				// 리스트가 없을 때
				resultMap.put("list", null);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			logger.error("댓글 목록 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 댓글 삭제
	@DeleteMapping("/delete/{rid}")
	public ResponseEntity<Map<String, Object>> deleteReply(@PathVariable("rid") int rid, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int ret = replyService.delete(rid);
			if (ret > 0) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
			}
		} catch (Exception e) {
			logger.error("댓글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
