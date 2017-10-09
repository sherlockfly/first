package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.AdminOperMapper;
import com.drbwx.admin.dao.AdminUserRoleMapper;
import com.drbwx.admin.dto.AdminOperDto;
import com.drbwx.admin.po.AdminOper;
import com.drbwx.admin.service.AdminOperService;

@Service
public class AdminOperServiceImpl implements AdminOperService {
	
	@Autowired
	private AdminOperMapper adminOperMapper;
	
	@Autowired
	private AdminUserRoleMapper adminUserRoleMapper;

	@Override
	public int save(AdminOperDto operDto) {
		AdminOper oper = new AdminOper();
		BeanUtils.copyProperties(operDto,oper);
		
		adminOperMapper.insert(oper);
		return 1;
	}

	@Override
	public int del(String id) {
		Integer operId = Integer.parseInt(id);
		adminOperMapper.deleteByPrimaryKey(operId);
		//级联删除 人员 角色信息
		adminUserRoleMapper.deleteByOperId(operId);
		
		return 1;
	}

	@Override
	public int update(AdminOperDto operDto) {
		AdminOper oper = new AdminOper();
		BeanUtils.copyProperties(operDto,oper);
		adminOperMapper.updateByPrimaryKeySelective(oper);
		
		return 1;
	}
	
	@Override
	public PageResultDto<AdminOperDto> findByPage(AdminOperDto dto,Integer page,Integer size) {
		AdminOper po = new AdminOper();
		BeanUtils.copyProperties(dto,po);
		
		PageQueryDto<AdminOper> pageQuery = new PageQueryDto<AdminOper>(po,page,size);
		ReturnPage<AdminOper> popageResult  = adminOperMapper.findByPage(pageQuery);
		
		List<AdminOperDto> dtolist = phraseList(popageResult.getObjList());
		
		PageResultDto<AdminOperDto> dtoPageresult = new PageResultDto<AdminOperDto>(popageResult.getTotalCount(),dtolist);
		
		return dtoPageresult;
	}

	@Override
	public List<AdminOperDto> findByWhere(AdminOperDto roleDto) {
		AdminOper role = new AdminOper();
		BeanUtils.copyProperties(roleDto,role);
		List<AdminOper> list = adminOperMapper.findByWhere(role);
		
		return phraseList(list);
	}
	
	private List<AdminOperDto> phraseList(List<AdminOper> poList){
		
		List<AdminOperDto> dtoList = new ArrayList<AdminOperDto>();
		for(AdminOper po:poList){
			AdminOperDto dto = new AdminOperDto();
			BeanUtils.copyProperties(po,dto);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
/*	*//**
	 * 校验登录用户名密码
	 *//*
	@Override
	public List<AdminOper> checkOperPass(String loginName, String passworld) {
		AdminOper po = new AdminOper();
		po.setLoginName(loginName);
		po.setPwd(passworld);
		List<AdminOper> list = adminOperMapper.findByWhere(po);
		if(list!=null&&list.size()>0){
			return true;
		}
		
		return list;
	}*/
}
