package com.zjy.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="recommendationtype")
public class RecommendationList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2377397475171641826L;
	
	@Id
	private Integer id;
	
	private String name;
	
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "RecommendationList [id=" + id + ", name=" + name + ", url="
				+ url + "]";
	}
	
	
}
