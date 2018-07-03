package com.zjy.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CollectionAOP {
	
	@Pointcut("execution(* com.zjy.service.intf.ICollectionService.*(..))")
	public void isLogin(){}
	/**
	 * 判断操作收藏时，用户是否登陆
	 * @param pjp
	 * @return
	 */
	@Around("isLogin()")
	public Object after(ProceedingJoinPoint pjp){
		Object[] o = pjp.getArgs();
		Map<String,String> m = new HashMap<String, String>();
		//System.out.println("o:" + o[0]);
		if(o[0] == null || "".equals(o[0]) || "null".equals(o[0]))
		{
			m.put("success", "false");
			m.put("info", "未登录");
			m.put("state", "login");
			return m;
		}
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			m.put("success", "false");
			m.put("info", "不知道发生了啥😂");
			m.put("state", "未知错误");
			return m;
		}
	}
	
}
