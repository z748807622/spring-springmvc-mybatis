package com.zjy.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zjy.entity.Song;
@Component
public class KuGouMusic {
	
	@Autowired
	CharTool charTool;
	
	//通过关键值字酷狗查找歌曲id的链接
	private static String chazhaoID = "http://songsearch.kugou.com/song_search_v2?keyword={mingzi}&page={dijiye}&pagesize={yiyedaxiao}&userid=-1&clientver=&platform=WebFilter&tag=em&filter=2&iscorrection=1&privilege_filter=0&_=1528787612124";

	//通过id查找歌曲url
	private static String chazhaoURL = "http://www.kugou.com/yy/index.php?r=play/getdata&hash=";
	
	private Logger logger = LoggerFactory.getLogger(KuGouMusic.class);
	/**
	 * 返回歌曲list
	 * @param name
	 * @param page
	 * @param size
	 */
	public List<Map<String, String>> getID(String name, int page, int size){
		logger.info("搜索：{},page: {}",name,page);
		String url = getIDUrl(name, page, size);
		List<Map<String,String>> songs = new ArrayList<Map<String,String>>();
		Map<String,String> song;
		String result = NetMethod.get(url).replace("\\/", "/");
		List<String> filehash = JsonTools.getValues("FileHash", result);
		Set<String> filehashSet = new HashSet<String>(filehash);
		Iterator<String> i = filehashSet.iterator();
		while(i.hasNext()){
			song = new HashMap<String, String>();
			song.putAll(getSongByFileHash(i.next()));
			songs.add(song);
		}
		return songs;
	}
	/**
	 * 通过fileHash获得歌曲详情信息
	 * @param fileHash
	 * @return 返回一个map
	 */
	public Map<String,String> getSongByFileHash(String fileHash){
		Map<String,String> m = new HashMap<String, String>();
		String url = chazhaoURL + fileHash;
		String result = NetMethod.get(url).replace("\\/", "/");
		//System.out.println(result);
		m.put("fileHash", fileHash);
		m.put("songName", charTool.unicodeTransToUTF8(JsonTools.getValue("audio_name", result)));
		m.put("singerName", charTool.unicodeTransToUTF8(JsonTools.getValue("author_name", result)));
		m.put("img", JsonTools.getValue("img", result));
		m.put("lyrics", charTool.unicodeTransToUTF8(JsonTools.getValue("lyrics", result).replace("//", "/")));
		m.put("url", JsonTools.getValue("play_url", result));
		return m;
	}
	
	public Song getSongClassByFileHash(String fileHash){
		//Map<String,String> m = new HashMap<String, String>();
		if(fileHash == null)
			return null;
		String url = chazhaoURL + fileHash;
		String resultt = NetMethod.get(url);
		if(resultt == null && "".equals(resultt))
			return null;
		String result = resultt.replace("\\/", "/");
		//System.out.println(result);
		Song s = new Song(
					fileHash,
					charTool.unicodeTransToUTF8(JsonTools.getValue("audio_name", result)),
					JsonTools.getValue("img", result),
					charTool.unicodeTransToUTF8(JsonTools.getValue("lyrics", result).replace("//", "/")),
					charTool.unicodeTransToUTF8(JsonTools.getValue("author_name", result)),
					JsonTools.getValue("play_url", result),
					""
				);
		//m.put("filehash", fileHash);
		//m.put("songName", charTool.unicodeTransToUTF8(JsonTools.getValue("audio_name", result)));
		//m.put("singerName", charTool.unicodeTransToUTF8(JsonTools.getValue("author_name", result)));
		//m.put("img", JsonTools.getValue("img", result));
		//m.put("lyrics", charTool.unicodeTransToUTF8(JsonTools.getValue("lyrics", result).replace("//", "/")));
		//m.put("url", JsonTools.getValue("play_url", result));
		return s;
	}
	/**
	 * 拼接查找的url
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	private String getIDUrl(String name, int page, int size){
		return chazhaoID.replace("{mingzi}", name).replace("{dijiye}", String.valueOf(page)).replace("{yiyedaxiao}", String.valueOf(size));
		
	}
}
