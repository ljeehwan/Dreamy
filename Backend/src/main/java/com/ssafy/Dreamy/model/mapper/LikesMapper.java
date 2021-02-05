package com.ssafy.Dreamy.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.LikesDto;

@Mapper
public interface LikesMapper {
	public void setLikes(LikesDto likesdto);

	public void unLikes(LikesDto likesdto);

	public int countLikes(int pid);

}
