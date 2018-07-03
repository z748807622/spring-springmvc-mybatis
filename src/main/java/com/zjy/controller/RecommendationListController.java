package com.zjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zjy.service.RecommendationListService;

@Controller
@RequestMapping("/")
public class RecommendationListController {
	
	@Autowired
	RecommendationListService recommendationListService;
	
	@RequestMapping(value="/recommendation",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getAllRecommendation(){
		return JSON.toJSONString(recommendationListService.getAllRecommendationList());
	}
}
