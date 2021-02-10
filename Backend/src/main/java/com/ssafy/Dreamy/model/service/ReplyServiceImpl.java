package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.ReplyDto;
import com.ssafy.Dreamy.model.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;
	private Map<String, Object> map;

	@Override
	public int insert(ReplyDto replyDto) throws Exception {
		map = new HashMap<>();
		map.put("pid", replyDto.getPid());
		map.put("uid", replyDto.getUid());
		map.put("content", replyDto.getContent());
		return sqlSession.getMapper(ReplyMapper.class).insertReply(map);
	}

	@Override
	public int countList(int pid) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).countList(pid);
	}

	@Override
	public List<ReplyDto> getList(int pid) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).getList(pid);
	}

	@Override
	public int delete(int rid) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).deleteReply(rid);
	}
	
}
