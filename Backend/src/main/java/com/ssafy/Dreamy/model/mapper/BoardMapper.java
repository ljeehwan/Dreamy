package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface BoardMapper {
	
	public int insertBucket(Map<String, Object> map) throws SQLException;		// 등록
	public int insertChallenge(Map<String, Object> map) throws SQLException;	// 등록
	public List<BoardDto> getList(Map<String, Object> map) throws SQLException;						// 조회
	public List<BoardDto> getBucketOrChallengeList(Map<String, Object> map) throws SQLException;	// 조회
	public int getListTotalSize(@Param("uid") int uid) throws SQLException;								// 리스트 개수 조회
	public int getBucketOrChallengeTotalSize(@Param("boardType") int boardType) throws SQLException;	// 버킷 or 챌린지 개수 조회
	public int update(Map<String, Object> map) throws SQLException;
	public int delete(@Param("pid") int pid) throws SQLException;
	
}
