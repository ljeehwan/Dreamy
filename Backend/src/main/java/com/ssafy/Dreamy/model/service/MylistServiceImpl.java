package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.BoardDto;
import com.ssafy.Dreamy.model.mapper.MylistMapper;

@Service
public class MylistServiceImpl implements MylistService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<BoardDto> getMyBoardList(int uid, int limit) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("limit", limit);
		
		
		return sqlSession.getMapper(MylistMapper.class).getMyBoardList(map);
	}

	@Override
	public List<BoardDto> getMyParticipateList(int uid, int limit) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("limit", limit);
		
		return sqlSession.getMapper(MylistMapper.class).getMyParticipateList(map);
	}

}