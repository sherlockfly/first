package com.drbwx.admin.dao;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.Expert;
import com.drbwx.admin.po.SysMsg;

@Repository
public interface SysMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMsg record);

    SysMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMsg record);
    
    ReturnPage<SysMsg> findByPage(PageQueryDto<SysMsg> query);

}