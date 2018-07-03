package com.zjy.dao;

import org.apache.ibatis.annotations.Insert;

import com.zjy.entity.Visitor;

import tk.mybatis.mapper.common.Mapper;

public interface VisitorMapper extends Mapper<Visitor> {
	@Insert("insert into visitor(ip,date,times) values(#{ip},NOW(),1) on duplicate key update date=NOW(),times=times+1")
	public void updateOrInsert(Visitor visitor);
}
