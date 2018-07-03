package com.zjy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zjy.entity.KuGouTop500;
import com.zjy.entity.Recommendation;

import tk.mybatis.mapper.common.Mapper;

public interface RecommendationMapper extends Mapper<Recommendation> {
	
	@Delete("delete from recommendation where 1=1")
	public void deleteAll();
	
	@Insert("insert ignore into recommendation(filehash,type,rank,time) values(#{filehash},#{type},#{rank},NOW())")
	public void insertvalue(Recommendation recommendation);
	
	@Select("select * from recommendation where type = #{type} and rank between #{start} and #{end}")
	public List<Recommendation> getSong(Map<String,Integer> m);
}
