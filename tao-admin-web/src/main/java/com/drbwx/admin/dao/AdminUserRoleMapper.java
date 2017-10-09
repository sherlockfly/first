package com.drbwx.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.po.AdminUserRole;

@Repository
public interface AdminUserRoleMapper {
    int deleteByPrimaryKey(AdminUserRole key);

    int insert(AdminUserRole record);

    int insertSelective(AdminUserRole record);
    
    int deleteByOperId (int operId);
    
    int deleteByRoleId (int roleId);
    
    List<AdminUserRole> findByWhere(AdminUserRole po);
}