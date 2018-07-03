package com.zjy.dao;

import org.apache.ibatis.annotations.Insert;

import com.zjy.entity.Song;

import tk.mybatis.mapper.common.Mapper;

public interface SongMapper extends Mapper<Song> {
	
	@Insert("insert ignore into songinfo(fileHash,songName,img,singerName,url,lyrics,MvHash) values(#{fileHash},#{songName},#{img},#{singerName},#{url},#{lyrics},#{mvHash})")
	public void insertOrCancel(Song song);
	
}
