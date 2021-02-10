package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface MylistMapper {
	public List<BoardDto> getMyBoardList(Map<String, Object> map) throws SQLException;			// 내가 작성한 게시물 조회
	public List<BoardDto> getMyParticipateList(Map<String, Object> map) throws SQLException;	// 내가 참여한 게시물 조회
}
