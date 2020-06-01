package com.chirko.course.entity;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<com.chirko.course.entity.User,Long> {
	public User findByNameAndPass(String name,String pass);
	public boolean existsByName(String name);
	public boolean existsByEmail(String email);
	}
