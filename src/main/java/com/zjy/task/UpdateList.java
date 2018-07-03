package com.zjy.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zjy.service.UpdateRecommendationMusicService;

@Component
public class UpdateList {
	
	Logger logger = LoggerFactory.getLogger(UpdateList.class);
	@Autowired
	UpdateRecommendationMusicService updateTop500MusicService;
	public int i = 0;
	//@Scheduled(cron = "0 0 1 * * ?")
	@Scheduled(cron = "0 0 9 * * ?")
	public void updateTop500(){
		logger.info("推荐列表更新任务开始执行");
		//updateTop500MusicService.updateTop500();
		logger.info("推荐列表更新任务执行完成");
		//System.out.println(i++);
	}
}
