package com.ssafy.Dreamy.model;

import java.sql.Date;

public class BoardDto {
	
	private int postid;
	private int userid;
	private int boardType;
	private String title;
	private String content;
	private Date writtenDate;	// DATETIME을 잘 모르겠습니다..
	private Date startDate;		// DATE
	private Date endDate;		// DATE
	private String category;
	private String imageUrl;	// TEXT
	private int dateType;
	private int totalDate;
	private int running;
	
	public final int getPostid() {
		return postid;
	}
	
	public final void setPostid(int postid) {
		this.postid = postid;
	}
	
	public final int getUserid() {
		return userid;
	}
	
	public final void setUserid(int userid) {
		this.userid = userid;
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
	
	public final String getCategory() {
		return category;
	}
	
	public final void setCategory(String category) {
		this.category = category;
	}
	
	public final String getImageUrl() {
		return imageUrl;
	}
	
	public final void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public final int getDateType() {
		return dateType;
	}
	
	public final void setDateType(int dateType) {
		this.dateType = dateType;
	}
	
	public final int getTotalDate() {
		return totalDate;
	}
	
	public final void setTotalDate(int totalDate) {
		this.totalDate = totalDate;
	}
	
	public final int getRunning() {
		return running;
	}
	
	public final void setRunning(int running) {
		this.running = running;
	}
	
}
