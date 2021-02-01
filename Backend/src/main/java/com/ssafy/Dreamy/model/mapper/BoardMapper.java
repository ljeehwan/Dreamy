package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface BoardMapper {
	
	public int insertBucket(Map<String, Object> map) throws SQLException;	// 등록
	public int insertChallenge(Map<String, Object> map) throws SQLException;	// 등록
	public List<BoardDto> getList(@Param("limit") int limit) throws SQLException;			// 조회
	public List<BoardDto> getBucketList() throws SQLException;		// 조회
	public List<BoardDto> getChallengeList() throws SQLException;	// 조회
	public void update(Map<String, Object> map) throws SQLException;
	
	public int getListTotalSize() throws SQLException;	// 리스트 개수 조회
}
