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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		System.out.println("-- 게시물 참가");
		System.out.println("-- uid : " + uid);
		System.out.println("-- pid : " + pid);
		try {
			if(participateService.addParticipant(uid, pid, successDate) < 1) { // 게시물 참가 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
				
				System.out.println("-- 게시물 참가 실패");
			}
			else { // 게시물 참가 성공
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
				System.out.println("-- 게시물 참가 성공");
			}
		} catch (Exception e) {
			logger.error("게시물 참가 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//////////게시물 참가 취소 ///////////
	@DeleteMapping("/deleteParticipant/{uid}/{pid}")
	public ResponseEntity<Map<String, Object>> deleteParticipant(@PathVariable("uid") int uid,@PathVariable("pid") int pid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		System.out.println("-- 참가 취소");
		System.out.println("-- uid : " + uid);
		System.out.println("-- pid : " + pid);
		
		try {
			if(participateService.deleteParticipant(uid, pid) < 1) { // 참가 취소 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
				
				System.out.println("-- 참가 취소 실패");
			}
			else { // 참가 취소 성공
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
				System.out.println("-- 참가 취소 성공");
			}
		} catch (Exception e) {
			logger.error("게시물 참가 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
