package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.SysMsgMapper;
import com.drbwx.admin.dto.SysMsgDto;
import com.drbwx.admin.po.SysMsg;
import com.drbwx.admin.service.SysMsgService;

@Service
public class SysMsgServiceImpl implements SysMsgService {
	
	@Autowired
	private SysMsgMapper sysMsgMapper;

	@Override
	public void saveUserMsg(SysMsgDto dto) {
		SysMsg po = new SysMsg();
		BeanUtils.copyProperties(dto, po);
		
		sysMsgMapper.insert(po);
	}

	@Override
	public void updateUserMsg(SysMsgDto dto) {
		SysMsg po = new SysMsg();
		BeanUtils.copyProperties(dto, po);

		sysMsgMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public SysMsgDto getSysMsgInfo(Long id) {
		SysMsg po = sysMsgMapper.selectByPrimaryKey(id);

		SysMsgDto dto = new SysMsgDto();
		BeanUtils.copyProperties(po, dto);

		return dto;
	}

	@Override
	public void delSysMsg(Long id) {
		sysMsgMapper.deleteByPrimaryKey(id);
	}
	

	@Override
	public PageResultDto<SysMsgDto> findUserMsg(SysMsgDto dto, Integer page,
			Integer size) {
		SysMsg po = new SysMsg();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<SysMsg> pageQuery = new PageQueryDto<SysMsg>(po, page,
				size);
		ReturnPage<SysMsg> popageResult = sysMsgMapper.findByPage(pageQuery);

		List<SysMsgDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<SysMsgDto> dtoPageresult = new PageResultDto<SysMsgDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<SysMsgDto> phraseList(List<SysMsg> poList) {

		List<SysMsgDto> dtoList = new ArrayList<SysMsgDto>();
		for (SysMsg po : poList) {
			SysMsgDto dto = new SysMsgDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

}
