package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface BoardMapper {
	
	public Object insertBucket(Map<String, Object> map) throws SQLException;	// 등록
	public Object insertChallenge(Map<String, Object> map) throws SQLException;	// 등록
	public List<BoardDto> getList() throws SQLException;			// 조회
	public List<BoardDto> getBucketList() throws SQLException;		// 조회
	public List<BoardDto> getChallengeList() throws SQLException;	// 조회
	public void update(Map<String, Object> map) throws SQLException;
	
}
