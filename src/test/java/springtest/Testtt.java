package springtest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zjy.dao.KuGouTop500Mapper;
import com.zjy.service.RecommendationListService;
import com.zjy.service.UpdateRecommendationMusicService;
import com.zjy.tools.CharTool;
import com.zjy.tools.JsonTools;
import com.zjy.tools.KuGouMusic;
import com.zjy.tools.MyHtmlCompress;
import com.zjy.tools.NetMethod;

public class Testtt {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/spring.xml");
		/*int i=0;
		while(true){
			System.out.println(i++);
			Thread.sleep(3000);
		}*/
		/*MyHtmlCompress c = new MyHtmlCompress();
		String s = NetMethod.get("http://www2.kugou.kugou.com/yueku/v9/rank/home/1-8888.html");
		System.out.println(JsonTools.getValues("hash", s));
		System.out.println(JsonTools.getDIYValues("(?<=\"id\":).*?(?=,)", s));*/
		//System.out.println(c.htmlCompress(s));
		/*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/spring.xml");
		UpdateTop500MusicService updateTop500MusicService = ctx.getBean(UpdateTop500MusicService.class);
		updateTop500MusicService.updateTop500();*/
		KuGouTop500Mapper r = ctx.getBean(KuGouTop500Mapper.class);
		System.out.println(r.selectByPrimaryKey(1));
	}

}
