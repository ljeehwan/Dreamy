package com.ssafy.Dreamy.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.ParticipateDto;
import com.ssafy.Dreamy.model.UserDto;
import com.ssafy.Dreamy.model.service.ParticipateService;

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = { "http://i4a306.p.ssafy.io" })
@RestController
@RequestMapping("/participate")
public class ParticipateController {
	public static final Logger logger = LoggerFactory.getLogger(ParticipateController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ParticipateService participateService;
	
	// 게시물 참가
	@PostMapping("/addParticipant")
	public ResponseEntity<Map<String, Object>> addParticipant(@RequestBody ParticipateDto participatedto) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		int uid = participatedto.getUid();
		int pid = participatedto.getPid();
		
		try {
			if(participateService.addParticipant(uid, pid) < 1) { // 게시물 참가 실패
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
	
	// 게시물 참가 취소
	@DeleteMapping("/deleteParticipant/{uid}/{pid}")
	public ResponseEntity<Map<String, Object>> deleteParticipant(@PathVariable("uid") int uid,@PathVariable("pid") int pid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			if(participateService.deleteParticipant(uid, pid) < 1) { // 참가 취소 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.EXPECTATION_FAILED;
			}
			else { // 참가 취소 성공
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
	
	// 게시물 참가 여부 조회
	@GetMapping("/checkParticipant/{uid}/{pid}")
	public ResponseEntity<Map<String, Object>> checkParticipant(@PathVariable("uid") int uid, @PathVariable("pid") int pid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			if(participateService.checkParticipant(uid, pid) < 1) { // 참가 여부 X
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
			else { // 참가 여부 O
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		}
		catch(Exception e) {
			logger.error("참가 여부 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 참가자 리스트 조회
	@GetMapping("/getUserList/{pid}")
	public ResponseEntity<Map<String,Object>> getUserList(@PathVariable("pid") int pid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int userTotal = participateService.getListSize(pid);

			if( userTotal < 1) {
				resultMap.put("userTotal", 0);
				resultMap.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;

			}else {
				List<UserDto> list = participateService.getUserList(pid);
				
				resultMap.put("userTotal", userTotal);
				resultMap.put("userList", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		}
		catch(Exception e) {
			logger.error("참가자 리스트 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 달성 체크
	@PutMapping("/success/{uid}/{pid}")
	public ResponseEntity<Map<String, Object>> addSuccess(@PathVariable("uid") int uid, @PathVariable("pid") int pid, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			int isSuccess = participateService.bucketSuccess(uid, pid);
			if(isSuccess > 0) {	// update 성공
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {			// update 실패
				resultMap.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			logger.error("성공 체크 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
