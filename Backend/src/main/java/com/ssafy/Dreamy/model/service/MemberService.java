package com.ssafy.Dreamy.model.service;

import com.ssafy.Dreamy.model.MemberDto;

public interface MemberService {

	public MemberDto login(String email, String password) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
//	public MemberDto userJoin(MemberDto memberDto) throws Exception;
	public void join(String email, String nickname, String password, String phone) throws Exception;
	
}
