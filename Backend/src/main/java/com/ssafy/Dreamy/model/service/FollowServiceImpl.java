package com.ssafy.Dreamy.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.FollowDto;
import com.ssafy.Dreamy.model.mapper.FollowMapper;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void followService(int following, int follow) {//팔로우버튼 누른사람, 팔로우당한사람
		FollowDto followdto = new FollowDto();
		
		followdto.setFollowuid(following);
		followdto.setFollowuid(follow);
		
		sqlSession.getMapper(FollowMapper.class).setFollow(followdto);
	}

	@Override
	public void unfollowService(int followed, int follow) {//언팔로우버튼 누른사람, 언팔로우 당하는사람

		sqlSession.getMapper(FollowMapper.class).revokeFollow(followed,follow);
	}
	
	

}
