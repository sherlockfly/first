package com.drbwx.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.po.Article;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    ReturnPage<Article> findByPage(PageQueryDto<Article> pageQuery);
    
	public List<CategoryDto> Category(int id);
	
	public void savemodify(int id,int cat_id);
	
	public Long selectusername(String username);
	
	public void saverelated_goods(int gid,int type,String url,String name,String pic_def);
}