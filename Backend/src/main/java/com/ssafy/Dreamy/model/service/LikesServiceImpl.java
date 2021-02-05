package com.ssafy.Dreamy.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.LikesDto;
import com.ssafy.Dreamy.model.mapper.LikesMapper;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addLike(int uid, int pid) { // 좋아요 버튼 누른사람, 좋아요누른 게시물
		LikesDto likesdto = new LikesDto();

		likesdto.setUserid(uid);
		likesdto.setPostid(pid);

		sqlSession.getMapper(LikesMapper.class).setLikes(likesdto);

	}

	@Override
	public void unLike(int uid, int pid) { // 좋아요취소하고자 하는 사람, 좋아요 취소 당할 게시물
		LikesDto likesdto = new LikesDto();

		likesdto.setUserid(uid);
		likesdto.setPostid(pid);

		sqlSession.getMapper(LikesMapper.class).unLikes(likesdto);

	}

	@Override
	public int countLikes(int pid) {
		return sqlSession.getMapper(LikesMapper.class).countLikes(pid);
	}

}
