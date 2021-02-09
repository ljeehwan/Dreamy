package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.BoardDto;

public interface MylistService {
	public List<BoardDto> getMyBoardList(int uid, int limit) throws Exception;
	public List<BoardDto> getMyParticipateList(int uid, int limit) throws Exception;
	
}
