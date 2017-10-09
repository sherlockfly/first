package com.drbwx.admin.dao;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dto.InformDto;
import com.drbwx.admin.po.Inform;

@Repository
public interface InformMapper {
	
	public ReturnPage<Inform> findByPage(PageQueryDto<Inform> pageQuery);
	
	public void change(int id,int status);

}
