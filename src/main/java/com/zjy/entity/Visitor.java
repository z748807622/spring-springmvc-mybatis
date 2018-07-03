package com.zjy.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="visitor")
public class Visitor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3777219538471131995L;

	@Id
	private String ip;
	private int times;
	private Date date;
	public String getIp() {
		return ip;
	}
	public Visitor(){}
	public Visitor(String ip){
		this.ip = ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
