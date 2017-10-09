package com.drbwx.admin.service;

import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.CategoryDto;

/**
 * 分类
 * @author zhaolin
 * 二〇一六年十一月一日 16:52:2
 */
public interface CategoryService {
	
	public int save(CategoryDto dto);
	
	public int del(Integer id);
	
	public int update(CategoryDto dto);
	
	public PageResultDto<CategoryDto> findByPage(CategoryDto dto,Integer page,Integer size);
	
	public List<CategoryDto> findByWhere(CategoryDto dto);
	
	//public boolean checkOperPass(String loginName,String passworld);

}
