package com.drbwx.admin.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.po.User;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    ReturnPage<User> findByPage(PageQueryDto<User> query);
    
    List<User> findByWhere(User user);
    
    public int saveremark(int id,String myremark);

}