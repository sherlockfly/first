package com.drbwx.admin.service;

import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminFunctionDto;


public interface AdminFunctionService {
	
	public int save(AdminFunctionDto dto);
	
	public int del(String id);
	
	public int update(AdminFunctionDto dto);
	
	public PageResultDto<AdminFunctionDto> findByPage(AdminFunctionDto dto,Integer page,Integer size);
	
	public List<AdminFunctionDto> findByWhere(AdminFunctionDto dto);
	
	public int addroleFunctions(Integer roleId,String functions);
	
	public String findRoleFunctions(Integer roleId);
	
	public List<AdminFunctionDto> findByRoles(String roles);
}
