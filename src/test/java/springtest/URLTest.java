package springtest;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.zjy.tools.CharTool;
import com.zjy.tools.KuGouMusic;
import com.zjy.tools.NetMethod;

public class URLTest {

	@Test
	public void getUrl(){
		String url = "http://musicmini.baidu.com/resources/plugins/suggest/temp.php?qword=" + "123";
		String url2 = "http://www.kugou.com/yy/index.php?r=play/getdata&hash=" + "3C3D93A5615FB42486CAB22024945264";
		String re = NetMethod.get(url2);
		System.out.println(re);
		JSONObject json = JSONObject.parseObject(re).getJSONObject("data");
		String songname = json.getString("play_url");
		System.out.println(songname);
		String regx = "(?<=\"img\":\").*?(?=\")";
		Pattern r = Pattern.compile(regx);
		Matcher m = r.matcher(re);
		while(m.find()){
			System.out.println(m.group());
		}
	}
	@Test
	public void getID(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/spring.xml");
		KuGouMusic music = new KuGouMusic();
		List<Map<String,String>> result = music.getID("告白气球", 1, 2);
		System.out.println(result);
		//System.out.println(music.getSongByFileHash("A1FAD4431389072D074E4B4E88C95A2B"));
		//System.out.println(songName.size() + " " + singerName.size() + " "+filehash.size()+" "+sqFileHash.size() +" " + hqFileHash.size()+" "+mvHash.size());
	}
	@Test
	public void charSet(){
		CharTool t = new CharTool();
		String str = "[00:00.28]\u65b0\u4e50\u5c18\u7b26 - 123\u6211\u7231\u4f60\r\n[00:00.89]\u8bcd\uff1a\u5b5f\u541b\u9171\r";
		System.out.println(t.unicodeTransToUTF8(str));
	}
}
