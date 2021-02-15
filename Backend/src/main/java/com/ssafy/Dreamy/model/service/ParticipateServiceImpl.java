package com.ssafy.Dreamy.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.UserDto;
import com.ssafy.Dreamy.model.mapper.ParticipateMapper;

@Service
public class ParticipateServiceImpl implements ParticipateService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int addParticipant(int uid, int pid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("pid", pid);
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

	@Override
	public List<UserDto> getUserList(int pid) throws Exception {
		return sqlSession.getMapper(ParticipateMapper.class).getUserList(pid);
	}

	@Override
	public int getListSize(int pid) throws SQLException {
		return sqlSession.getMapper(ParticipateMapper.class).getListSize(pid);
	}

	@Override
	public int bucketSuccess(int uid, int pid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("pid", pid);
		return sqlSession.getMapper(ParticipateMapper.class).bucketSuccess(map);
	}

}
