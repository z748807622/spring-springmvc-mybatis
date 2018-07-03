package com.zjy.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.VisitorMapper;
import com.zjy.entity.Visitor;

@Service
public class VisitorService {
	Logger logger = LoggerFactory.getLogger(VisitorService.class);
	@Autowired
	private VisitorMapper visitorMapper;
	
	public void insertVisitor(HttpServletRequest req){
		String ip = getIpAddr(req);
		logger.info("{}：来访问",ip);
		visitorMapper.updateOrInsert(new Visitor(ip));
	}
	private String getIpAddr(HttpServletRequest req) {  
	    String ip = req.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = req.getRemoteAddr();  
	    }  
	    return ip;  
	}  
}
