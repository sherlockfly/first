package com.drbwx.admin.dao;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.ExpertApply;

@Repository
public interface ExpertApplyMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(ExpertApply record);

    ExpertApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpertApply record);

    int updateByPrimaryKeyWithBLOBs(ExpertApply record);

    int updateByPrimaryKey(ExpertApply record);
    
    ReturnPage<ExpertApply> findByPage(PageQueryDto<ExpertApply> query);

}