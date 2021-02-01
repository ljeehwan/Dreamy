package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.UserDto;

@Mapper
public interface UserMapper {

	public int login(Map<String, String> map) throws SQLException;
	public UserDto setUser(String email) throws SQLException;		// for social login
	public String getLoginType(String email) throws SQLException;	// for social login
	public int signup(Map<String, Object> map) throws SQLException;
	public int getEmail(String email) throws SQLException;	// for signup
	public int getName(String name) throws SQLException;	// for signup
	public int confirm(Map<String, Object> map) throws SQLException;	// for update, delete
	public int update(Map<String, Object> map) throws SQLException;
	public int delete(int uid) throws SQLException;
	public int certification(Map<String, String> map)throws SQLException;	// for find password
	public void updatePassword(Map<String, String> map)throws SQLException;	// for find password
	public UserDto userInfo(int uid) throws SQLException;
	
}
