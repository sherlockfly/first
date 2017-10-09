package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.ArticleCommentMapper;
import com.drbwx.admin.dto.ArticleCommentDto;
import com.drbwx.admin.po.ArticleComment;
import com.drbwx.admin.po.GoodsComment;
import com.drbwx.admin.service.ArticleCommentService;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
	
	@Autowired
	private ArticleCommentMapper articleCommentMapper;

	@Override
	public void save(ArticleCommentDto  dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ArticleCommentDto dto) {
		ArticleComment po = new ArticleComment();
		BeanUtils.copyProperties(dto, po);

		articleCommentMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public PageResultDto<ArticleCommentDto> findByPage(ArticleCommentDto dto,
			Integer page, Integer size) {
		ArticleComment po = new ArticleComment();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<ArticleComment> pageQuery = new PageQueryDto<ArticleComment>(po, page,
				size);

		ReturnPage<ArticleComment> popageResult = articleCommentMapper.findByPage(pageQuery);

		List<ArticleCommentDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<ArticleCommentDto> dtoPageresult = new PageResultDto<ArticleCommentDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}

	private List<ArticleCommentDto> phraseList(List<ArticleComment> poList) {

		List<ArticleCommentDto> dtoList = new ArrayList<ArticleCommentDto>();
		for (ArticleComment po : poList) {
			ArticleCommentDto dto = new ArticleCommentDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public ArticleCommentDto getInfo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Delectone(int id) {
		return articleCommentMapper.Delectone(id);
	}

	@Override
	public int Recoveryone(int id) {
		return articleCommentMapper.Recoveryone(id);
	}

	
	
}
