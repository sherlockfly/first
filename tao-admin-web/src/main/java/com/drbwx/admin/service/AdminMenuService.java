package com.drbwx.admin.service;

import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminMenuDto;

public interface AdminMenuService {
	
	public int save(AdminMenuDto operDto);
	
	public int del(String id);
	
	public int update(AdminMenuDto operDto);
	
	public PageResultDto<AdminMenuDto> findByPage(AdminMenuDto dto,Integer page,Integer size);
	
	public List<AdminMenuDto> findByWhere(AdminMenuDto dto);
	
	public String getMenuTreeJson(String roles);
	
	public int addRoleMenus(String roleId,String menus);
	
	public String getRoleMenus(String roleId);
	
	public List<AdminMenuDto> findHaveFunction();
}
