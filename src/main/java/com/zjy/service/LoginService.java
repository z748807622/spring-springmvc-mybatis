package com.zjy.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.dao.UserMapper;
import com.zjy.entity.User;
import com.zjy.service.intf.ILoginService;
import com.zjy.tools.Encoder;

@Service
public class LoginService implements ILoginService {

	private Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	Encoder encoder;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 获得随机md5加密的字符串
	 */
	public Map<String,String> getSalt() {
		Map<String, String> m = new HashMap<String, String>();
		String salt =  String.valueOf(new Random().nextInt(1314));
		salt = encoder.EncoderByMd5(String.valueOf(salt));
		//logger.info("salt:{}", salt);
		m.put("salt", salt.toUpperCase());
		return m;
	}
	
	/**
	 * 验证登陆 state的类型有  success、info、warning、danger
	 */
	public Map<String, String> login(String username, String passwd, String salt) {
		Map<String,String> m = new HashMap<String, String>();
		if(salt.length() != 32){
			m.put("state", "danger");
			m.put("info", "登陆出错");
			return m;
		}
		User user = userMapper.selectByPrimaryKey(username);
		if(user == null){
			m.put("state", "danger");
			m.put("info", "登陆出错");
			return m;
		}
		logger.debug(user.toString());
		String realPasswd = encoder.EncoderByMd5(user.getPasswd().toUpperCase()+salt).toUpperCase();
		logger.debug("salt:{} passwd:{} realpasswd:{}",salt,passwd,realPasswd);
		if(realPasswd.equals(passwd.toUpperCase())){
			m.put("state", "success");
			m.put("info", "登陆成功");
			m.put("name", username);
			return m;
		}
		m.put("state", "warning");
		m.put("info", "密码错误");
		return m;
	}

	public void logout(HttpServletRequest req) {
		req.getSession().removeAttribute("username");
	}

}
