package com.drbwx.admin.dao;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.GoodsComment;

@Repository
public interface GoodsCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsComment record);

    GoodsComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsComment record);
    
    ReturnPage<GoodsComment> findByPage(PageQueryDto<GoodsComment> query);
    
    public int Delectone(int id);
	
	public int Recoveryone(int id);
}