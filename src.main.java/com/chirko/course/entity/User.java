package com.chirko.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class User{

	 
	private long id;
	private String name;
	
	private String pass;
	private String email;
	
	public User () {}
	public User (String name, String pass, String email) {
		
		this.name=name;
		this.pass = pass;
		this.email = email;
	}
	
	@Column
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}

	@Column
	public synchronized String getPass() {
		return pass;
	}
	public synchronized void setPass(String pass) {
		this.pass = pass;
	}

	@Column
	public synchronized String getEmail() {
		return email;
	}
	public synchronized void setEmail(String email) {
		this.email = email;
	}
	
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
