package com.drbwx.admin.dao;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.ArticleComment;

@Repository
public interface ArticleCommentMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(ArticleComment record);

    ArticleComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleComment record);
    
    ReturnPage<ArticleComment> findByPage(PageQueryDto<ArticleComment> query);
    
    public int Delectone(int id);
    
    public int Recoveryone(int id);
}
