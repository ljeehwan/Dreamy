package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParticipateMapper {
	public int addParticipant(Map<String, Object> map) throws SQLException;		// 게시물 참가하기
	public int deleteParticipant(Map<String, Object> map) throws SQLException;	// 참가 취소하기
	public int checkParticipant(Map<String, Object> map) throws SQLException;	// 참가 여부 확인
}
