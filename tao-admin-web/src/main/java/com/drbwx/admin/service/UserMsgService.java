package com.drbwx.admin.service;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.UserMsgDto;

public interface UserMsgService {
	
	public void saveUserMsg(UserMsgDto UserMsgDto);
	
	public void updateUserMsg(UserMsgDto UserMsgDto);
	
	public PageResultDto<UserMsgDto> findUserMsg(UserMsgDto UserMsgDto, Integer page, Integer size);
	
	public UserMsgDto getUserMsgInfo(Long id);
	
}
