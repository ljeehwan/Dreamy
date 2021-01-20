package com.ssafy.Dreamy.model.service;

public interface FollowService {
	public void followService(int following,int follow);//팔로우버튼 누른사람, 팔로우당한사람
	public void unfollowService(int followed, int follow);//언팔로우버튼 누른사람, 언팔로우 당하는사람
	
}
