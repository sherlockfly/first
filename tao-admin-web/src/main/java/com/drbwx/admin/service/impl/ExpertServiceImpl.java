package com.drbwx.admin.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.ExpertMapper;
import com.drbwx.admin.dto.ExpertDto;
import com.drbwx.admin.po.Expert;
import com.drbwx.admin.service.ExpertService;

@Service
public class ExpertServiceImpl implements ExpertService {

	@Autowired
	private ExpertMapper expertMapper;

	public void saveExpert(ExpertDto expertDto) {
		Expert po = new Expert();
		BeanUtils.copyProperties(expertDto,po);

		expertMapper.insert(po);

	}

	public void updateExpert(ExpertDto expertDto) {
		Expert po = new Expert();
		BeanUtils.copyProperties(expertDto, po);

		expertMapper.updateByPrimaryKeySelective(po);
	}

	public PageResultDto<ExpertDto> findExpert(ExpertDto dto, Integer page,
                                               Integer size) {
		Expert po = new Expert();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<Expert> pageQuery = new PageQueryDto<Expert>(po, page,
				size);
		ReturnPage<Expert> popageResult = expertMapper.findByPage(pageQuery);

		List<ExpertDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<ExpertDto> dtoPageresult = new PageResultDto<ExpertDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<ExpertDto> phraseList(List<Expert> poList) {

		List<ExpertDto> dtoList = new ArrayList<ExpertDto>();
		for (Expert po : poList) {
			ExpertDto dto = new ExpertDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public ExpertDto getExpertInfo(Long id) {
		Expert po = expertMapper.selectByPrimaryKey(id);

		ExpertDto dto = new ExpertDto();
		BeanUtils.copyProperties(po, dto);

		return dto;
	}

	@Override
	public PageResultDto<ExpertDto> findInfoByPage(ExpertDto dto, Integer page,
            Integer size) {
		Expert po = new Expert();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<Expert> pageQuery = new PageQueryDto<Expert>(po, page,
				size);
		ReturnPage<Expert> popageResult = expertMapper.findInfoByPage(pageQuery);

		List<ExpertDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<ExpertDto> dtoPageresult = new PageResultDto<ExpertDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}
}
