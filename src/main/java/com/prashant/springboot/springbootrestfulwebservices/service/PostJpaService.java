package com.prashant.springboot.springbootrestfulwebservices.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prashant.springboot.springbootrestfulwebservices.pojo.Post;

@Repository
public interface PostJpaService extends JpaRepository<Post, Integer>{
}
