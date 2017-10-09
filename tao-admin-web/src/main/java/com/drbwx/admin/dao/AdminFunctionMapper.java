package com.drbwx.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.AdminFunction;
import com.drbwx.admin.po.AdminRoleFunction;

@Repository
public interface AdminFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminFunction record);

    AdminFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminFunction record);
    
    ReturnPage<AdminFunction> findByPage(PageQueryDto<AdminFunction> oper);
    
    List<AdminFunction> findByWhere(AdminFunction oper);
    
    int insertRoleFunction(AdminRoleFunction roleFunction);
    
    int deleteByRoleId(Integer roleId);
    
    int deleteByFunctionId(Integer functionId);
    
    List<AdminRoleFunction> findByRoleId(Integer roleId);
    
    List<AdminFunction> findByRoles(Map map);
}