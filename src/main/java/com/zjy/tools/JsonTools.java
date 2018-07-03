package com.zjy.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTools {
	private static String str = "(?<=\"{key}\":\").*?(?=\")";
	
	public static List<String> getValues(String name, String json){
		String re = str.replace("{key}", name);
		Pattern p = Pattern.compile(re);
		Matcher m = p.matcher(json);
		List<String> list = new ArrayList<String>();
		while(m.find()){
			list.add(m.group());
		}
		return list;
	}
	public static String getValue(String name, String json){
		String re = str.replace("{key}", name);
		Pattern p = Pattern.compile(re);
		Matcher m = p.matcher(json);
		if(m.find()){
			return m.group();
		}
		return "";
	}
	public static List<String> getDIYValues(String regx, String json){
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(json);
		List<String> list = new ArrayList<String>();
		while(m.find()){
			list.add(m.group());
		}
		return list;
	}
	public static String getDIYValue(String regx, String json){
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(json);
		if(m.find()){
			return m.group();
		}
		return "";
	}
}
