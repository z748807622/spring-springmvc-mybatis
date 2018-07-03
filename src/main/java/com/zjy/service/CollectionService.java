package com.zjy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.CollectionSongMapper;
import com.zjy.dao.SongMapper;
import com.zjy.entity.CollectionSong;
import com.zjy.entity.Song;
import com.zjy.entity.User;
import com.zjy.service.intf.ICollectionService;
@Service
public class CollectionService implements ICollectionService {
	
	@Autowired
	CollectionSongMapper collectionSongMapper;
	
	@Autowired
	KuGouService kuGouService;
	
	@Autowired
	SongMapper songMapper;
	
	public Map<String, String> collectOrCancel(String username, String filehash) {
		Map<String,String> m = new HashMap<String, String>();
		CollectionSong cs = new CollectionSong(username,filehash);
		if(collectionSongMapper.hasCollect(cs)<=0){
			Song s = kuGouService.getSongByHash(filehash);
			//System.out.println(s);
			if(s == null || "".equals(s.getUrl())){
				m.put("success", "false");
				m.put("state", "");
				m.put("info", "歌曲不存在");
				return m;
			}
			collectionSongMapper.insertValue(cs);
			m.put("success", "true");
			m.put("state", "collect");
			m.put("info", "收藏成功");
			return m;
		}else{
			collectionSongMapper.del(cs);
			m.put("success", "true");
			m.put("state", "cancel");
			m.put("info", "取消成功");
			return m;
		}
	}

	public Map<String, Object> getAllCollectionByName(String username, String spage, String ssize) {
		int page = spage==null?1:Integer.valueOf(spage);
		int size = ssize==null?24:Integer.valueOf(ssize);
		/*Map<String,Object> mm = new HashMap<String, Object>();
		mm.put("username", username);
		mm.put("start", String.valueOf(page * size - size));
		mm.put("size", String.valueOf(size));*/
		List<CollectionSong> l = collectionSongMapper.getSong(username,page * size - size,size);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("success", "true");
		map.put("info", "用户所有收藏");
		map.put("state", l.isEmpty()?"end":"");
		for(CollectionSong c:l){
			Map<String,Object> m =new HashMap<String, Object>();
			m.put("song", songMapper.selectByPrimaryKey(c.getFilehash()));
			m.put("hascollect", "true");
			list.add(m);
		}
		map.put("data", list);
		return map;
	}
	
}
