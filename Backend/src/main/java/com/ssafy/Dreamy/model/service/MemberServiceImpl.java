package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.MemberDto;
import com.ssafy.Dreamy.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(String email, String password) throws Exception {
		if(email == null || password == null)
			return null;
		System.out.println("--로그인 정보 / 이메일 : " + email + " 비번 : " + password);
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.getMapper(MemberMapper.class).login(map);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public void signup(String email, String name, String password, String phone) throws Exception {
		Map<String, String>map = new HashMap<>();
		map.put("email", email);
		map.put("name", name);
		map.put("password", password);
		map.put("phone", phone);
//		MemberDto memberDto = new MemberDto(email,nickname,password,phone);
		sqlSession.getMapper(MemberMapper.class).signup(map);
	}
	
	@Override
	public void delete(String email) {
		sqlSession.getMapper(MemberMapper.class).delete(email);

	}

}
