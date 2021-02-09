package com.ssafy.Dreamy.model.service;

public interface ParticipateService {
	public int addParticipant(int uid, int pid, int successDate) throws Exception;
	public int deleteParticipant(int uid, int pid) throws Exception;
}
