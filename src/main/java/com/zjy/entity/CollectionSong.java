package com.zjy.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="collection")
public class CollectionSong implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6523668580507443913L;
	@Id
	private String username;
	private String filehash;
	private Date date;
	public CollectionSong(){}
	
	public CollectionSong(String username, String filehash) {
		super();
		this.username = username;
		this.filehash = filehash;
	}
	public CollectionSong setVelues(String username, String filehash){
		this.username = username;
		this.filehash = filehash;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFilehash() {
		return filehash;
	}
	public void setFilehash(String filehash) {
		this.filehash = filehash;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Collection [username=" + username + ", filehash=" + filehash
				+ ", date=" + date + "]";
	}
	
}
