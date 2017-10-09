package com.drbwx.admin.service;

import java.util.List;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ArticleDto;
import com.drbwx.admin.dto.CategoryDto;

public interface ArticleService {
	
	public int save(ArticleDto dto);
	
	public int delById(Long id);
	
	public ArticleDto getInfo(Long id);
	
	public int check(Long id,Byte status,String beizhu,String subTitle);
	
	public PageResultDto<ArticleDto> findByPage(ArticleDto dto, Integer page, Integer size);
	
	public String selectcatId(int catId);
	
	public String selectuid(int uid);
	
    public List<CategoryDto> classificationone();
	
	public List<CategoryDto> classificationtwo(int id);
	
	public List<CategoryDto> classificationthree(int id);
	
	public void savemodify(int id,int cat_id);
	
	public Long selectusername(String username);
	
	public void saverelated_goods(int gid,int type,String url,String name,String pic_def);
}
