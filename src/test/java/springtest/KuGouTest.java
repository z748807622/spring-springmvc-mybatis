package springtest;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zjy.dao.RecommendationListMapper;
import com.zjy.dao.RecommendationMapper;
import com.zjy.dao.SongMapper;
import com.zjy.entity.Recommendation;
import com.zjy.service.CollectionService;
import com.zjy.service.RecommendationListService;
import com.zjy.service.RecommendationService;
import com.zjy.service.intf.ICollectionService;

public class KuGouTest extends BaseSpringUnitTest {
	
	@Autowired
	RecommendationListService recommendationListService;
	@Autowired
	RecommendationListMapper recommendationListMapper;
	
	@Autowired
	RecommendationService recommendationService;
	
	@Autowired
	RecommendationMapper recommendationMapper;
	
	@Autowired
	SongMapper songMapper;
	
	@Autowired
	ICollectionService collectionService;
	
	@Test
	public void re(){
		//recommendationListService.updateRecommendationList();
		//recommendationListService.updateAllSongs();
		System.out.println(collectionService.collectOrCancel("", "123"));
	}
	
	@Test
	public void stringTest(){
		String s = "http://www2.kugou.kugou.com/yueku/v9/rank/home/1-6666.html";
		String ss = s.replaceAll("\\d(?=-\\d{4})", "2");
		System.out.println(ss);
	}
	
	@Test
	public void serviceTest(){
		System.out.println(JSON.toJSONString(recommendationListService.getAllRecommendationList()));
		System.out.println(JSON.toJSONString(recommendationListMapper.selectAll()));
	}
	
	@Test
	public void musicTest(){
		//System.out.println(recommendationService.getReMusic(null, null, null));
//		HashMap<String, Integer> m = new HashMap<String, Integer>();
//		m.put("start", 1);
//		m.put("end", 3);
//		m.put("type", 1);
//		List<Recommendation> list = recommendationMapper.getSong(m);
		System.out.println(songMapper.selectByPrimaryKey("0802000ADC0B29639A738F9B06335C13"));
	}
}
