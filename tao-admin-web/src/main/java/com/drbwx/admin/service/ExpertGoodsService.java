package com.drbwx.admin.service;


import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.dto.ExpertGoodsDto;

public interface ExpertGoodsService {
	
	public int saveExpertGoods(ExpertGoodsDto dto);
	
	public int delById(Long id);
	
	public ExpertGoodsDto getExpertGoods(Long id);
	
	public int check(Long id, Short status);
	
	public int check(Long id, Short status, String msg,String url,String subhead);
	
	public PageResultDto<ExpertGoodsDto> findByPage(ExpertGoodsDto dto, Integer page, Integer size);
	
	public List<CategoryDto> Category(int id);
	
	public void savemodify(int id,int cat_id,String url);
	
	public Long selectusername(String username);
	
	public void saverelated_goods(int gid,int type,String url,String name,String pic_def);
}
