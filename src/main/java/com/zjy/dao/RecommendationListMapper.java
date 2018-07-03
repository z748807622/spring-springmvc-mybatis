package com.zjy.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.zjy.entity.RecommendationList;

import tk.mybatis.mapper.common.Mapper;

public interface RecommendationListMapper extends Mapper<RecommendationList> {
	
	@Delete("delete from recommendationtype where 1=1")
	public void deleteAll();
	
	@Insert("insert into recommendationtype(id,name,url) values(id,name,url) on duplicate key update name=#{name},url=#{url}")
	public void insertValue(RecommendationList recommendationList);
}
