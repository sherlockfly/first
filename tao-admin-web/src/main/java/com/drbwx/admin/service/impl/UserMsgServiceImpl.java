package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.UserMsgMapper;
import com.drbwx.admin.dto.UserMsgDto;
import com.drbwx.admin.dto.UserMsgDto;
import com.drbwx.admin.po.UserMsg;
import com.drbwx.admin.po.UserMsg;
import com.drbwx.admin.service.UserMsgService;

/**
 * 
 * <p>Description:用户消息接口 <／p>
 * <p>Company: drbwx<／p>
 * @author zhaolin
 * @date 2016年11月14日
 * @version 1.0
 */
@Service
public class UserMsgServiceImpl implements UserMsgService {
	
	@Autowired
	private UserMsgMapper userMsgMapper;

	@Override
	public void saveUserMsg(UserMsgDto dto) {
		UserMsg po = new UserMsg();
		BeanUtils.copyProperties(dto, po);
		
		userMsgMapper.insert(po);
	}	

	@Override
	public void updateUserMsg(UserMsgDto dto) {
		UserMsg po = new UserMsg();
		BeanUtils.copyProperties(dto, po);
		
		userMsgMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public PageResultDto<UserMsgDto> findUserMsg(UserMsgDto dto, Integer page,
			Integer size) {
		UserMsg po = new UserMsg();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<UserMsg> pageQuery = new PageQueryDto<UserMsg>(po, page,
				size);
		ReturnPage<UserMsg> popageResult = userMsgMapper.findByPage(pageQuery);

		List<UserMsgDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<UserMsgDto> dtoPageresult = new PageResultDto<UserMsgDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<UserMsgDto> phraseList(List<UserMsg> poList) {

		List<UserMsgDto> dtoList = new ArrayList<UserMsgDto>();
		for (UserMsg po : poList) {
			UserMsgDto dto = new UserMsgDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public UserMsgDto getUserMsgInfo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
