package com.drbwx.admin.service;


import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ExpertApplyActionDto;
import com.drbwx.admin.dto.ExpertApplyDto;

public interface ExpertApplyService {
	
	public int ApplyExpert(ExpertApplyDto applyDto);
	
	public int updateApplyExpert(ExpertApplyDto applyDto,ExpertApplyActionDto actionDto);
	
	public ExpertApplyDto getExpert(Long id);
	
	public PageResultDto<ExpertApplyDto> findExpert(ExpertApplyDto expertDto, Integer page, Integer size);
	
	public int delById(Long id);
}
