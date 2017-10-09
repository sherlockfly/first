package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.InformMapper;
import com.drbwx.admin.dto.InformDto;
import com.drbwx.admin.po.Inform;
import com.drbwx.admin.service.InformService;

@Service
public class InformServiceImpl implements InformService {
	
	@Autowired
	private InformMapper informMapper;

	@Override
	public PageResultDto<InformDto> findByPage(InformDto dto, Integer page, Integer size) {
		Inform po = new Inform();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<Inform> pageQuery = new PageQueryDto<Inform>(po, page,size);
		ReturnPage<Inform> popageResult = informMapper.findByPage(pageQuery);

		List<InformDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<InformDto> dtoPageresult = new PageResultDto<InformDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<InformDto> phraseList(List<Inform> poList) {
		List<InformDto> dtoList = new ArrayList<InformDto>();
		for (Inform po : poList) {
			InformDto dto = new InformDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public void change(int id,int status) {
		informMapper.change(id,status);
	}

}
