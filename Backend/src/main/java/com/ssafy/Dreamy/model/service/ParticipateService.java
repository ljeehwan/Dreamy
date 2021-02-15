package com.ssafy.Dreamy.model.service;

import java.util.List;

import com.ssafy.Dreamy.model.UserDto;

public interface ParticipateService {
	
	public int addParticipant(int uid, int pid) throws Exception;		// 게시물 참가하기
	public int deleteParticipant(int uid, int pid) throws Exception;					// 참가 취소하기
	public int checkParticipant(int uid, int pid) throws Exception;						// 참가 여부 확인
	public List<UserDto>getUserList(int pid) throws Exception;							// 참가자 리스트 조회
	public int getListSize(int pid) throws Exception;									// 참가자 수 조회
	public int bucketSuccess(int uid, int pid) throws Exception;						// 달성 체크
	
}
