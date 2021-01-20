package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.UserDto;

@Mapper
public interface UserMapper {

	public UserDto login(Map<String, String> map) throws SQLException;
	public void signup(Map<String, String> map) throws SQLException;
	public int getEmail(String email) throws SQLException;
	public int getName(String name) throws SQLException;
	public void delete(String email) throws SQLException;
	public void update(Map<String, String> map) throws SQLException;
	
	public UserDto userInfo(String userid) throws SQLException;
	
}
