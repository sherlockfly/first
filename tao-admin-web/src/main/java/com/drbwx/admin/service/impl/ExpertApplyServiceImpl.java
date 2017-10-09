package com.drbwx.admin.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drbwx.admin.common.ApplyStatusEnum;
import com.drbwx.admin.common.CommonStatusEnum;
import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.ExpertApplyActionMapper;
import com.drbwx.admin.dao.ExpertApplyMapper;
import com.drbwx.admin.dao.ExpertMapper;
import com.drbwx.admin.dao.UserMapper;
import com.drbwx.admin.dao.UserMsgMapper;
import com.drbwx.admin.dto.ExpertApplyActionDto;
import com.drbwx.admin.dto.ExpertApplyDto;
import com.drbwx.admin.dto.ExpertDto;
import com.drbwx.admin.po.Expert;
import com.drbwx.admin.po.ExpertApply;
import com.drbwx.admin.po.ExpertApplyAction;
import com.drbwx.admin.po.UserMsg;
import com.drbwx.admin.service.ExpertApplyActionService;
import com.drbwx.admin.service.ExpertApplyService;

@Service
public class ExpertApplyServiceImpl implements ExpertApplyService {

	@Autowired
	private ExpertApplyMapper experApplyMapper;
	
	@Autowired
	private ExpertMapper expertMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMsgMapper userMsgMapper;
	
	//private 

	public int ApplyExpert(ExpertApplyDto applyDto) {
		ExpertApply po = new ExpertApply();
		BeanUtils.copyProperties(applyDto, po);

		return experApplyMapper.insert(po);
	}

	public ExpertApplyDto getExpert(Long id) {
		ExpertApply po = experApplyMapper.selectByPrimaryKey(id);

		ExpertApplyDto dto = new ExpertApplyDto();
		BeanUtils.copyProperties(po, dto);

		return dto;
	}

	public PageResultDto<ExpertApplyDto> findExpert(ExpertApplyDto expertDto,
                                                    Integer page, Integer size) {
		ExpertApply po = new ExpertApply();
		BeanUtils.copyProperties(expertDto, po);

		PageQueryDto<ExpertApply> pageQuery = new PageQueryDto<ExpertApply>(po,
				page, size);
		ReturnPage<ExpertApply> popageResult = experApplyMapper
				.findByPage(pageQuery);

		List<ExpertApplyDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<ExpertApplyDto> dtoPageresult = new PageResultDto<ExpertApplyDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<ExpertApplyDto> phraseList(List<ExpertApply> poList) {

		List<ExpertApplyDto> dtoList = new ArrayList<ExpertApplyDto>();
		for (ExpertApply po : poList) {
			ExpertApplyDto dto = new ExpertApplyDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public int delById(Long id) {
		int i =experApplyMapper.deleteByPrimaryKey(id);
		
		return i;
	}
	
	/**
	 * 审核通过
	 */
	@Override
	@Transactional
	public int updateApplyExpert(ExpertApplyDto applyDto,
			ExpertApplyActionDto actionDto) {
		ExpertApply po = new ExpertApply();
		
		BeanUtils.copyProperties(applyDto, po);
		int result = experApplyMapper.updateByPrimaryKeySelective(po);
		
		//审核通过同步 信息 并发送消息
		if(result > 0 && applyDto.getStatus() == ApplyStatusEnum.tongguo.getStatus()){
			ExpertApply applyPo = experApplyMapper.selectByPrimaryKey(po.getId());

			//同步到expert表
			Expert expert = new Expert();
			expert.setId(applyPo.getUid());
			expert.setStatus(CommonStatusEnum.keyong.getStatus());
			expert.setUid(applyPo.getUid());
			expert.setIntro(applyPo.getContent());
			
			expertMapper.insert(expert);
			//保存消息
			UserMsg msg = new UserMsg();
			msg.setUid(applyPo.getUid());
			msg.setTitle("申请成功");
			msg.setContent("申请成功,现在可以发布商品了哦");
			msg.setType((byte)1);
			msg.setCreator("1");
			
			userMsgMapper.insert(msg);
		}

		
		return 1;
	}

}
