package com.ssafy.Dreamy.model.service;

import com.ssafy.Dreamy.model.UserDto;

public interface UserService {

	public UserDto login(String email, String password) throws Exception;
	public void signup(String email, String name, String password, String phone) throws Exception;
	public int getEmail(String email) throws Exception;
	public int getName(String name) throws Exception;
	public void delete(String email) throws Exception;
	public void update(String email, String password, String phone) throws Exception;
	
	public int certification(String email, String phone) throws Exception;
	public void updatePassword(String email, String password)throws Exception;
	
	public UserDto userInfo(String userid) throws Exception;
	
}
