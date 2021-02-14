package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.Dreamy.model.UserDto;

@Mapper
public interface ParticipateMapper {
	public int addParticipant(Map<String, Object> map) throws SQLException;		// 게시물 참가하기
	public int deleteParticipant(Map<String, Object> map) throws SQLException;	// 참가 취소하기
	public int checkParticipant(Map<String, Object> map) throws SQLException;	// 참가 여부 확인
	public List<UserDto> getUserList(int pid) throws SQLException;				// 참가자 리스트 조회
	public int getListSize(int pid) throws SQLException;						// 참가자 수 조회
	public int addSuccess(Map<String, Object> map) throws SQLException;			// 달성 체크
	public int getTotalDate(int pid) throws SQLException;						// total date 조회
	public int getSuccessDate(Map<String, Object> map) throws SQLException;		// success date 조회
}
