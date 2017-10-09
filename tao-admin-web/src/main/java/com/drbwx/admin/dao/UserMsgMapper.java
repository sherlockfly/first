package com.drbwx.admin.dao;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.UserMsg;

@Repository
public interface UserMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMsg record);

    UserMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMsg record);
    
    ReturnPage<UserMsg> findByPage(PageQueryDto<UserMsg> query); 
}