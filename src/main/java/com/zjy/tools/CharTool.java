package com.zjy.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CharTool {
	
	public String unicodeTransToUTF8(String str){
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");  
	    Matcher matcher = pattern.matcher(str);  
	    char ch;  
	    while (matcher.find()) {  
	        //group 6728  
	        String group = matcher.group(2);  
	        //ch:'木' 26408  
	        ch = (char) Integer.parseInt(group, 16);  
	        //group1 \u6728  
	        String group1 = matcher.group(1);  
	        str = str.replace(group1, ch + "");  
	    }  
	    return str;
	}
	public boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
  }
}
