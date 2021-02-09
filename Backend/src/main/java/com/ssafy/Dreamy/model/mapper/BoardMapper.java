package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.Dreamy.model.BoardDto;

@Mapper
public interface BoardMapper {
	
	// search
	public int searchTotalSize(@Param("word") String word) throws SQLException;		// 검색 리스트 개수
	public List<BoardDto> searchList(Map<String, Object> map) throws SQLException;	// 검색 리스트
	
	// insert
	public int insertBucket(Map<String, Object> map) throws SQLException;		// 등록
	public int insertChallenge(Map<String, Object> map) throws SQLException;	// 등록
	
	// get
	public int getAllListTotalSize() throws SQLException;										// 전체 리스트 개수
	public int getCategoryListTotalSize(@Param("category") int category) throws SQLException;	// 카테고리별 리스트 개수
	public List<BoardDto> getAllList(Map<String, Object> map) throws SQLException;		// 전체 조회
	public List<BoardDto> getCategoryList(Map<String, Object> map) throws SQLException;	// 카테고리별 조회
	
	// update
	public int update(Map<String, Object> map) throws SQLException;
	
	// delete
	public int delete(@Param("pid") int pid) throws SQLException;
	
}
