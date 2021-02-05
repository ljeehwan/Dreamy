package com.ssafy.Dreamy.model.service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ssafy.Dreamy.model.UserDto;
import com.ssafy.Dreamy.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean login(String email, String password) throws Exception {
		if (email == null || password == null)
			return false;
//		System.out.println("--로그인 정보 / 이메일 : " + email + " 비번 : " + password);
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		int res=sqlSession.getMapper(UserMapper.class).login(map);
		if(res==1)
			return true;
		else
			return false; 
	}
	
	@Override
	public UserDto setUser(String email) throws Exception {
		return sqlSession.getMapper(UserMapper.class).setUser(email);
	}

	@Override
	public String getLoginType(String email) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getLoginType(email);
	}
	
	@Override
	public int signup(UserDto userDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("email", userDto.getEmail());
		map.put("name", userDto.getName());
		map.put("password", userDto.getPassword());
		map.put("phone", userDto.getPhone());
		map.put("profileUrl", userDto.getProfileUrl());
		if (userDto.getLoginType()==null)
			map.put("loginType", "default");
		else
			map.put("loginType", userDto.getLoginType());
		return sqlSession.getMapper(UserMapper.class).signup(map);
	}
	
	@Override
	public int getEmail(String email) throws Exception {
		int ret = sqlSession.getMapper(UserMapper.class).getEmail(email);
		return ret;
	}
	
	@Override
	public int getName(String name) throws Exception {
		int ret = sqlSession.getMapper(UserMapper.class).getName(name);
		return ret;
	}

	@Override
	public int confirm(int uid, String password) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("password", password);
		return sqlSession.getMapper(UserMapper.class).confirm(map);
	}
	
	@Override
	public int update(UserDto userDto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", userDto.getUid());
		map.put("password", userDto.getPassword());
		map.put("phone", userDto.getPhone());
		return sqlSession.getMapper(UserMapper.class).update(map);
	}
	
	@Override
	public int delete(int uid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).delete(uid);
	}

	@Override
	public int certification(String email, String name) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("name", name);
		int result = sqlSession.getMapper(UserMapper.class).certification(map);
		
		return result;
	}

	@Override
	public int updatePassword(String email, String password) throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.getMapper(UserMapper.class).updatePassword(map);
	}

	@Override
	public UserDto userInfo(int uid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(uid);
	}
	
	@Override
	public int profileUpload(int uid, String imageUrl) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("profileUrl", imageUrl);
		return sqlSession.getMapper(UserMapper.class).profileUpload(map);
	}

	@Override
	@Async
	public int sendEmail(String email, String newPassword) throws Exception {
		int result = 0;
		String host = "smtp.gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
		String user = "dreamya306@gmail.com"; 
		// 패스워드 
		String password = "ssafyA306!";      
		
		// SMTP 서버 정보를 설정. 
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", 465); 
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
		//Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(user, password); 
			} 
		}); 
		
		try { 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user)); 
			
			//수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
			// 메일 제목 
			message.setSubject("[Dreamy] 비밀번호 찾기를 통해 발급된 임시 비밀번호를 보내드립니다."); 
			// 메일 내용 
			message.setText("임시비밀번호 : " + newPassword);
			// send the message 
			Transport.send(message); 
			System.out.println("Success Message Send"); 
			
		} catch (MessagingException e) { 
			result = -1;
			e.printStackTrace(); 
		}
		
		return result;
	}

	@Override
	public String createPassword() throws Exception {
		String password = "";
		// 현재 날짜로 SHA-256 적용
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String msg = format.format(date);
		
		MessageDigest md;
		try {
			// SHA-256으로 해싱
			md = MessageDigest.getInstance("SHA-256");
			
			md.update(msg.getBytes());
			// 바이트를 헥스값으로 변환
			StringBuilder sb = new StringBuilder();
			
			for (byte b : md.digest())
				sb.append(String.format("%02x", b));
			
			password = sb.substring(sb.length() - 8, sb.length());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return password;
	}
	
}
