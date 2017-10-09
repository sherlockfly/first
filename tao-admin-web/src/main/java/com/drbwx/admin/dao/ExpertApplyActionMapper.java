package com.drbwx.admin.dao;


import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.ExpertApplyAction;

@Repository
public interface ExpertApplyActionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExpertApplyAction record);

    ExpertApplyAction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpertApplyAction record);
    
    ReturnPage<ExpertApplyAction> findByPage(PageQueryDto<ExpertApplyAction> query);
}