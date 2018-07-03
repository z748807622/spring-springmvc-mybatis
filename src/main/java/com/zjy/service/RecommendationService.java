package com.zjy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.CollectionSongMapper;
import com.zjy.dao.RecommendationMapper;
import com.zjy.dao.SongMapper;
import com.zjy.entity.CollectionSong;
import com.zjy.entity.KuGouTop500;
import com.zjy.entity.Recommendation;
import com.zjy.entity.Song;

@Service
public class RecommendationService {
	
	Logger logger = LoggerFactory.getLogger(RecommendationService.class);
	@Autowired
	RecommendationMapper recommendationMapper;
	@Autowired
	private SongMapper songMapper;
	
	@Autowired
	private CollectionSongMapper collectionSongMapper;
	/**
	 * 获得推荐的音乐
	 * @param spage
	 * @param ssize
	 * @param stype
	 * @param username
	 * @return
	 */
	public Map<String,Object> getReMusic(String spage,String ssize,String stype,String username){
		//System.out.println("username:"+username);
		int page = spage==null?1:Integer.valueOf(spage);
		int size = ssize==null?20:Integer.valueOf(ssize);
		int type = ssize==null?2:Integer.valueOf(stype);
		Map<String,Integer> m = new HashMap<String, Integer>();
		m.put("start", (page-1)*size + 1);
		m.put("end", size * page);
		m.put("type", type);
		Map<String,Object> map = new HashMap<String, Object>();
		List<Recommendation> list = recommendationMapper.getSong(m);
		map.put("success", "true");
		map.put("info", "获得推荐音乐");
		map.put("state", list.isEmpty()?"end":"");
		List<Map<String,Object>> songList = new ArrayList<Map<String,Object>>();
		Iterator<Recommendation> i = list.iterator();
		Song s;
		CollectionSong cs = new CollectionSong();
		while(i.hasNext()){
			Map<String,Object> ms = new HashMap<String, Object>();
			s = songMapper.selectByPrimaryKey(i.next().getFilehash());
			ms.put("song", s);
			ms.put("hascollect", collectionSongMapper.hasCollect(cs.setVelues(username, s.getFileHash())));
			songList.add(ms);
		}
		map.put("data", songList);
		return map;
		
	}
}
