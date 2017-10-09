package com.drbwx.admin.service;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.UserDto;



public interface UserService {
	
	public void saveUser(UserDto UserDto);
	
	public void updateUser(UserDto UserDto);
	
	public PageResultDto<UserDto> findByPage(UserDto UserDto, Integer page, Integer size);
	
	public UserDto getUser(Long id);
	
	public int saveremark(int id,String myremark);

}
