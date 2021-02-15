package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.FollowDto;
import com.ssafy.Dreamy.model.UserDto;

@Mapper
public interface FollowMapper {
	public void setFollow(FollowDto followdto);

	public void revokeFollow(FollowDto followdto);

	public int countFollowing(int userid);

	public int countFollower(int userid);

	public int checkFollow(FollowDto followdto);

	public List<UserDto> listFollowing(Map<String, Object> map);

	public List<UserDto> listFollower(Map<String, Object> map);
}
