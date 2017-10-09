package com.drbwx.admin.service;

import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminOperDto;

/**
 * 管理员账号
 * @author zhaolin
 *
 */
public interface AdminOperService {
	
	public int save(AdminOperDto operDto);
	
	public int del(String id);
	
	public int update(AdminOperDto operDto);
	
	public PageResultDto<AdminOperDto> findByPage(AdminOperDto dto,Integer page,Integer size);
	
	public List<AdminOperDto> findByWhere(AdminOperDto dto);
	
	//public boolean checkOperPass(String loginName,String passworld);

}
