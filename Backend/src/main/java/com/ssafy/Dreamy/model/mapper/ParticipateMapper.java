package com.ssafy.Dreamy.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParticipateMapper {
	public int addParticipant(Map<String, Object> map) throws Exception;
	public int deleteParticipant(Map<String, Object> map) throws Exception;
}
