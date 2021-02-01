package com.ssafy.Dreamy.model.service;

import java.sql.SQLException;
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
	public boolean login(String email, String password) throws Exception {
		if (email == null || password == null)
			return false;
//		System.out.println("--로그인 정보 / 이메일 : " + email + " 비번 : " + password);
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		int res=sqlSession.getMapper(UserMapper.class).login(map);
		if(res==1)
			return true;
		else
			return false; 
	}
	
	@Override
	public UserDto setUser(String email) throws Exception {
		return sqlSession.getMapper(UserMapper.class).setUser(email);
	}

	@Override
	public String getLoginType(String email) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getLoginType(email);
	}
	
	@Override
	public int signup(UserDto userDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("email", userDto.getEmail());
		map.put("name", userDto.getName());
		map.put("password", userDto.getPassword());
		map.put("phone", userDto.getPhone());
		if (userDto.getLoginType()==null)
			map.put("loginType", "default");
		else
			map.put("loginType", userDto.getLoginType());
		return sqlSession.getMapper(UserMapper.class).signup(map);
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
	public int confirm(int uid, String password) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("password", password);
		return sqlSession.getMapper(UserMapper.class).confirm(map);
	}
	
	@Override
	public int update(UserDto userDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", userDto.getUid());
		map.put("password", userDto.getPassword());
		map.put("phone", userDto.getPhone());
		return sqlSession.getMapper(UserMapper.class).update(map);
	}
	
	@Override
	public int delete(int uid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).delete(uid);
	}

	@Override
	public int certification(String email, String phone) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("phone", phone);
		int result = sqlSession.getMapper(UserMapper.class).certification(map);
		
		return result;
	}

	@Override
	public void updatePassword(String email, String password) throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		sqlSession.getMapper(UserMapper.class).updatePassword(map);
	}

	@Override
	public UserDto userInfo(int uid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(uid);
	}
	
	@Override
	public void profileUpload(int uid, String imageUrl) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("profileUrl", imageUrl);
		sqlSession.getMapper(UserMapper.class).profileUpload(map);
	}
	
}
