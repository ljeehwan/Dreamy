package com.ssafy.Dreamy.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface MylistMapper {
	public List<BoardDto> getMyBoardList(int uid) throws Exception;
	public List<BoardDto> getMyParticipateList(int uid) throws Exception;
}
