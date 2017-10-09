package com.drbwx.admin.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.AdminMenu;
import com.drbwx.admin.po.AdminRoleMenu;

@Repository
public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMenu record);

    AdminMenu selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AdminMenu record);
    
    ReturnPage<AdminMenu> findByPage(PageQueryDto<AdminMenu> oper);
    
    List<AdminMenu> findByWhere(AdminMenu oper);
    
    int insertRoleMenu(AdminRoleMenu roleMenu);
    
    int deleteByRoleId(Integer roleId);
    
    int deleteByMenuId(Integer menuId);
    
    List<AdminRoleMenu> findByRoleId(Integer roleId);
    
    List<AdminMenu> findByRoles(Map pMap);
    
    List<AdminMenu> findHaveFunction();
    
}