package com.ssafy.Dreamy.model.service;

import com.ssafy.Dreamy.model.UserDto;

public interface UserService {

	public boolean login(String email, String password) throws Exception;
	public UserDto setUser(String email) throws Exception;		// for social login
	public String getLoginType(String email) throws Exception;	// for social login
	public int signup(UserDto userDto) throws Exception;
	public int getEmail(String email) throws Exception;		// for signup
	public int getName(String name) throws Exception;		// for signup
	public int confirm(int uid, String password) throws Exception;	// for update, delete
	public int update(UserDto userDto) throws Exception;
	public int delete(int uid) throws Exception;
	public String createPassword() throws Exception;
	public int certification(String email, String name) throws Exception;		// for find password
	public int updatePassword(String email, String password)throws Exception;	// for find password
	public int sendEmail(String email, String newPassword) throws Exception;
	public UserDto userInfo(int uid) throws Exception;
	public int updateProfile(int uid, String profileUrl) throws Exception; // for upload profile image
}
