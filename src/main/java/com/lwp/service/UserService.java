package com.lwp.service;

import com.lwp.pojo.User;

public interface UserService {
	public User findUserByName(String name);
	
	public User findUserByid(Long id);
}
