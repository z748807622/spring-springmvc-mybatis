package com.zjy.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 发送get，post等请求的工具类
 * @author 张劲宇
 *
 */

public class NetMethod {
	
	private static Logger logger = LoggerFactory.getLogger(NetMethod.class);
	
	/** 
     * 向指定URL发送GET方法的请求 
     *  
     */  
    public static String get(String url) {  
        BufferedReader in = null;  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection connection = realUrl.openConnection();  
            // 设置通用的请求属性  
            connection.setRequestProperty("accept", "*/*");  
            connection.setRequestProperty("connection", "Keep-Alive");  
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            connection.setConnectTimeout(5000);  
            connection.setReadTimeout(5000);  
            // 建立实际的连接  
            connection.connect();  
            // 定义 BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
            StringBuffer sb = new StringBuffer();  
            String line;  
            while ((line = in.readLine()) != null) {  
                sb.append(line);  
            }  
            return sb.toString();  
        } catch (Exception e) {  
            //LOG.error("Exception occur when send http get request!", e);  
            logger.error("Exception occur when send http get request! {}",e);
            return null;
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace(); 
                return null;
            }  
        }  
    }  
}
