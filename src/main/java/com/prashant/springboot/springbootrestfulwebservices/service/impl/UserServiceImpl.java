package com.prashant.springboot.springbootrestfulwebservices.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prashant.springboot.springbootrestfulwebservices.pojo.User;
import com.prashant.springboot.springbootrestfulwebservices.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	static {
		users.add(new User(++userCount, "Aarav", new Date()));
		users.add(new User(++userCount, "Aayansh", new Date()));
		users.add(new User(++userCount, "Vaibhav", new Date()));
		users.add(new User(++userCount, "Sudhanshu", new Date()));
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser(Integer id) {
		Optional<User> usr = users.stream().filter(user -> user.getId().equals(id)).findFirst();
		User user = null;
		if (usr.isPresent())
			user = usr.get();
		return user;
	}

	public User insert(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

}
