package com.drbwx.admin.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.po.ExpertGoods;

@Repository
public interface ExpertGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExpertGoods record);

    ExpertGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExpertGoods record);

    int updateByPrimaryKeyWithBLOBs(ExpertGoods record);
    
    ReturnPage<ExpertGoods> findByPage(PageQueryDto<ExpertGoods> query);
    
    public List<CategoryDto> classificationone();
    
    public List<CategoryDto> classificationtwo(int id);
    
    public List<CategoryDto> classificationthree(int id);
    
    public void savemodify(int id,int cat_id,String url);
    
    public String selectcatId(int catId);
    
    public String selectuid(int uid);
    
    public Long selectusername(String username);
    
    public void saverelated_goods(int gid,int type,String url,String name,String pic_def);
}