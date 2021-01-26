package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	
	public Object createBucket(Map<String, Object> map) throws SQLException;
	public Object createChallenge(Map<String, Object> map) throws SQLException;
	public void update(Map<String, Object> map) throws SQLException;
	
}
