package com.zjy.service;

import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.KuGouTop500Mapper;
import com.zjy.dao.SongMapper;
import com.zjy.dao.RecommendationMapper;
import com.zjy.entity.KuGouTop500;
import com.zjy.entity.Recommendation;
import com.zjy.entity.Song;
import com.zjy.tools.JsonTools;
import com.zjy.tools.KuGouMusic;
import com.zjy.tools.NetMethod;

@Service
public class UpdateRecommendationMusicService {

	//获取top500的url
	private String kugouTop500  = "http://www2.kugou.kugou.com/yueku/v9/rank/home/{page}-8888.html";
	
	@Autowired
	RecommendationMapper recommendationMapper;
	
	@Autowired
	private KuGouMusic kuGouMusic;
	
	@Autowired
	private SongMapper songMapper;
	
	@Autowired
	private KuGouTop500Mapper kuGouTop500Mapper;
	
	Logger logger = LoggerFactory.getLogger(UpdateRecommendationMusicService.class);
	/**
	 * 更新酷狗top500                 已废弃
	 */
	/*public void updateTop500(String url){
		logger.info("top500列表开始更新");
		int j=1;
		KuGouTop500 k;
		for(int i=1; i<18; i++){
			logger.info("-----------第{}页---------------",i);
			url = url.replace("{page}", String.valueOf(i));
			String result = NetMethod.get(url);
			List<String> fileHashList = JsonTools.getValues("hash", result);
			for(String fileHash:fileHashList){
				//k = kuGouMusic.getSongClassByFileHash(fileHash);
				//k.setRank(j);
				//kuGouTop500Mapper.updateOrInsert(k);
				//j++;
			}
		}
	}*/
	/**
	 * 更新所有推荐歌曲
	 * @param url
	 * @param type
	 */
	public void updateAll(String url,Integer type){
		logger.info("top500列表开始更新");
		int j=1;
		Song k;
		for(int i=1; i<20; i++){
			logger.info("-----------第{}页---------------",i);
			String ss = url.replaceAll("\\d(?=-\\d{4})", String.valueOf(i));
			//System.out.println(url);
			String result = NetMethod.get(ss);
			if(result == null)
				continue;
			List<String> fileHashList = JsonTools.getValues("hash", result);
			if(fileHashList.isEmpty())
				break;
			for(String fileHash:fileHashList){
				k = kuGouMusic.getSongClassByFileHash(fileHash);
				if(k == null)
					continue;
				recommendationMapper.insertvalue(new Recommendation(fileHash,type,j));
				songMapper.insertOrCancel(k);
				j++;
			}
		}
	}
}
