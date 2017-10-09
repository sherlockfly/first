package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.GoodsCommentMapper;
import com.drbwx.admin.dto.GoodsCommentDto;
import com.drbwx.admin.po.GoodsComment;
import com.drbwx.admin.service.GoodsCommentService;

@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {
	
	@Autowired
	private GoodsCommentMapper goodsCommentMapper;

	@Override
	public void save(GoodsCommentDto dto) {
		GoodsComment po = new GoodsComment();
		BeanUtils.copyProperties(dto,po);

		goodsCommentMapper.insert(po);
	}

	@Override
	public void update(GoodsCommentDto dto) {
		GoodsComment po = new GoodsComment();
		BeanUtils.copyProperties(dto, po);

		goodsCommentMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public PageResultDto<GoodsCommentDto> findByPage(
			GoodsCommentDto dto, Integer page, Integer size) {
		GoodsComment po = new GoodsComment();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<GoodsComment> pageQuery = new PageQueryDto<GoodsComment>(po, page,
				size);

		ReturnPage<GoodsComment> popageResult = goodsCommentMapper.findByPage(pageQuery);

		List<GoodsCommentDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<GoodsCommentDto> dtoPageresult = new PageResultDto<GoodsCommentDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<GoodsCommentDto> phraseList(List<GoodsComment> poList) {

		List<GoodsCommentDto> dtoList = new ArrayList<GoodsCommentDto>();
		for (GoodsComment po : poList) {
			GoodsCommentDto dto = new GoodsCommentDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}
	@Override
	public GoodsCommentDto getInfo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Delectone(int id) {
		return goodsCommentMapper.Delectone(id);
	}

	@Override
	public int Recoveryone(int id) {
		return goodsCommentMapper.Recoveryone(id);
	}

}
