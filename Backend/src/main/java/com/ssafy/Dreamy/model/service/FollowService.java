package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.UserDto;

public interface FollowService {
	public void followService(int following, int follow);// 팔로우버튼 누른사람, 팔로우당한사람

	public void unfollowService(int followed, int follow);// 언팔로우버튼 누른사람, 언팔로우 당하는사람

	public int countfollowing(int userid); // 팔로잉수 카운팅한거 리턴

	public int countfollower(int userid); // 팔로워수 카운팅한거 리턴

	public boolean followcheck(int userid, int targetid); // 팔로우관계를 맺은 유저인지 체크 => 팔로우버튼을 누른상태인지 아닌지

	public List<UserDto> listfollowing(int userid); // 팔로잉 목록(팔로잉하는 사람들 이름을 보여준다)

	public List<UserDto> listfollower(int userid);// 팔로워 목록(팔로워들의 이름을 보여준다)
}
