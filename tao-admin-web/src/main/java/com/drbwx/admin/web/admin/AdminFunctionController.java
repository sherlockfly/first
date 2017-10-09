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
import com.drbwx.admin.dto.AdminFunctionDto;
import com.drbwx.admin.dto.AdminMenuDto;
import com.drbwx.admin.service.AdminFunctionService;
import com.drbwx.admin.service.AdminMenuService;
import com.drbwx.admin.utils.JsonUtils;
import com.drbwx.admin.web.BaseController;

@Controller
@RequestMapping("function")
public class AdminFunctionController extends BaseController{
	
	@Autowired
	private AdminFunctionService adminFunctionService;
	
	@Autowired
	private AdminMenuService adminMenuService;
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.GONGNENGGUANLI);
		
		mv.setViewName("/auth/functionindex");
		return mv;
	}
	
	//添加
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(HttpServletRequest request,AdminFunctionDto roledto){
		roledto.setCreator(getCurrentOper(request).getRealName());
		roledto.setCreatDt(new Date());
		adminFunctionService.save(roledto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//更新
	@ResponseBody
	@RequestMapping("/update")
	public AjaxResult update(HttpServletRequest request,AdminFunctionDto dto){
		dto.setUpdateDt(new Date());
		dto.setCreator(getCurrentOper(request).getRealName());
		adminFunctionService.update(dto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<AdminFunctionDto> list(AdminFunctionDto dto,Integer page,Integer rows){
		
		PageResultDto<AdminFunctionDto> pageResult = adminFunctionService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public AjaxResult del(String id){
		
		adminFunctionService.del(id);
		
		AjaxResult result = new AjaxResult();
		return result;
	}
	
	/**
	 * 返回带菜单的功能树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("menuAndfunctiontree")
	public List menuAndfunctiontree(){
		//获取所有菜单
		AdminMenuDto menuqueryDto = new AdminMenuDto();
		menuqueryDto.setStatus(1);
		List<AdminMenuDto> menuList = adminMenuService.findHaveFunction();

		//获取所有function
		AdminFunctionDto queryfunctionDto = new AdminFunctionDto();
		queryfunctionDto.setStatus(1);;
		List<AdminFunctionDto> functionList = adminFunctionService.findByWhere(queryfunctionDto);
		
		//拼二级菜单下的功能列表
		Map<String,List> funMap = new HashMap<String,List>();
		for(AdminFunctionDto functionDto:functionList){
			List list = (List)funMap.get(functionDto.getMenuId().toString());
			if(list==null){
				list =new ArrayList<Map>();
			}
			Map<String,String> cmap = new HashMap<String,String>();
			cmap.put("id",functionDto.getId().toString());
			cmap.put("text",functionDto.getName());
			cmap.put("iconCls","icon-none");
			list.add(cmap);
			
			funMap.put(functionDto.getMenuId().toString(), list);
		}
		//拼 menu
		Map treeMap = new HashMap();
		for(AdminMenuDto dto:menuList){
			if(1==dto.getGrade()){
				//判断是否已存在
				Map nodemap = (Map)treeMap.get(dto.getId().toString());
				if(nodemap==null){
					nodemap = new HashMap<String, Object>();
				}	
				//nodemap.put("id",dto.getId());
				nodemap.put("text",dto.getName());
				
				treeMap.put(dto.getId().toString(), nodemap);

			}else if(2==dto.getGrade()){
				Map pMap = (Map)treeMap.get(dto.getPid().toString());
				if(funMap.get(dto.getId()+"")==null){
					continue;
				}
				if(pMap == null){
					Map nodemap = new HashMap<String, Object>();
					//nodemap.put("id",dto.getPid().toString());
					List cList = new ArrayList();
					Map<String,Object> cmap = new HashMap<String, Object>();
					//cmap.put("id",dto.getId().toString());
					cmap.put("text",dto.getName());
					cmap.put("children",funMap.get(dto.getId()+""));
					cList.add(cmap);
					nodemap.put("children", cList);
					
					treeMap.put(dto.getPid().toString(), nodemap);
				}else {
					List cList = (List)pMap.get("children");
					if(cList == null){
						cList = new ArrayList();
					}
					
					Map<String,Object> cmap = new HashMap<String, Object>();
					//cmap.put("id",dto.getId().toString());
					cmap.put("text",dto.getName());
					cmap.put("children",funMap.get(dto.getId()+""));
					cList.add(cmap);
					pMap.put("children",cList);
					
					treeMap.put(dto.getPid().toString(),pMap);
				}
			}
		}
		System.out.println((JsonUtils.toString(treeMap)));
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
	@RequestMapping("addRoleFunctions")
	public AjaxResult addRoleFunctions(Integer roleId,String functions){
		
		
		adminFunctionService.addroleFunctions(roleId, functions);
		
		return new AjaxResult(200,"操作成功");
	}
	
	
	@ResponseBody
	@RequestMapping("getRoleFunctions")
	//角色获取被分配的功能权限
	public AjaxResult getRoleFunctions(Integer roleId){
		
		String functions = adminFunctionService.findRoleFunctions(roleId);
		
		AjaxResult result = new AjaxResult();
		result.setBody(functions);
		
		return result;
	}	
	
}
