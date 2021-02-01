package com.ssafy.Dreamy.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.BoardDto;
import com.ssafy.Dreamy.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertBucket(BoardDto boardDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", boardDto.getUid());
		map.put("boardType", 1);
		map.put("title", boardDto.getTitle());
		map.put("content", boardDto.getContent());
		map.put("endDate", boardDto.getEndDate());
		map.put("category", boardDto.getCategory());
		map.put("imageUrl", boardDto.getImageUrl());
		return sqlSession.getMapper(BoardMapper.class).insertBucket(map);
	}
	
	@Override
	public int insertChallenge(BoardDto boardDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
        // 연산결과 -950400000. long type 으로 return 된다.
		long tempDate = boardDto.getEndDate().getTime() - boardDto.getStartDate().getTime();
		// Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
		long totalDate = tempDate / (24 * 60 * 60 * 1000);
		
		map.put("uid", boardDto.getUid());
		map.put("boardType", 2);
		map.put("title", boardDto.getTitle());
		map.put("content", boardDto.getContent());
		map.put("startDate", boardDto.getStartDate());
		map.put("endDate", boardDto.getEndDate());
		map.put("category", boardDto.getCategory());
		map.put("imageUrl", boardDto.getImageUrl());
		map.put("totalDate", totalDate);
		return sqlSession.getMapper(BoardMapper.class).insertChallenge(map);
	}
	
	@Override
	public List<BoardDto> getList(int limit) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getList(limit);
	}
	
	@Override
	public List<BoardDto> getBucketList() throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getBucketList();
	}
	
	@Override
	public List<BoardDto> getChallengeList() throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getChallengeList();
	}
	
	@Override
	public void update(int pid, String content) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("content", content);
		sqlSession.getMapper(BoardMapper.class).update(map);
	}
	
	public int getListTotalSize() throws Exception{
		return sqlSession.getMapper(BoardMapper.class).getListTotalSize();
	}
}

