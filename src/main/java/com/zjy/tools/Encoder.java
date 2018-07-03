package com.zjy.tools;

import java.io.UnsupportedEncodingException;
import java.security.*;

import org.springframework.stereotype.Component;
/**
 * 用来加密的类
 * @author 张劲宇
 *
 */
@Component
public class Encoder {
	/**
	 * 
	 * @param s 要加密的字符串
	 * @return 返回MD5 32位加密的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	 public String EncoderByMd5(String s){
		 try {
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] bytes = md.digest(s.getBytes("utf-8"));
		        return toHex(bytes);
		    }
		    catch (Exception e) {
		        throw new RuntimeException(e);
		    }
	    }
	 private String toHex(byte[] bytes) {

		    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		    StringBuilder ret = new StringBuilder(bytes.length * 2);
		    for (int i=0; i<bytes.length; i++) {
		        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
		        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		    }
		    return ret.toString();
		}
}
