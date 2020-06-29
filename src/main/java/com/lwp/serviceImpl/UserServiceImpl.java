package com.lwp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwp.mapper.UserMapper;
import com.lwp.pojo.User;
import com.lwp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	@Override
	public User findUserByName(String name) {
		
		return mapper.findUserByName(name);
	}
	@Override
	public User findUserByid(Long id) {
		return mapper.findUserById(id);
	}

}
