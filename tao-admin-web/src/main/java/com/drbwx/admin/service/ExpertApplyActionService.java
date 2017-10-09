package com.drbwx.admin.service;


import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ExpertApplyActionDto;

public interface ExpertApplyActionService {

	public int saveAction(ExpertApplyActionDto actionDto);

	public PageResultDto<ExpertApplyActionDto> findActionPage(
            ExpertApplyActionDto expertDto, Integer page, Integer size);

}
