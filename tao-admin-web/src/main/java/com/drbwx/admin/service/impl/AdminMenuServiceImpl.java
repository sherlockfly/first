package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.AdminMenuMapper;
import com.drbwx.admin.dto.AdminMenuDto;
import com.drbwx.admin.po.AdminMenu;
import com.drbwx.admin.po.AdminRoleMenu;
import com.drbwx.admin.service.AdminMenuService;

@Service
public class AdminMenuServiceImpl implements AdminMenuService {
	
	@Autowired
	private AdminMenuMapper adminMenuMapper;

	@Override
	public int save(AdminMenuDto dto) {
		AdminMenu po = new AdminMenu();
		BeanUtils.copyProperties(dto,po);
		
		adminMenuMapper.insert(po);
		
		return 1;
	}

	@Override
	public int del(String id) {
		Integer menuId = Integer.parseInt(id);
		adminMenuMapper.deleteByPrimaryKey(menuId);
		//级联删除 对应 角色 +对应功能
		adminMenuMapper.deleteByMenuId(menuId);
		
		return 1;
	}

	@Override
	public int update(AdminMenuDto dto) {
		AdminMenu po = new AdminMenu();
		BeanUtils.copyProperties(dto,po);
		
		adminMenuMapper.updateByPrimaryKeySelective(po);
		return 1;
	}

	@Override
	public PageResultDto<AdminMenuDto> findByPage(AdminMenuDto dto,
			Integer page, Integer size) {
		AdminMenu po = new AdminMenu();
		BeanUtils.copyProperties(dto,po);
		
		PageQueryDto<AdminMenu> pageQuery = new PageQueryDto<AdminMenu>(po,page,size);
		ReturnPage<AdminMenu> popageResult  = adminMenuMapper.findByPage(pageQuery);
		
		List<AdminMenuDto> dtolist = phraseList(popageResult.getObjList());
		
		PageResultDto<AdminMenuDto> dtoPageresult = new PageResultDto<AdminMenuDto>(popageResult.getTotalCount(),dtolist);
		
		return dtoPageresult;
	}

	@Override
	public List<AdminMenuDto> findByWhere(AdminMenuDto dto) {
		AdminMenu po = new AdminMenu();
		BeanUtils.copyProperties(dto,po);
		List<AdminMenu> list = adminMenuMapper.findByWhere(po);
		
		return phraseList(list);
	}
	
	private List<AdminMenuDto> phraseList(List<AdminMenu> poList){
		
		List<AdminMenuDto> dtoList = new ArrayList<AdminMenuDto>();
		for(AdminMenu po:poList){
			if(po==null){
				continue;
			}
			AdminMenuDto dto = new AdminMenuDto();
			BeanUtils.copyProperties(po,dto);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public String getMenuTreeJson(String roles) {
		//获取用户权限下的菜单
		Map<String,String> paraMap = new HashMap<String, String>();
		paraMap.put("roles", roles);
		List<AdminMenu> list = adminMenuMapper.findByRoles(paraMap);
		
		Map treeMap = new HashMap();
		for(AdminMenu menupo:list){
			if(1==menupo.getGrade()){
				//判断是否已存在
				Map nodemap = (Map)treeMap.get(menupo.getId().toString());
				if(nodemap==null){
					nodemap = new HashMap<String, Object>();
				}	
				nodemap.put("menuid",menupo.getId());
				nodemap.put("icon",menupo.getIcon());
				nodemap.put("menuname",menupo.getName());
				
				treeMap.put(menupo.getId().toString(), nodemap);

			}else if(2==menupo.getGrade()){
				Map pMap = (Map)treeMap.get(menupo.getPid().toString());
				if(pMap == null){
					Map nodemap = new HashMap<String, Object>();
					nodemap.put("menuid",menupo.getPid().toString());
					List cList = new ArrayList();
					Map<String,String> cmap = new HashMap<String, String>();
					cmap.put("menuid",menupo.getId().toString());
					cmap.put("menuname",menupo.getName());
					cmap.put("icon",menupo.getIcon());
					cmap.put("url",menupo.getUrl());
					cList.add(cmap);
					nodemap.put("menus", cList);
					
					treeMap.put(menupo.getPid().toString(), nodemap);
				}else {
					List cList = (List)pMap.get("menus");
					if(cList == null){
						cList = new ArrayList();
					}
					
					Map<String,String> cmap = new HashMap<String, String>();
					cmap.put("menuid",menupo.getId().toString());
					cmap.put("menuname",menupo.getName());
					cmap.put("icon",menupo.getIcon());
					cmap.put("url",menupo.getUrl());
					
					cList.add(cmap);
					pMap.put("menus",cList);
					
					treeMap.put(menupo.getPid().toString(),pMap);
				}
			}
		}
		
		List list2 = toList(treeMap);
		return JSONUtils.toJSONString(list2);
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
	 * 为角色分配菜单权限
	 */
	@Override
	public int addRoleMenus( String roleId, String menus) {
		//删除 之前 角色的
		adminMenuMapper.deleteByRoleId(Integer.parseInt(roleId));		
		
		String[] menuArray = menus.split(",");
		for(String menu:menuArray){
			if(StringUtils.isEmpty(menu)){
				continue;
			}
			AdminRoleMenu roleMenu = new AdminRoleMenu();
			roleMenu.setRoleId(Integer.parseInt(roleId));
			roleMenu.setMenuId(Integer.parseInt(menu));
			adminMenuMapper.insertRoleMenu(roleMenu);
		}
		
		return 1;
	}

	@Override
	public String getRoleMenus(String roleId) {
		List<AdminRoleMenu> list = adminMenuMapper.findByRoleId(Integer.parseInt(roleId));
		
		String menus = "";
		for(AdminRoleMenu po:list){
			menus+=po.getMenuId()+",";
		}
		if(menus.length()>2){
			menus = menus.substring(0,menus.length()-1);
		}
		return menus;
	}

	@Override
	public List<AdminMenuDto> findHaveFunction() {
		List<AdminMenu> list = adminMenuMapper.findHaveFunction();
		
		return phraseList(list);
	}

}
