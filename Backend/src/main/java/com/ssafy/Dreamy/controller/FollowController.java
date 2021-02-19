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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.FollowDto;
import com.ssafy.Dreamy.model.UserDto;
import com.ssafy.Dreamy.model.service.FollowService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://i4a306.p.ssafy.io" })
@RestController
@RequestMapping("/follow")
public class FollowController {

	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	FollowService followservice;

	////////// 팔로우 요청 ///////////
	@PostMapping("/requestfollow")
	public ResponseEntity<Map<String, Object>> follow(@RequestBody FollowDto followDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		int user_id = followDto.getFollowingUid();
		int target_id = followDto.getFollowUid();

		try {
			followservice.followService(user_id, target_id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	

	////////// 팔로우 취소 ///////////
	@DeleteMapping("/unfollow/{uid1}/{uid2}")
	public ResponseEntity<Map<String, Object>> unfollow(@PathVariable("uid1") int userid,
			@PathVariable("uid2") int targetid, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			followservice.unfollowService(userid, targetid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// 팔로우 관계 검증 ///////////
	@GetMapping("/checkfollow/{uid1}/{uid2}")
	public ResponseEntity<Map<String, Object>> relationcheck(@PathVariable("uid1") int userid,
			@PathVariable("uid2") int targetid, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			if (followservice.followcheck(userid, targetid)) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// following 목록 ///////////
	@GetMapping("/listfollowing/{uid}")
	public ResponseEntity<Map<String, Object>> followinglist(@PathVariable("uid") int uid, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("팔로잉 목록 출력");
		try {
			List<UserDto> list = followservice.listfollowing(uid);
			if (list.size() != 0) {
				resultMap.put("list", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("list", null);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.NO_CONTENT;
			}

		} catch (Exception e) {
			logger.error("팔로잉 리스트 출력 실패");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// follower 목록 ///////////
	@GetMapping("/listfollower/{uid}")
	public ResponseEntity<Map<String, Object>> followerlist(@PathVariable("uid") int uid, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("팔로워 목록 출력");
		try {
			List<UserDto> list = followservice.listfollower(uid);
			if (list.size() != 0) {
				resultMap.put("list", list);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("list", null);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			logger.error("팔로워 리스트 출력 실패");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// following 수 카운트 ///////////
	@GetMapping("/countfollowing/{uid}")
	public ResponseEntity<Map<String, Object>> followingcount(@PathVariable("uid") int uid, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("팔로잉 수 카운트 시작");
		try {
			int count = followservice.countfollowing(uid);
			resultMap.put("count", count);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("팔로잉 수 카운트 실패");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	////////// follower 수 카운트 ///////////
	@GetMapping("/countfollower/{uid}")
	public ResponseEntity<Map<String, Object>> followercount(@PathVariable("uid") int uid, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("팔로워 수 카운트");
		try {
			int count = followservice.countfollower(uid);
			resultMap.put("count", count);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("팔로우 수 카운트 실패");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
