package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.mapper.ParticipateMapper;

@Service
public class ParticipateServiceImpl implements ParticipateService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int addParticipant(int uid, int pid, int successDate) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("uid", uid);
		map.put("pid", pid);
		map.put("successDate", successDate);
		
		return sqlSession.getMapper(ParticipateMapper.class).addParticipant(map);
	}

	@Override
	public int deleteParticipant(int uid, int pid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("uid", uid);
		map.put("pid", pid);
		
		return sqlSession.getMapper(ParticipateMapper.class).deleteParticipant(map);
		
	}

	@Override
	public int checkParticipant(int uid, int pid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("uid", uid);
		map.put("pid", pid);
		
		return sqlSession.getMapper(ParticipateMapper.class).checkParticipant(map);
	}

}
