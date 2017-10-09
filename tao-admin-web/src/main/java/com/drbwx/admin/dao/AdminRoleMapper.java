package com.drbwx.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.AdminRole;

@Repository
public interface AdminRoleMapper {
    int deleteByPrimaryKey(int id);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(AdminRole record);
    
    ReturnPage<AdminRole> findByPage(PageQueryDto<AdminRole> pageQuery);
    
    List<AdminRole> findByWhere(AdminRole role);
    
    List<AdminRole> findOperRoles(int operId);
    
}