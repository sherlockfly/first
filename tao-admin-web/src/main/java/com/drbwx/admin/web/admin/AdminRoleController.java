package com.drbwx.admin.web.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminRoleDto;
import com.drbwx.admin.service.AdminRoleService;
import com.drbwx.admin.web.BaseController;

@Controller
@RequestMapping("role")
public class AdminRoleController extends BaseController{
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JUESEGUANLI);
		
		mv.setViewName("/auth/roleindex");
		return mv;
	}
	
	//添加用户
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(HttpServletRequest request,AdminRoleDto roledto){
		roledto.setCreator(getCurrentOper(request).getRealName());
		roledto.setCreatDt(new Date());
		adminRoleService.save(roledto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//更新
	@ResponseBody
	@RequestMapping("/update")
	public AjaxResult update(HttpServletRequest request,AdminRoleDto roledto){
		roledto.setUpdateDt(new Date());
		roledto.setCreator(getCurrentOper(request).getRealName());
		adminRoleService.update(roledto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<AdminRoleDto> list(AdminRoleDto dto,Integer page,Integer rows){
		
		PageResultDto<AdminRoleDto> pageResult = adminRoleService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public AjaxResult del(String id){
		
		adminRoleService.del(id);
		
		AjaxResult result = new AjaxResult();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("rolecombo")
	public List<Map<String,String>> rolecombo(AdminRoleDto querydto){
		querydto.setStatus((short)1);
		List<AdminRoleDto> list = adminRoleService.findByWhere(querydto);

		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		Map<String,String> initMap = new HashMap<String, String>();
		resultList.add(initMap);
		for(AdminRoleDto dto:list){
			Map<String,String> map = new HashMap<String, String>();
			
			map.put("id",dto.getId().toString());
			map.put("text", dto.getName());
			resultList.add(map);
		}
		
		return resultList;
	}
	
	//添加角色
	@ResponseBody
	@RequestMapping("/addRoleForOper")
	public AjaxResult addRoleForOper(String id,String roles){
		adminRoleService.addRolesForOper(id, roles);
		
		AjaxResult result = new AjaxResult();
		return result;
	} 
	
	//获取用户已有的角色值
	@ResponseBody
	@RequestMapping("/getRoleOfOper")
	public AjaxResult getRoleOfOper(String id){
		String roles = adminRoleService.findByOperId(id);
		
		AjaxResult result = new AjaxResult();
		result.setBody(roles);
		return result;
	} 
	
}
