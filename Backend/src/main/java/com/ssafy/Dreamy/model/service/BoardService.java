package com.ssafy.Dreamy.model.service;

import com.ssafy.Dreamy.model.BoardDto;

public interface BoardService {

	public Object create(BoardDto boardDto) throws Exception;
	public void update(int pid, String content) throws Exception;
}
