package com.drbwx.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.AdminOper;

@Repository
public interface AdminOperMapper {
    int deleteByPrimaryKey(int id);

    int insert(AdminOper record);

    int insertSelective(AdminOper record);

    int updateByPrimaryKeySelective(AdminOper record);
    
    ReturnPage<AdminOper> findByPage(PageQueryDto<AdminOper> oper);
    
    List<AdminOper> findByWhere(AdminOper oper);
}