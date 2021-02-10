package com.ssafy.Dreamy.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParticipateMapper {
	public int addParticipant(Map<String, Object> map) throws Exception;		// 게시물 참가하기
	public int deleteParticipant(Map<String, Object> map) throws Exception;		// 참가 취소하기
}
