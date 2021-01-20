package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.UserDto;
import com.ssafy.Dreamy.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(String email, String password) throws Exception {
		if (email == null || password == null)
			return null;
		System.out.println("--로그인 정보 / 이메일 : " + email + " 비번 : " + password);
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.getMapper(UserMapper.class).login(map);
	}

	@Override
	public void signup(String email, String name, String password, String phone) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("name", name);
		map.put("password", password);
		map.put("phone", phone);
		sqlSession.getMapper(UserMapper.class).signup(map);
	}
	
	@Override
	public int getEmail(String email) throws Exception {
		int ret = sqlSession.getMapper(UserMapper.class).getEmail(email);
		return ret;
	}
	
	@Override
	public int getName(String name) throws Exception {
		int ret = sqlSession.getMapper(UserMapper.class).getName(name);
		return ret;
	}

	@Override
	public void delete(String email) throws Exception {
		sqlSession.getMapper(UserMapper.class).delete(email);
	}

	@Override
	public void update(String email, String password, String phone) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		map.put("phone", phone);
		sqlSession.getMapper(UserMapper.class).update(map);
	}
	
	@Override
	public UserDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(userid);
	}

}
