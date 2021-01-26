package com.ssafy.Dreamy.model.service;

import com.ssafy.Dreamy.model.BoardDto;

public interface BoardService {

	public int createBucket(BoardDto boardDto) throws Exception;
	public int createChallenge(BoardDto boardDto) throws Exception;
	public void update(int pid, String content) throws Exception;
	
}
