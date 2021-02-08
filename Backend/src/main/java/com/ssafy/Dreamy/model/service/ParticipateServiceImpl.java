package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.Dreamy.model.mapper.ParticipateMapper;

public class ParticipateServiceImpl implements ParticipateService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int addParticipant(int uid, int pid, int successDate) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		map.put("uid", uid);
		map.put("pid", pid);
		map.put("successDate", successDate);
		
		return sqlSession.getMapper(ParticipateMapper.class).addParticipant(map);
	}

}
