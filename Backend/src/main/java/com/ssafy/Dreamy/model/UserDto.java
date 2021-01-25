package com.ssafy.Dreamy.model;

import java.util.Date;

public class UserDto {

	private int uid;
	private String email;
	private String name;
	private String password;
	private String phone;
	private Date joindate;
    private String profileUrl;
    private String logintype;

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public final Date getJoindate() {
		return joindate;
	}

	public final void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public final String getProfileUrl() {
		return profileUrl;
	}

	public final void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

}
