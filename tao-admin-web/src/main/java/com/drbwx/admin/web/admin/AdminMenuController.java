package com.drbwx.admin.web.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminMenuDto;
import com.drbwx.admin.service.AdminMenuService;
import com.drbwx.admin.web.BaseController;
import com.google.gson.JsonArray;

@Controller
@RequestMapping("menu")
public class AdminMenuController extends BaseController{
	
	@Autowired
	private AdminMenuService adminMenuService;
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.CAIDANGUANLI);
		
		mv.setViewName("/auth/menuindex");
		return mv;
	}
	
	//添加用户
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(HttpServletRequest request,AdminMenuDto roledto){
		roledto.setCreator(getCurrentOper(request).getRealName());
		roledto.setCreatDt(new Date());
		adminMenuService.save(roledto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//更新
	@ResponseBody
	@RequestMapping("/update")
	public AjaxResult update(HttpServletRequest request,AdminMenuDto dto){
		dto.setUpdateDt(new Date());
		dto.setCreator(getCurrentOper(request).getRealName());
		adminMenuService.update(dto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<AdminMenuDto> list(AdminMenuDto dto,Integer page,Integer rows){
		
		PageResultDto<AdminMenuDto> pageResult = adminMenuService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public AjaxResult del(String id){
		
		adminMenuService.del(id);
		
		AjaxResult result = new AjaxResult();
		return result;
	} 
	
	/**
	 * 一级菜单 单选
	 * @param querydto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("menucombo")
	public List<Map<String,String>> menucombo(AdminMenuDto querydto){
		querydto.setGrade(1);
		querydto.setStatus(1);
		List<AdminMenuDto> list = adminMenuService.findByWhere(querydto);

		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		Map<String,String> initMap = new HashMap<String, String>();
		initMap.put("id","0");
		initMap.put("text","无");
		resultList.add(initMap);
		for(AdminMenuDto dto:list){
			Map<String,String> map = new HashMap<String, String>();
			
			map.put("id",dto.getId().toString());
			map.put("text", dto.getName());
			
			resultList.add(map);
		}
		
		return resultList;
	}
	
	/**
	 * 所有菜单的树
	 * @param querydto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("menutree")
	public List menutree(AdminMenuDto querydto){
		querydto.setStatus(1);
		JsonArray jsonArray = new JsonArray();
		List<AdminMenuDto> list = adminMenuService.findByWhere(querydto);
		Map treeMap = new HashMap();
		for(AdminMenuDto dto:list){
			if(1==dto.getGrade()){
				//判断是否已存在
				Map nodemap = (Map)treeMap.get(dto.getId().toString());
				if(nodemap==null){
					nodemap = new HashMap<String, Object>();
				}	
				nodemap.put("id",dto.getId());
				nodemap.put("text",dto.getName());
				
				treeMap.put(dto.getId().toString(), nodemap);

			}else if(2==dto.getGrade()){
				Map pMap = (Map)treeMap.get(dto.getPid().toString());
				if(pMap == null){
					Map nodemap = new HashMap<String, Object>();
					nodemap.put("id",dto.getPid().toString());
					List cList = new ArrayList();
					Map<String,String> cmap = new HashMap<String, String>();
					cmap.put("id",dto.getId().toString());
					cmap.put("text",dto.getName());
					cList.add(cmap);
					nodemap.put("children", cList);
					
					treeMap.put(dto.getPid().toString(), nodemap);
				}else {
					List cList = (List)pMap.get("children");
					if(cList == null){
						cList = new ArrayList();
					}
					
					Map<String,String> cmap = new HashMap<String, String>();
					cmap.put("id",dto.getId().toString());
					cmap.put("text",dto.getName());
					cList.add(cmap);
					pMap.put("children",cList);
					
					treeMap.put(dto.getPid().toString(),pMap);
				}
			}
		}
		
		return toList(treeMap);
	}
	
	private List toList(Map<String,Object> map){
		List list = new ArrayList<Object>();
		Set<String> set = map.keySet();
		for(String id:set){
			list.add(map.get(id));
		}
		
		return list;
	}
	
	/**
	 * 为角色分配菜单
	 * @param roleId
	 * @param menus
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addRoleMenus")
	public AjaxResult addRoleMenus(String roleId,String menus){
		
		
		adminMenuService.addRoleMenus(roleId, menus);
		
		return new AjaxResult(200,"操作成功");
	}
	
	
	@ResponseBody
	@RequestMapping("getRoleMenus")
	//角色获取菜单权限
	public AjaxResult getRoleMenus(String roleId){
		
		String menus = adminMenuService.getRoleMenus(roleId);
		
		AjaxResult result = new AjaxResult();
		result.setBody(menus);
		
		return result;
	}
	
}
