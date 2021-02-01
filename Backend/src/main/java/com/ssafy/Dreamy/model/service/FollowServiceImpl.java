package com.ssafy.Dreamy.model.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.FollowDto;
import com.ssafy.Dreamy.model.UserDto;
import com.ssafy.Dreamy.model.mapper.FollowMapper;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void followService(int following, int follow) {// 팔로우버튼 누른사람, 팔로우당한사람
		FollowDto followdto = new FollowDto();

		followdto.setFollowingUid(following);
		followdto.setFollowUid(follow);

		sqlSession.getMapper(FollowMapper.class).setFollow(followdto);
	}

	@Override
	public void unfollowService(int followed, int follow) {// 언팔로우버튼 누른사람, 언팔로우 당하는사람

		sqlSession.getMapper(FollowMapper.class).revokeFollow(followed, follow);
	}

	@Override
	public int countfollowing(int userid) {
		return sqlSession.getMapper(FollowMapper.class).countFollowing(userid);
	}

	@Override
	public int countfollower(int userid) {
		return sqlSession.getMapper(FollowMapper.class).countFollower(userid);
	}

	@Override
	public boolean followcheck(int userid, int targetid) {
		FollowDto followdto = new FollowDto();

		followdto.setFollowingUid(userid);
		followdto.setFollowUid(targetid);
		if (sqlSession.getMapper(FollowMapper.class).checkFollow(followdto) == 0) {
			return false; // 해당유저가 타겟유저한테 팔로우요청한게 없으면 false => 이 부분은 나중에 수락/거절 기능이 생긴다면 바뀌어야함!!
		}
		return true; // 0이 아니라면 팔로우관계성립
	}

	@Override
	public List<UserDto> listfollowing(int userid) {// 팔로우 리스트 넘겨줌
		List<UserDto> list = new LinkedList<UserDto>();
		list = sqlSession.getMapper(FollowMapper.class).listFollowing(userid);
		return list;
	}

	@Override
	public List<UserDto> listfollower(int userid) {// 팔로워 리스트 넘겨줌
		List<UserDto> list = new LinkedList<UserDto>();
		list = sqlSession.getMapper(FollowMapper.class).listFollower(userid);
		return list;
	}

}
