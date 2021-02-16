package com.ssafy.Dreamy.model;

public class ParticipateDto {
	private int uid;
	private int pid;
	private int success;	// 0: 버킷리스트 미달성, 1: 버킷리스트 달성

	public final int getUid() {
		return uid;
	}
	
	public final void setUid(int uid) {
		this.uid = uid;
	}
	
	public final int getPid() {
		return pid;
	}
	
	public final void setPid(int pid) {
		this.pid = pid;
	}
	
	public final int getSuccess() {
		return success;
	}
	
	public final void setSuccess(int success) {
		this.success = success;
	}
	
}
