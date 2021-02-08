package com.ssafy.Dreamy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.ParticipateDto;
import com.ssafy.Dreamy.model.service.ParticipateService;

@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://i4a306.p.ssafy.io" })
@RestController
@RequestMapping("/participate")
public class ParticipateController {
	public static final Logger logger = LoggerFactory.getLogger(ParticipateController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ParticipateService participateService;
	
	//////////게시물 참가 ///////////
	@PostMapping("/addParticipant")
	public ResponseEntity<Map<String, Object>> addParticipant(@RequestBody ParticipateDto participatedto) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		int uid = participatedto.getUid();
		int pid = participatedto.getPid();
		int successDate = 0;
		
		try {
			if(participateService.addParticipant(uid, pid, successDate) < 1) { // 게시물 참가 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
			}
			else { // 게시물 참가 성공
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("게시물 참가 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//////////마이리스트 내가 작성한 게시물 조회///////////
	@GetMapping("/getMyListMine")
	public ResponseEntity<Map<String, Object>> getMyListMine(@RequestParam("uid")int uid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//////////마이리스트 내가 참여한 게시물 조회///////////
}
