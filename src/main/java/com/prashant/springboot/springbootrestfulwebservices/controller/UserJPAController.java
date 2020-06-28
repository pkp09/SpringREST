package com.prashant.springboot.springbootrestfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prashant.springboot.springbootrestfulwebservices.exception.UserNotFoundException;
import com.prashant.springboot.springbootrestfulwebservices.pojo.Post;
import com.prashant.springboot.springbootrestfulwebservices.pojo.User;
import com.prashant.springboot.springbootrestfulwebservices.service.UserJpaService;
import com.prashant.springboot.springbootrestfulwebservices.service.impl.UserServiceImpl;

@RestController
public class UserJPAController {

	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	UserJpaService userJpaService;

	@GetMapping("/jpa/users")
	public List<User> getUsers() {
		return userJpaService.findAll();
	}

	@GetMapping("/jpa/user/{id}")
	public EntityModel<User> getUser(@PathVariable Integer id) {
		Optional<User> user = userJpaService.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User Not Found");

		// this will add link all-user in the response
		EntityModel<User> resource = setLink(user, "all-users");
		
		return resource;
	}

	private EntityModel<User> setLink(Optional<User> user, String linkName) {
		@SuppressWarnings("deprecation")
		EntityModel<User> resource = new EntityModel<>(user.get());
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getUsers());
		
		resource.add(linkTo.withRel(linkName));
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> insert(@Valid @RequestBody User user) {
		User insert = userJpaService.save(user);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(insert.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void delteUser(@PathVariable Integer id) {
		userJpaService.deleteById(id);
	}
	
	@GetMapping("/jpa/user/{id}/posts")
	public List<Post> getAllPostByUser(@PathVariable Integer id) {
		Optional<User> user = userJpaService.findById(id);
		
		if (!user.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		return user.get().getPosts();
	}
}
