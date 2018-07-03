package com.zjy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.CollectionSongMapper;
import com.zjy.dao.KuGouTop500Mapper;
import com.zjy.dao.RecommendationMapper;
import com.zjy.entity.CollectionSong;
import com.zjy.entity.KuGouTop500;
import com.zjy.entity.Song;
import com.zjy.tools.CharTool;
import com.zjy.tools.KuGouMusic;

@Service
public class KuGouService {
	
	@Autowired
	private KuGouMusic kuGouMusic;
	
	@Autowired
	private KuGouTop500Mapper kuGouTop500Mapper;
	
	@Autowired
	private RecommendationMapper recommendationMapper;
	
	@Autowired
	private CollectionSongMapper collectionSongMapper;
	
	/**
	 * 通过关键值查找资源
	 * @param name
	 * @param page
	 * @param size
	 * @return 
	 */
	public Map<String,Object> getIDs(String name, String spage, String ssize,String username){
		//System.out.println("username:"+username);
		int page = spage==null?1:Integer.valueOf(spage);
		int size = ssize==null?21:Integer.valueOf(ssize);
		String info = "第" + page + "页";
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		List<Map<String,String>> list = kuGouMusic.getID(name, page, size);
		if(list.isEmpty())
			info = "没有了";
		CollectionSong cs = new CollectionSong();
		map.put("success", "true");
		map.put("info", info);
		map.put("state", "查找"+name);
		for(Map<String,String> m:list){
			Map<String,Object> mm = new HashMap<String, Object>();
			mm.put("song", m);
			mm.put("hascollect", collectionSongMapper.hasCollect(cs.setVelues(username, m.get("fileHash"))));
			l.add(mm);
		}
		map.put("data", l);
		return map;
	}
	/**
	 * 返回top500
	 * @param spage
	 * @param ssize
	 * @return
	 */
	public Map<String,Object> getIndex(String spage,String ssize){
		
		int page = spage==null?1:Integer.valueOf(spage);
		int size = ssize==null?20:Integer.valueOf(ssize);
		Map<String,Integer> m = new HashMap<String, Integer>();
		m.put("start", (page-1)*size + 1);
		m.put("end", size * page);
		Map<String,Object> map = new HashMap<String, Object>();
		List<KuGouTop500> list = kuGouTop500Mapper.getSong(m);
		map.put("success", "true");
		map.put("info", "getkutou500");
		map.put("state", list.isEmpty()?"end":"");
		map.put("data", list);
		return map;
	}
	/**
	 * 通多filehash获得Song
	 * @param fileHash
	 * @return Song
	 */
	public Song getSongByHash(String fileHash){
		return kuGouMusic.getSongClassByFileHash(fileHash);
	}
}
