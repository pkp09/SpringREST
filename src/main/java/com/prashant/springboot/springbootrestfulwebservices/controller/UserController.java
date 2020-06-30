package com.prashant.springboot.springbootrestfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prashant.springboot.springbootrestfulwebservices.exception.UserNotFoundException;
import com.prashant.springboot.springbootrestfulwebservices.pojo.User;
import com.prashant.springboot.springbootrestfulwebservices.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/user/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {
		User user = userService.getUser(id);
		if (Objects.isNull(user))
			throw new UserNotFoundException("User Not Found");

		// this will add link all-user in the response
		@SuppressWarnings("deprecation")
		EntityModel<User> resource = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<User> insert(@Valid @RequestBody User user) {
		User insert = userService.insert(user);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(insert.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void delteUser(@PathVariable Integer id) {
		User user = userService.deletUser(id);
		if(Objects.isNull(user))
			throw new UserNotFoundException("User with Id - " + id + " Not available.");
	}
}
