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
	private Map<String, Object> map;
	
	@Override
	public int searchTotalSize(String word) throws Exception {
		String searchWord = "%" + word + "%";
		return sqlSession.getMapper(BoardMapper.class).searchTotalSize(searchWord);
	}

	@Override
	public List<BoardDto> searchList(String word, int limit) throws Exception {
		map = new HashMap<>();
		map.put("searchWord", "%" + word + "%");
		map.put("limit", limit);
		return sqlSession.getMapper(BoardMapper.class).searchList(map);
	}
	
	@Override
	public int insertBucket(BoardDto boardDto) throws Exception {
		map = new HashMap<>();
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
		map = new HashMap<>();
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
	public int getListTotalSize(int category) throws Exception {
		if (category == 0)
			return sqlSession.getMapper(BoardMapper.class).getAllListTotalSize();
		else
			return sqlSession.getMapper(BoardMapper.class).getCategoryListTotalSize(category);
	}
	
	@Override
	public List<BoardDto> getList(int uid, int category, int limit) throws Exception {
		map = new HashMap<>();
		if (category == 0) {	// 전체 목록
			map.put("uid", uid);
			map.put("limit", limit);
			return sqlSession.getMapper(BoardMapper.class).getAllList(map);
		} else {				// 카테고리별 목록
			map.put("uid", uid);
			map.put("category", category);
			map.put("limit", limit);
			return sqlSession.getMapper(BoardMapper.class).getCategoryList(map);
		}
	}
	
	@Override
	public int update(int pid, String content) throws Exception {
		map = new HashMap<>();
		map.put("pid", pid);
		map.put("content", content);
		return sqlSession.getMapper(BoardMapper.class).update(map);
	}
	
	@Override
	public int delete(int pid) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).delete(pid);
	}
	
}

