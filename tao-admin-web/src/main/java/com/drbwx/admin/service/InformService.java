package com.drbwx.admin.service;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.InformDto;

public interface InformService {
	
	public PageResultDto<InformDto> findByPage(InformDto dto, Integer page, Integer size);
	
	public void change(int id,int status);

}
