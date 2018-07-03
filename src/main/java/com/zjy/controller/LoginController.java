package com.zjy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zjy.service.intf.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	ILoginService loginService;
	
	/**
	 * 登陆之前获取salt
	 * @param req
	 * @return 返回一个随机字符串md 32位加密的{salt:xxx}
	 */
	@RequestMapping(value="/salt",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getSalt(HttpServletRequest req){
		Map<String, String> salt  = loginService.getSalt();
		req.getSession().setAttribute("salt", salt.get("salt"));
		return JSON.toJSONString(salt);
	}
	/**
	 * 登陆验证
	 * @param req
	 * @param username
	 * @param passwd
	 * @return 返回登陆结果
	 */
	@RequestMapping(value="/in",produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest req, String username, String passwd){
		if(req.getSession().getAttribute("username") != null){
			Map<String,String> mm = new HashMap<String, String>();
			mm.put("state", "success");
			mm.put("info", "登陆成功");
			mm.put("name", req.getSession().getAttribute("username").toString());
			return JSON.toJSONString(mm);
		}
		String salt = String.valueOf(req.getSession().getAttribute("salt"));
		Map<String, String> m = loginService.login(username, passwd, salt);
		if(m.get("state").equals("success"))
			req.getSession().setAttribute("username", username);
		return JSON.toJSONString(m);
	}
	@RequestMapping(value="/out",produces="application/json;charset=utf-8")
	@ResponseBody
	public void logout(HttpServletRequest req){
		loginService.logout(req);
	}
	
}
