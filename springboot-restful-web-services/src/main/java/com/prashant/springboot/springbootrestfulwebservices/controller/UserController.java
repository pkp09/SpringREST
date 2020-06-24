package com.prashant.springboot.springbootrestfulwebservices.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.springboot.springbootrestfulwebservices.exception.UserNotFoundException;
import com.prashant.springboot.springbootrestfulwebservices.pojo.User;
import com.prashant.springboot.springbootrestfulwebservices.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Integer id){
		User user = userService.getUser(id);
		if(Objects.isNull(user))
			throw new UserNotFoundException("Not FOund");
		return user;
	}
	
	@PostMapping("/users")
	public User insert(@RequestBody User user) {
		return userService.insert(user);
	}
}
