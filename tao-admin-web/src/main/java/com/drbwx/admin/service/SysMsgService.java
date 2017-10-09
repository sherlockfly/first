package com.drbwx.admin.service;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.SysMsgDto;

public interface SysMsgService {
	
	public void saveUserMsg(SysMsgDto dto);
	
	public void updateUserMsg(SysMsgDto dto);
	
	public PageResultDto<SysMsgDto> findUserMsg(SysMsgDto dto, Integer page, Integer size);
	
	public SysMsgDto getSysMsgInfo(Long id);
	
	public void delSysMsg(Long id);
}
