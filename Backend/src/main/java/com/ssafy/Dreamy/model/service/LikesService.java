package com.ssafy.Dreamy.model.service;

public interface LikesService {

	public void addLike(int uid, int pid);

	public void unLike(int uid, int pid);
}
