package com.drbwx.admin.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.UserMapper;
import com.drbwx.admin.dto.UserDto;
import com.drbwx.admin.po.User;
import com.drbwx.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void saveUser(UserDto dto) {
		User po = new User();
		BeanUtils.copyProperties(dto,po);

		userMapper.insert(po);
	}

	@Override
	public void updateUser(UserDto dto) {
		User po = new User();
		BeanUtils.copyProperties(dto, po);

		userMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public UserDto getUser(Long id) {
		User po = userMapper.selectByPrimaryKey(id);

		UserDto dto = new UserDto();
		BeanUtils.copyProperties(po, dto);

		return dto;
	}

	@Override
	public PageResultDto<UserDto> findByPage(UserDto dto, Integer page,
			Integer size) {
		User po = new User();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<User> pageQuery = new PageQueryDto<User>(po, page,
				size);
		ReturnPage<User> popageResult = userMapper.findByPage(pageQuery);

		List<UserDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<UserDto> dtoPageresult = new PageResultDto<UserDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}
	
	private List<UserDto> phraseList(List<User> poList) {

		List<UserDto> dtoList = new ArrayList<UserDto>();
		for (User po : poList) {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public int saveremark(int id, String myremark) {
		return userMapper.saveremark(id, myremark);
	}
	
	

 
}
