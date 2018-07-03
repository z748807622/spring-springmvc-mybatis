package com.zjy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zjy.entity.Song;
import com.zjy.service.KuGouService;
import com.zjy.service.RecommendationService;
import com.zjy.service.UpdateRecommendationMusicService;
import com.zjy.service.VisitorService;
import com.zjy.tools.CharTool;

@Controller
@RequestMapping("music")
public class MusicController {
	@Autowired
	private KuGouService kuGouService;
	
	@Autowired
	private UpdateRecommendationMusicService updateTop500MusicService;
	
	@Autowired
	private RecommendationService recommendationService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	CharTool charTool;
	
	/**
	 * 获得top500
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/index",produces="application/json")
	@ResponseBody
	public String getIndex(String page,String size,HttpServletRequest req){
		visitorService.insertVisitor(req);
		page = page==null?"1":page;
		size = size==null?"24":size;
		System.out.println(page+" "+size);
		if(!charTool.isInteger(page) || !charTool.isInteger(size))
			return "{'state':'error'}";
		return JSON.toJSONString(kuGouService.getIndex(page, size));
	}
	/**
	 * 所有推荐音乐
	 * @param page
	 * @param size
	 * @param date
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/recommendation/{date}",produces="application/json")
	@ResponseBody
	public String getMM(String page,String size,@PathVariable String date,HttpServletRequest req){
		visitorService.insertVisitor(req);
		String name = String.valueOf(req.getSession().getAttribute("username"));
		page = page==null?"1":page;
		size = size==null?"24":size;
		date = size==null?"2":date;
		System.out.println(page+" "+size);
		if(!charTool.isInteger(page) || !charTool.isInteger(size) || !charTool.isInteger(date))
			return "{'state':'error'}";
		return JSON.toJSONString(recommendationService.getReMusic(page, size, date, name));
	}
	/**
	 * 查找歌曲
	 * @param songName
	 * @return
	 */
	@RequestMapping(value="/search",produces="application/json")
	@ResponseBody
	public String getMusic(String songName,String page,String size,HttpServletRequest req){
		visitorService.insertVisitor(req);
		String name = String.valueOf(req.getSession().getAttribute("username"));
		page = page==null?"1":page;
		size = size==null?"21":size;
		if(!charTool.isInteger(page) || !charTool.isInteger(size))
			return "{'state':'error'}";
		return JSON.toJSONString(kuGouService.getIDs(songName, page, size, name));
	}
	
	/**
	 * 通过filehash获得Song
	 * @param fileHash
	 * @return Song
	 */
	@RequestMapping(value="/geturl",produces="application/json")
	@ResponseBody
	public String getUrlByHash(String fileHash){
		Song s = kuGouService.getSongByHash(fileHash);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("info", "播放" + s.getSongName());
		map.put("state", "获得歌曲");
		map.put("data", s);
		return JSON.toJSONString(map);
	}
	/**
	 * 更新top500
	 * @param passwd
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String updateTop500(String passwd){
		if("z7488".equals(passwd)){
			//updateTop500MusicService.updateTop500();
			return "{'state':'success'}";
		}
		return "{'state':'faile'}";
	}
}
