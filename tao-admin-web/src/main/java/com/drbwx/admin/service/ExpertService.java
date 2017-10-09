package com.drbwx.admin.service;


import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ExpertDto;

public interface ExpertService {
	
	public void saveExpert(ExpertDto expertDto);
	
	public void updateExpert(ExpertDto expertDto);
	
	public PageResultDto<ExpertDto> findExpert(ExpertDto expertDto, Integer page, Integer size);
	
	public ExpertDto getExpertInfo(Long id);
	
	public PageResultDto<ExpertDto> findInfoByPage(ExpertDto dto, Integer page,
            Integer size);
}
