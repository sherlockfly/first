package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.AdminFunctionMapper;
import com.drbwx.admin.dto.AdminFunctionDto;
import com.drbwx.admin.po.AdminFunction;
import com.drbwx.admin.po.AdminRoleFunction;
import com.drbwx.admin.service.AdminFunctionService;

@Service
public class AdminFunctionServiceImpl implements AdminFunctionService {
	
	@Autowired
	private AdminFunctionMapper adminFunctionMapper;

	@Override
	public int save(AdminFunctionDto dto) {
		AdminFunction po = new AdminFunction();
		BeanUtils.copyProperties(dto,po);
		
		adminFunctionMapper.insert(po);
		return 1;
	}

	@Override
	public int del(String id) {
		Integer fid = Integer.parseInt(id);
		adminFunctionMapper.deleteByPrimaryKey(fid);
		
		adminFunctionMapper.deleteByFunctionId(fid);
		return 1;
	}

	@Override
	public int update(AdminFunctionDto dto) {
		AdminFunction po = new AdminFunction();
		BeanUtils.copyProperties(dto,po);
		
		adminFunctionMapper.updateByPrimaryKeySelective(po);
		return 1;
	}

	@Override
	public PageResultDto<AdminFunctionDto> findByPage(AdminFunctionDto dto,
			Integer page, Integer size) {
		AdminFunction po = new AdminFunction();
		BeanUtils.copyProperties(dto,po);
		
		PageQueryDto<AdminFunction> pageQuery = new PageQueryDto<AdminFunction>(po,page,size);
		ReturnPage<AdminFunction> popageResult  = adminFunctionMapper.findByPage(pageQuery);
		
		List<AdminFunctionDto> dtolist = phraseList(popageResult.getObjList());
		
		PageResultDto<AdminFunctionDto> dtoPageresult = new PageResultDto<AdminFunctionDto>(popageResult.getTotalCount(),dtolist);
		
		return dtoPageresult;
	}

	@Override
	public List<AdminFunctionDto> findByWhere(AdminFunctionDto dto) {
		AdminFunction po = new AdminFunction();
		BeanUtils.copyProperties(dto,po);
		List<AdminFunction> list = adminFunctionMapper.findByWhere(po);
		
		return phraseList(list);
	}
	
	private List<AdminFunctionDto> phraseList(List<AdminFunction> poList){
		
		List<AdminFunctionDto> dtoList = new ArrayList<AdminFunctionDto>();
		for(AdminFunction po:poList){
			AdminFunctionDto dto = new AdminFunctionDto();
			BeanUtils.copyProperties(po,dto);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public int addroleFunctions(Integer roleId, String functions) {
		//删除 之前 角色的
		adminFunctionMapper.deleteByRoleId(roleId);		
		
		String[] functionArray = functions.split(",");
		for(String function:functionArray){
			if(StringUtils.isEmpty(function)){
				continue;
			}
			AdminRoleFunction roleFunction = new AdminRoleFunction();
			roleFunction.setRoleId(roleId);
			roleFunction.setFunctionId(Integer.parseInt(function));
			adminFunctionMapper.insertRoleFunction(roleFunction);
		}
		
		return 1;
	}

	@Override
	public String findRoleFunctions(Integer roleId) {
		List<AdminRoleFunction> list = adminFunctionMapper.findByRoleId(roleId);
		
		String functions = "";
		for(AdminRoleFunction po:list){
			functions+=po.getFunctionId()+",";
		}
		if(functions.length()>2){
			functions = functions.substring(0,functions.length()-1);
		}
		return functions;
	}

	@Override
	public List<AdminFunctionDto> findByRoles(String roles) {
		Map<String,String> paraMap = new HashMap<String, String>();
		paraMap.put("roles",roles);
		List<AdminFunction> list = adminFunctionMapper.findByRoles(paraMap);
		
		return phraseList(list);
	}

}
