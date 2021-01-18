package com.ssafy.Dreamy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.Dreamy.model.MemberDto;
import com.ssafy.Dreamy.model.service.JwtServiceImpl;
import com.ssafy.Dreamy.model.service.MemberService;

//@CrossOrigin(origins = { "*" }, maxAge = 6000)
//@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/account")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String email, String password) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println("--로그인 함수 진입");	//
		try {
			MemberDto loginUser = memberService.login(email, password);
			System.out.println("--로그인 시도");		//
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getEmail(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				System.out.println("--토큰 생성");		//
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
				System.out.println("--로그인 정보 없음");	//
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("--로그인 실패");	//
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	//////////회원가입///////////
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> userJoin(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		String email = memberDto.getEmail();
		String nickname = memberDto.getNickname();
		String password = memberDto.getPassword();
		String phone = memberDto.getPhone();
		
		System.out.println(email);
		HttpStatus status = null;
		System.out.println("--회원가입 함수 진입");	//		
		try {
			System.out.println("--회원가입 시도");		//
			
			memberService.join(email,nickname,password,phone);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			System.out.println("--회원가입 성공");	//
		} catch (Exception e) {
			logger.error("회원가입 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("--회원가입 실패");	//
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid, HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
