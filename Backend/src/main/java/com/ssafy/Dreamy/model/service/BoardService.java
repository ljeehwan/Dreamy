package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.BoardDto;

public interface BoardService {

	public int insertBucket(BoardDto boardDto) throws Exception;	// 등록
	public int insertChallenge(BoardDto boardDto) throws Exception;	// 등록
	public List<BoardDto> getList() throws Exception;			// 조회
//	public List<BoardDto> getBucketList() throws Exception;		// 조회
//	public List<BoardDto> getChallengeList() throws Exception;	// 조회
	public void update(int pid, String content) throws Exception;
	
}
