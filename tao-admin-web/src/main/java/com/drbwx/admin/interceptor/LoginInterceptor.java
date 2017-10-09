package com.drbwx.admin.interceptor;


import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.CurrentOper;
import com.google.gson.Gson;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
/*	@Autowired
	private RedisService redisService;*/
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		if(url.indexOf("login")>-1||url.indexOf("logout")>-1){ //涓嶆嫤鎴殑璇锋眰
			return true;
		}
		
/*		String sessionId = getCookieValue(request,Constant.COOKIE_SESSION_ID);
		if(StringUtils.isEmpty(sessionId)){
			gotologinPage(request,response);
			
			return false;
		}*/
		CurrentOper Oper = (CurrentOper)getCurrentOper(request,"");
		if(Oper==null){
			gotologinPage(request,response);
			
			return false;
		}
		

		
		return true;
	}
	
	private CurrentOper getCurrentOper(HttpServletRequest request,String key){
		//CurrentOper Oper = (CurrentOper)cacheService.getGlobalCache(sessionId);*/
		CurrentOper Oper = (CurrentOper)request.getSession().getAttribute("cuser");
		//CurrentOper Oper = (CurrentOper)redisService.getValue(key);
		
		return Oper;
	}
	
	private void gotologinPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String loginPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/login.html";
		
		String turnsrcipt = "<script language=javascript>"
		+"this.top.location.href ='"+loginPath+"';"
		+"</script>";
		
		if(isAjaxRequst(request)){
			AjaxResult ajaxResult = new AjaxResult();
			ajaxResult.setStatus(4400);
			
			ajaxResult.setMsg(turnsrcipt);
			ajaxResult.setBody(loginPath);
			Gson gson = new Gson();
			String rspjson = gson.toJson(ajaxResult);
			response.getWriter().print(rspjson);
		}else {
			response.getWriter().print(turnsrcipt);			
		}
	}
	
	/**
	 * 鍒ゆ柇鏄惁鏄痑jax璇锋眰
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequst(HttpServletRequest request){
		
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
        	return true;
        }
		
		return false;
	}
	
	public String getCookieValue(HttpServletRequest request,String key){
		Cookie[] cookieArray = request.getCookies();
		if(cookieArray==null){
			return null;
		}
		for(Cookie cookie:cookieArray){
			if(key.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		
		return null;
	}
}
