package com.prashant.springboot.springbootrestfulwebservices.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.prashant.springboot.springbootrestfulwebservices.pojo.User;

@Component
public interface UserService {

	public List<User> getUsers();
}
