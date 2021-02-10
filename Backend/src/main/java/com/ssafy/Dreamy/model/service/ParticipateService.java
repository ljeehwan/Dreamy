package com.ssafy.Dreamy.model.service;

public interface ParticipateService {
	public int addParticipant(int uid, int pid, int successDate) throws Exception;		// 게시물 참가하기
	public int deleteParticipant(int uid, int pid) throws Exception;					// 참가 취소하기
	public int checkParticipant(int uid, int pid) throws Exception;						// 참가 여부 확인
}
