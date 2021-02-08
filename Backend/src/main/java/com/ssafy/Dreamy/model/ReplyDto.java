package com.ssafy.Dreamy.model;

import java.time.LocalDateTime;

public class ReplyDto {
	
	private int rid;	// AI
	private int pid;
	private int uid;
	private String content;
	private LocalDateTime writtenDate;	// DATETIME, DEFAULT CURRENT_TIMESTAMP
	
	public final int getPid() {
		return pid;
	}
	
	public final void setPid(int pid) {
		this.pid = pid;
	}
	
	public final int getUid() {
		return uid;
	}
	
	public final void setUid(int uid) {
		this.uid = uid;
	}
	
	public final String getContent() {
		return content;
	}
	
	public final void setContent(String content) {
		this.content = content;
	}
	
	public final LocalDateTime getWrittenDate() {
		return writtenDate;
	}

	public final void setWrittenDate(LocalDateTime writtenDate) {
		this.writtenDate = writtenDate;
	}
	
}
