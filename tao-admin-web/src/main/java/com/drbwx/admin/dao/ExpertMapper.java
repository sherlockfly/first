package com.drbwx.admin.dao;


import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.Expert;

@Repository
public interface ExpertMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Expert record);

    Expert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Expert record);

    ReturnPage<Expert> findByPage(PageQueryDto<Expert> query);
    
    ReturnPage<Expert> findInfoByPage(PageQueryDto<Expert> query);
}