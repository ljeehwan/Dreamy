package com.ssafy.Dreamy.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;
	
}
