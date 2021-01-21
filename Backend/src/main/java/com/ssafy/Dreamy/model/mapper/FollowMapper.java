package com.ssafy.Dreamy.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.FollowDto;

@Mapper
public interface FollowMapper {
	public void setFollow(FollowDto followdto);

	public void revokeFollow(int followed, int follow);

	public int countFollowing(int userid);

	public int countFollower(int userid);
}
