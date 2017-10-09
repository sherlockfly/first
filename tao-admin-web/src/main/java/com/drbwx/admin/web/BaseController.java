package com.drbwx.admin.web;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.CurrentOper;

public class BaseController {
	
	private static final String SESSION_USER_KEY = "cuser";
	
/*	@Autowired
	private RedisService redisService;*/
	
	public CurrentOper getCurrentOper(HttpServletRequest request){
/*		String sessionId = getCookieValue(request,Constant.COOKIE_SESSION_ID);
		CurrentUser user = (CurrentUser)redisService.getValue(sessionId);
*/
		
		CurrentOper user = (CurrentOper)request.getSession().getAttribute(SESSION_USER_KEY);
		return user;
	}
	
	public void cacheCurrentOper(HttpServletRequest request,HttpServletResponse response,CurrentOper user){
		//String sessionId = request.getSession().getId();
		//cacheService.putGlobalCache(sessionId,user,Constant.LOGIN_REDIS_MAX_AGE);
		//redisService.setValue(sessionId, user, Constant.LOGIN_REDIS_MAX_AGE);
		
	/*	Cookie cookie = new Cookie(Constant.COOKIE_SESSION_ID,sessionId);
		cookie.setMaxAge(Constant.LOGIN_COOKIE_MAX_AGE);
		cookie.setPath("/");
		response.addCookie(cookie);*/
		
		request.getSession().setAttribute(SESSION_USER_KEY, user);
	}
	
	public void logoutOper(HttpServletRequest request,HttpServletResponse response){
		/*Cookie[] cookieArray = request.getCookies();
		for(Cookie cookie:cookieArray){
			if(Constant.COOKIE_SESSION_ID.equals(cookie.getName())){
				System.out.println("del:"+cookie.getValue());
				//cacheService.delGlobalCache(cookie.getValue());
			}
			
			Cookie newCookiew = new Cookie(cookie.getName(),null);
			newCookiew.setMaxAge(0);
			newCookiew.setPath("/");
			response.addCookie(newCookiew);
		}*/
		request.getSession().setAttribute(SESSION_USER_KEY, null);
	}

	public void getFunctions(HttpServletRequest request,ModelAndView mv,String menuName){
		CurrentOper user = getCurrentOper(request);
		Map funMap = user.getFunctionMap();
		String funs = (String)funMap.get(menuName);
		if(funs!=null){
			String[] funArray = funs.split(",");
			for(String fun:funArray){
				mv.addObject(fun,true);
			}
		}
	}
	
	private String getCookieValue(HttpServletRequest request,String key){
		Cookie[] cookieArray = request.getCookies();
		for(Cookie cookie:cookieArray){
			if(key.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		
		return null;
	}

}
