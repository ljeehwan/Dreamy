package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.BoardDto;

public interface BoardService {

	// search
	public List<BoardDto> searchList(String word, int limit) throws Exception;	// 검색 리스트
	public int searchTotalSize(String word) throws Exception;					// 검색 리스트 개수
	
	// insert
	public int insertBucket(BoardDto boardDto) throws Exception;	// 등록
	public int insertChallenge(BoardDto boardDto) throws Exception;	// 등록
	
	// get
	public int getListTotalSize(int category) throws Exception;							// 리스트 개수
	public List<BoardDto> getList(int uid, int category, int limit) throws Exception;	// 리스트 조회
	
	// update
	public int update(int pid, String content) throws Exception;
	
	// delete
	public int delete(int pid) throws Exception;
	
}
