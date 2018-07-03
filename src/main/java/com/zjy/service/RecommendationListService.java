package com.zjy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.RecommendationListMapper;
import com.zjy.dao.RecommendationMapper;
import com.zjy.entity.RecommendationList;
import com.zjy.entity.Song;
import com.zjy.tools.JsonTools;
import com.zjy.tools.KuGouMusic;
import com.zjy.tools.NetMethod;

@Service
public class RecommendationListService {
	
	private String indexUrl = "http://www2.kugou.kugou.com/yueku/v9/rank/home/1-6666.html";
	
	@Autowired
	RecommendationListMapper recommendationListMapper;
	
	@Autowired
	UpdateRecommendationMusicService updateRecommendationMusicService;
	
	
	@Autowired
	KuGouMusic kuGouMusic;

	@Autowired
	private RecommendationMapper recommendationMapper;
	
	
	/**
	 * 获取所有的推荐分类
	 * @return
	 */
	public Map<String,Object> getAllRecommendationList(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<RecommendationList> list = recommendationListMapper.selectAll();
		List<Map<String,String>> l = new ArrayList<Map<String,String>>();
		Iterator<RecommendationList> i = list.iterator();
		RecommendationList r;
		map.put("success", "true");
		map.put("info", "获得所有的推荐分类");
		map.put("state", l.size());
		while(i.hasNext()){
			Map<String,String> m = new HashMap<String,String>();
			r = i.next();
			m.put("id", String.valueOf(r.getId()));
			m.put("name", r.getName());
			l.add(m);
		}
		map.put("data", l);
		return map;
	}
	
	
	/**
	 * 获得推荐分类信息
	 */
	public void updateRecommendationList(){
		String context = NetMethod.get(indexUrl);
		List<String> main  = JsonTools.getDIYValues("<a title=\".*?\".*?href=\".*?\">", context);
		Iterator<String> i = main.iterator();
		Integer index=1;
		recommendationListMapper.deleteAll();
		while(i.hasNext()){
			RecommendationList r = new RecommendationList();
			String s = i.next();
			r.setName(JsonTools.getDIYValue("(?<=title=\").*?(?=\")", s));
			if("酷音乐排行榜".equals(r.getName()))
				continue;
			r.setUrl("http://www2.kugou.kugou.com"+JsonTools.getDIYValue("(?<=href=\").*?(?=\")", s));
			r.setId(index++);
			recommendationListMapper.insert(r);
		}
	}
	/**
	 * 更新推荐列表
	 */
	public void updateAllSongs(){
		List<RecommendationList> l = recommendationListMapper.selectAll();
		Iterator<RecommendationList> i = l.iterator();
		recommendationMapper.deleteAll();
		while(i.hasNext()){
			RecommendationList r = i.next();
			updateRecommendationMusicService.updateAll(r.getUrl(), r.getId());
		}
	}
}
