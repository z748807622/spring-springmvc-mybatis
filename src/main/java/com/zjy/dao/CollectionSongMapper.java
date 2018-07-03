package com.zjy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zjy.entity.CollectionSong;
import com.zjy.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface CollectionSongMapper extends Mapper<CollectionSong> {
	
	
	@Select("select * from collection where username = #{username}")
	public List<CollectionSong> getAllSongByUser(User user);
	
	@Insert("insert into collection(username,filehash,date) values(#{username},#{filehash},NOW())")
	public void insertValue(CollectionSong cs);
	
	@Delete("delete from collection where username = #{username} and filehash = #{filehash}")
	public void del(CollectionSong cs);
	
	@Select("select count(filehash) from collection where username = #{username} and filehash = #{filehash}")
	public Integer hasCollect(CollectionSong cs);
	
	@Select("select * from collection where username = #{param1} limit #{param2,jdbcType=INTEGER},#{param3,jdbcType=INTEGER}")
	public List<CollectionSong> getSong(String username,Integer start,Integer size);
}
