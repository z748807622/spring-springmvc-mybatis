package springtest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zjy.dao.KuGouTop500Mapper;
import com.zjy.dao.UserMapper;
import com.zjy.dao.VisitorMapper;
import com.zjy.entity.Visitor;
import com.zjy.service.KuGouService;
import com.zjy.service.RecommendationListService;


public class mainTest {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/spring.xml");
		TestService testService = ctx.getBean(TestService.class);
		System.out.println(testService.getName());
	}
*/
	ClassPathXmlApplicationContext ctx = null;
	UserMapper userMapper = null;
	Logger logger = LoggerFactory.getLogger(mainTest.class);
	
	@Before
	public void init(){
		logger.info("----mainTest init() is running-----");
		ctx = new ClassPathXmlApplicationContext("classpath*:/spring.xml,classpath*:/spring-mybatis.xml");
	}
	@Test
	public void getName(){
		logger.info("----mainTest getName() is running-----");
	}
	@Test
	public void getDbAll(){
		logger.info("----mainTest getDbAll() is running-----");
		
		userMapper = ctx.getBean(UserMapper.class);
		System.out.println(userMapper.selectAll());
	}
	@Test
	public void top500(){
		KuGouTop500Mapper kuGouTop500Mapper = ctx.getBean(KuGouTop500Mapper.class);
		Map<String,Integer> m = new HashMap<String, Integer>();
		m.put("start", 1);
		m.put("end", 3);
		System.out.println(JSON.toJSONString(kuGouTop500Mapper.getSong(m)));
	}
	@Test
	public void kuMusic(){
		KuGouService ku = ctx.getBean(KuGouService.class);
		
		//logger.info(JSON.toJSONString(ku.getIDs("双笙", "1", "3")));
	}
	
	@Test
	public void visitorTest(){
		VisitorMapper vm = ctx.getBean(VisitorMapper.class);
		Visitor v = new Visitor();
		v.setIp("192.168.1.1");
		vm.updateOrInsert(v);
	}
	
	@Test
	public void getRecommedationUrl(){
		RecommendationListService r = ctx.getBean(RecommendationListService.class);
		//System.out.println(r.getUrl());
	}
}
