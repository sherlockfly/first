package com.drbwx.admin.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.ExpertGoodsMapper;
import com.drbwx.admin.dao.UserMsgMapper;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.dto.ExpertGoodsDto;
import com.drbwx.admin.po.ExpertGoods;
import com.drbwx.admin.po.UserMsg;
import com.drbwx.admin.service.ExpertGoodsService;

@Service
public class ExpertGoodsServiceImpl implements ExpertGoodsService {
	
	@Autowired
	private ExpertGoodsMapper expertGoodsMapper;
	
	@Autowired
	private UserMsgMapper userMsgMapper;

	@Override
	public int saveExpertGoods(ExpertGoodsDto dto) {
		ExpertGoods po = new ExpertGoods();
		BeanUtils.copyProperties(dto,po);

		return expertGoodsMapper.insert(po);
	}

	@Override
	public int delById(Long id) {
		expertGoodsMapper.deleteByPrimaryKey(id);
		return 1;
	}

	@Override
	public ExpertGoodsDto getExpertGoods(Long id) {
		ExpertGoods po = expertGoodsMapper.selectByPrimaryKey(id);
		
		ExpertGoodsDto dto = new ExpertGoodsDto();
		BeanUtils.copyProperties(po, dto);

		return dto;
	}

	@Override
	public PageResultDto<ExpertGoodsDto> findByPage(ExpertGoodsDto dto,
			Integer page, Integer size) {
		
		ExpertGoods po = new ExpertGoods();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<ExpertGoods> pageQuery = new PageQueryDto<ExpertGoods>(po, page,
				size);
		ReturnPage<ExpertGoods> popageResult = expertGoodsMapper.findByPage(pageQuery);

		List<ExpertGoodsDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<ExpertGoodsDto> dtoPageresult = new PageResultDto<ExpertGoodsDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}
	
	private List<ExpertGoodsDto> phraseList(List<ExpertGoods> poList) {

		List<ExpertGoodsDto> dtoList = new ArrayList<ExpertGoodsDto>();
		for (ExpertGoods po : poList) {
			ExpertGoodsDto dto = new ExpertGoodsDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}


	@Override
	public int check(Long id, Short status, String msg,String tinyurl,String subhead) {
		ExpertGoods po = new ExpertGoods();
		po.setId(id);
		po.setStatus(status);
		po.setTinyUrl(tinyurl);
		po.setSubhead(subhead);

		
		expertGoodsMapper.updateByPrimaryKeySelective(po);
		
		return 1;
	}

	@Override
	public int check(Long id, Short status) {
		ExpertGoods po = new ExpertGoods();
		po.setId(id);
		po.setStatus(status);
		
		expertGoodsMapper.updateByPrimaryKeySelective(po);
		
		return 1;
	}

	@Override
	public List<CategoryDto> classificationone() {
		return expertGoodsMapper.classificationone();
	}

	@Override
	public List<CategoryDto> classificationtwo(int id) {
		return expertGoodsMapper.classificationtwo(id);
	}

	@Override
	public List<CategoryDto> classificationthree(int id) {
		return expertGoodsMapper.classificationthree(id);
	}

	@Override
	public void savemodify(int id, int cat_id, String url) {
		expertGoodsMapper.savemodify(id, cat_id, url);
	}

	@Override
	public String selectcatId(int catId) {
		return expertGoodsMapper.selectcatId(catId);
	}
	
	@Override
	public String selectuid(int uid) {
		return expertGoodsMapper.selectuid(uid);
	}

	@Override
	public Long selectusername(String username) {
		return expertGoodsMapper.selectusername(username);
	}

	@Override
	public void saverelated_goods(int gid, int type, String url, String name, String pic_def) {
		expertGoodsMapper.saverelated_goods(gid, type, url, name, pic_def);
	}
}
