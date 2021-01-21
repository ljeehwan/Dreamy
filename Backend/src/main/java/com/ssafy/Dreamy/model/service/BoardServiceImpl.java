package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.BoardDto;
import com.ssafy.Dreamy.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Object create(BoardDto boardDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", boardDto.getUid());
		map.put("boardType", boardDto.getBoardType());
		map.put("title", boardDto.getTitle());
		map.put("content", boardDto.getContent());
		map.put("startDate", boardDto.getStartDate());
		map.put("endDate", boardDto.getEndDate());
		map.put("category", boardDto.getCategory());
		map.put("imageUrl", boardDto.getImageUrl());
		map.put("dateType", boardDto.getDateType());
		map.put("totalDate", boardDto.getTotalDate());
		map.put("running", boardDto.getRunning());
		
		return sqlSession.getMapper(BoardMapper.class).create(map);
	}
	
}

