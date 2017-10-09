package com.drbwx.admin.service;

import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminRoleDto;

public interface AdminRoleService {
	
	public int save(AdminRoleDto roleDto);
	
	public int del(String id);
	
	public int update(AdminRoleDto roleDto);
	
	public PageResultDto<AdminRoleDto> findByPage(AdminRoleDto roleDto,int page,int size);
	
	public List<AdminRoleDto> findByWhere(AdminRoleDto roleDto);
	
	public AdminRoleDto getByKey(String id);
	
	public String findByOperId(String operId);
	
	public int addRolesForOper(String operId,String roles);
}
