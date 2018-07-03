package com.zjy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zjy.service.intf.ICollectionService;

@Controller
@RequestMapping("/collect")
public class CollectionController {
	
	@Autowired
	ICollectionService collectionService;
	
	/**
	 * 收藏或者取消收藏
	 * @param filehash
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/m/{filehash}",produces="application/json;charset=utf-8")
	@ResponseBody
	public String collectSong(@PathVariable String filehash,HttpServletRequest req){
		String username = String.valueOf(req.getSession().getAttribute("username"));
		return JSON.toJSONString(collectionService.collectOrCancel(username, filehash));
	}
	
	/**
	 * 获得登陆用户的所有收藏歌曲
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/all",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getAllCollection(String page,String size,HttpServletRequest req){
		String username = String.valueOf(req.getSession().getAttribute("username"));
		return JSON.toJSONString(collectionService.getAllCollectionByName(username,page, size));
	}
}
