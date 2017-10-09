package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.AdminFunctionMapper;
import com.drbwx.admin.dao.AdminMenuMapper;
import com.drbwx.admin.dao.AdminRoleMapper;
import com.drbwx.admin.dao.AdminUserRoleMapper;
import com.drbwx.admin.dto.AdminRoleDto;
import com.drbwx.admin.po.AdminRole;
import com.drbwx.admin.po.AdminUserRole;
import com.drbwx.admin.service.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;
	
	@Autowired
	private AdminMenuMapper adminMenuMapper;
	
	@Autowired
	private AdminFunctionMapper adminFunctionMapper;
	
	@Override
	public int save(AdminRoleDto roleDto) {
		AdminRole role = new AdminRole();
		
		BeanUtils.copyProperties(roleDto,role);
		role.setCreatDt(new Date());
		
		adminRoleMapper.insert(role);
		return 1;
	}

	@Override
	public int del(String id) {
		adminRoleMapper.deleteByPrimaryKey(Integer.parseInt(id));
		//删除管理的 人员关联 todo
		adminUserRoleMapper.deleteByRoleId(Integer.parseInt(id));
		//角色菜单  功能按钮
		adminMenuMapper.deleteByRoleId(Integer.parseInt(id));
		//删除功能按钮
		adminFunctionMapper.deleteByRoleId(Integer.parseInt(id));
		
		return 1;
	}

	@Override
	public int update(AdminRoleDto roleDto) {
		AdminRole role = new AdminRole();
		
		BeanUtils.copyProperties(roleDto,role);
		adminRoleMapper.updateByPrimaryKeySelective(role);
		return 1;
	}

	@Override
	public AdminRoleDto getByKey(String id) {
		AdminRole role = adminRoleMapper.selectByPrimaryKey(Integer.parseInt(id));
		AdminRoleDto roleDto = new AdminRoleDto();
		BeanUtils.copyProperties(role,roleDto);
		
		return roleDto;
	}

	@Override
	public PageResultDto<AdminRoleDto> findByPage(AdminRoleDto dto,int page,int size) {
		AdminRole po = new AdminRole();
		BeanUtils.copyProperties(dto,po);
		PageQueryDto<AdminRole> pageQuery = new PageQueryDto<AdminRole>(po,page,size);

		ReturnPage<AdminRole> poPageResult  = adminRoleMapper.findByPage(pageQuery);
		
		List<AdminRoleDto> dtoList = phraseList(poPageResult.getObjList());
		
		PageResultDto<AdminRoleDto> pageResult = new PageResultDto<AdminRoleDto>(poPageResult.getTotalCount(),dtoList);
		
		return pageResult;
	}

	@Override
	public List<AdminRoleDto> findByWhere(AdminRoleDto roleDto) {
		AdminRole role = new AdminRole();
		BeanUtils.copyProperties(roleDto,role);
		List<AdminRole> list = adminRoleMapper.findByWhere(role);
		
		return phraseList(list);
	}
	
	private List<AdminRoleDto> phraseList(List<AdminRole> poList){
		
		List<AdminRoleDto> dtoList = new ArrayList<AdminRoleDto>();
		for(AdminRole po:poList){
			AdminRoleDto dto = new AdminRoleDto();
			BeanUtils.copyProperties(po,dto);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	/**
	 * 查着管理员所有角色
	 */
	@Override
	public String findByOperId(String operId) {
		AdminUserRole userRole = new AdminUserRole();
		userRole.setUserId(Integer.parseInt(operId));
		List<AdminUserRole>  poList=  adminUserRoleMapper.findByWhere(userRole);
		String roles = "";
		for(AdminUserRole po:poList){
			roles+=po.getRoleId()+",";
		}
		if(roles.length()>2){
			roles = roles.substring(0,roles.length()-1);
		}
		return roles;
	}
	
	@Override
	public int addRolesForOper(String operId,String roles){
		//删除之前的数据
		adminUserRoleMapper.deleteByOperId(Integer.parseInt(operId));
		//保存新roles
		String[] roleArray = roles.split(",");
		for(String role:roleArray){
			if(StringUtils.isEmpty(role)){
				continue;
			}
			AdminUserRole userRole = new AdminUserRole();
			userRole.setUserId(Integer.parseInt((operId)));
			userRole.setRoleId(Integer.parseInt(role));
			
			adminUserRoleMapper.insert(userRole);
		}
		
		return 1;
	}

}
