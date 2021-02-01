package com.ssafy.Dreamy.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.FollowDto;
import com.ssafy.Dreamy.model.UserDto;

@Mapper
public interface FollowMapper {
	public void setFollow(FollowDto followdto);

	public void revokeFollow(int followed, int follow);

	public int countFollowing(int userid);

	public int countFollower(int userid);

	public int checkFollow(FollowDto followdto);

	public List<UserDto> listFollowing(int userid);

	public List<UserDto> listFollower(int userid);
}
