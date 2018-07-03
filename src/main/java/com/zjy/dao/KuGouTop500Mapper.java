package com.zjy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zjy.entity.KuGouTop500;

import tk.mybatis.mapper.common.Mapper;

public interface KuGouTop500Mapper extends Mapper<KuGouTop500> {
	
	@Insert("insert into kuTop500(rank,fileHash,songName,img,singerName,url,lyrics) values(#{rank},#{fileHash},#{songName},#{img},#{singerName},#{url},#{lyrics}) on duplicate key update fileHash=#{fileHash},songName=#{songName},img=#{img},singerName=#{singerName},url=#{url},lyrics=#{lyrics}")
	public void updateOrInsert(KuGouTop500 kuGouTop500);
	
	@Select("select * from kuTop500 where rank between #{start} and #{end}")
	public List<KuGouTop500> getSong(Map<String,Integer> m);
	
}
