package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.BoardDto;

public interface BoardService {

	public int insertBucket(BoardDto boardDto) throws Exception;	// 등록
	public int insertChallenge(BoardDto boardDto) throws Exception;	// 등록
	public List<BoardDto> getList(int uid, int limit) throws Exception;							// 조회
	public List<BoardDto> getBucketOrChallengeList(int boardType, int limit) throws Exception;	// 조회
	public int getListTotalSize(int uid) throws Exception;						// 전체리스트 개수
	public int getBucketOrChallengeTotalSize(int boardType) throws Exception;	// 버킷 or 챌린지 개수 (1:버킷, 2:챌린지)
	public void update(int pid, String content) throws Exception;
	
	
}
