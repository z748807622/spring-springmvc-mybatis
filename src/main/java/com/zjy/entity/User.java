package com.zjy.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3387816970725324159L;
	
	@Column(name="username")
	@Id
	private String username;
	
	@Column(name="passwd")
	private String passwd;
	
	@Column(name="email")
	private String email;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="zhuceriqi")
	private Date zhuceriqi;

	public User(){}
	
	public User(String username) {
		super();
		this.username = username;
	}

	public Date getZhuceriqi() {
		return zhuceriqi;
	}
	public void setZhuceriqi(Date zhuceriqi) {
		this.zhuceriqi = zhuceriqi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", passwd=" + passwd + "]";
	}
	
}
