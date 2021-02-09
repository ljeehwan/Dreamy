package com.ssafy.Dreamy.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface MylistMapper {
	public List<BoardDto> getMyBoardList(Map<String, Object> map) throws Exception;
	public List<BoardDto> getMyParticipateList(Map<String, Object> map) throws Exception;
}
