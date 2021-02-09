package com.ssafy.Dreamy.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.BoardDto;
import com.ssafy.Dreamy.model.mapper.MylistMapper;

@Service
public class MylistServiceImpl implements MylistService {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<BoardDto> getMyBoardList(int uid) throws Exception {
		
		return sqlsession.getMapper(MylistMapper.class).getMyBoardList(uid);
	}

	@Override
	public List<BoardDto> getMyParticipateList(int uid) throws Exception {

		return sqlsession.getMapper(MylistMapper.class).getMyParticipateList(uid);
	}

}
