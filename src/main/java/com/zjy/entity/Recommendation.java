package com.zjy.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="recommendation")
public class Recommendation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3573591518347459844L;
	
	@Id
	private String filehash;
	private Integer type;
	private Integer rank;
	private Date time;
	public Recommendation(){}
	
	public Recommendation(String filehash, Integer type, Integer rank) {
		super();
		this.filehash = filehash;
		this.type = type;
		this.rank = rank;
	}

	public String getFilehash() {
		return filehash;
	}
	public void setFilehash(String filehash) {
		this.filehash = filehash;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "recommendation [filehash=" + filehash + ", type=" + type + "]";
	}
	

}
