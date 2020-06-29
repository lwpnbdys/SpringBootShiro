package com.lwp.mapper;

import com.lwp.pojo.User;

public interface UserMapper {
	public User findUserByName(String name);
	
	public User findUserById(Long id);
}
