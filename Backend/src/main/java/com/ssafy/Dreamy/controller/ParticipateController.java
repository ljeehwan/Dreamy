package com.ssafy.Dreamy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class ParticipateController {
	public static final Logger logger = LoggerFactory.getLogger(ParticipateController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
}
