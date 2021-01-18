package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.MemberDto;

@Mapper
public interface MemberMapper {

//	public MemberDto login(String email, String password) throws SQLException;
	public MemberDto login(Map<String, String> map) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void userJoin(Map<String, String> map)throws SQLException;
	
}
