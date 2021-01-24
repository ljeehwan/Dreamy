package com.ssafy.Dreamy.model.service;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	String get(String key);
	boolean isUsable(String jwt);
	
}
