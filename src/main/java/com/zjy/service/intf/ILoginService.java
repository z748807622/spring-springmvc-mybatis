package com.zjy.service.intf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ILoginService {
	public Map<String,String> getSalt();
	public Map<String,String> login(String username, String passwd, String salt);
	public void logout(HttpServletRequest req);
}
