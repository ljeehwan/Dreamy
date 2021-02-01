package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.BoardDto;

public interface BoardService {

	public int insertBucket(BoardDto boardDto) throws Exception;	// 등록
	public int insertChallenge(BoardDto boardDto) throws Exception;	// 등록
	public List<BoardDto> getList(int uid, int limit) throws Exception;	// 조회
//	public List<BoardDto> getBucketList() throws Exception;				// 조회
//	public List<BoardDto> getChallengeList() throws Exception;			// 조회
	public int getListTotalSize(int uid, int boardType) throws Exception;	// 리스트 개수 조회 0:전체, 1:버킷, 2:챌린지
	public void update(int pid, String content) throws Exception;
	
	
}
