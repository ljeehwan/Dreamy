package com.ssafy.Dreamy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.Dreamy.model.ReplyDto;

@Mapper
public interface ReplyMapper {
	
	public int insertReply(Map<String, Object> map) throws SQLException;
	public List<ReplyDto> getList(@Param("pid") int pid) throws SQLException;
	
}
