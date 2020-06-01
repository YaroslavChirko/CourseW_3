package com.chirko.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MessageTable")
public class Message {
	private long id;
	private String message;
	private String username;
	private String groupname;
	private java.sql.Timestamp publishedOn;
	
	@Column(length=7000)
	public synchronized String getMessage() {
		return message;
	}
	public synchronized void setMessage(String message) {
		this.message = message;
	}
	@Column
	public synchronized String getUsername() {
		return username;
	}
	public synchronized void setUsername(String username) {
		this.username = username;
	}
	@Column
	public synchronized java.sql.Timestamp getPublishedOn() {
		return publishedOn;
	}
	public synchronized void setPublishedOn(java.sql.Timestamp publishedOn) {
		this.publishedOn = publishedOn;
	}
	@Column
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
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
