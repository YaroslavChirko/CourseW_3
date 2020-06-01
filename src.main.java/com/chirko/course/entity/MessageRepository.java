package com.chirko.course.entity;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Long>{
	public Message findByUsername(String username);
	public ArrayList<Message> findAllByGroupname(String groupname);
	
	@Query(value = "select distinct groupname from message_table", nativeQuery = true)
	public ArrayList<String> findAllUniqueGroupnameRow();
}
