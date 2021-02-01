package com.ssafy.Dreamy.model;

import java.sql.Date;

public class BoardDto {
	
	private int pid;			// AI
	private int uid;			// FK
	private int boardType;
	private String title;
	private String content;
	private Date writtenDate;	// DATETIME, DEFAULT CURRENT_TIMESTAMP
	private Date startDate;		// DATE
	private Date endDate;		// DATE
	private int category;		// DEFAULT 6
	private String imageUrl;	// TEXT
	private long totalDate;		// not int
	
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

	public final int getBoardType() {
		return boardType;
	}
	
	public final void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	
	public final String getTitle() {
		return title;
	}
	
	public final void setTitle(String title) {
		this.title = title;
	}
	
	public final String getContent() {
		return content;
	}
	
	public final void setContent(String content) {
		this.content = content;
	}
	
	public final Date getWrittenDate() {
		return writtenDate;
	}
	
	public final void setWrittenDate(Date writtenDate) {
		this.writtenDate = writtenDate;
	}
	
	public final Date getStartDate() {
		return startDate;
	}
	
	public final void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public final Date getEndDate() {
		return endDate;
	}
	
	public final void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public final int getCategory() {
		return category;
	}

	public final void setCategory(int category) {
		this.category = category;
	}

	public final String getImageUrl() {
		return imageUrl;
	}
	
	public final void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public final long getTotalDate() {
		return totalDate;
	}

	public final void setTotalDate(long totalDate) {
		this.totalDate = totalDate;
	}
	
}
