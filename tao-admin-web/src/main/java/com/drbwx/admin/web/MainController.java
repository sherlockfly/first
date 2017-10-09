package com.drbwx.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.service.AdminMenuService;
import com.drbwx.admin.service.AdminRoleService;


@Controller
public class MainController extends BaseController{
	
	@Autowired
	AdminMenuService adminMenuService;
	
	@Autowired
	AdminRoleService adminRoleService;

	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request,ModelAndView modelAndView){
		CurrentOper user = getCurrentOper(request);
		String roles = adminRoleService.findByOperId(user.getUserid());
		
		String menuTree ="";
		if(!StringUtils.isEmpty(roles)&&roles.length()>1){
			menuTree = adminMenuService.getMenuTreeJson(roles);
		}
		
		modelAndView.addObject("menuTree",menuTree);
		modelAndView.addObject("realname",user.getRealName());
		//初始化权限
		modelAndView.setViewName("main");
		return modelAndView;
	}
}
