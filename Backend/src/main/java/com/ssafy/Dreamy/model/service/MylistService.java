package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.BoardDto;

public interface MylistService {
	public List<BoardDto> getMyBoardList(int uid, int limit) throws Exception;			// 내가 작성한 게시물 조회
	public List<BoardDto> getMyParticipateList(int uid, int limit) throws Exception;	// 내가 참여한 게시물 조회
	
	public int getMyBoardTotal(int uid) throws Exception;
	public int getMyParticipateTotal(int uid) throws Exception;
}
