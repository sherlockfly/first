package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.ArticleMapper;
import com.drbwx.admin.dto.ArticleDto;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.po.Article;
import com.drbwx.admin.service.ArticleService;

/**
 * 会选文章	
 * @Description
 * @author zhaolin
 * @2017年1月10日
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public int save(ArticleDto dto) {
		Article po = new Article();
		BeanUtils.copyProperties(dto,po);

		return articleMapper.insert(po);
	}

	@Override
	public int delById(Long id) {
		articleMapper.deleteByPrimaryKey(id);
		return 1;
	}

	@Override
	public ArticleDto getInfo(Long id) {
		Article po = articleMapper.selectByPrimaryKey(id);
		
		ArticleDto dto = new ArticleDto();
		BeanUtils.copyProperties(po, dto);

		return dto;
	}

	@Override
	public PageResultDto<ArticleDto> findByPage(ArticleDto dto,
			Integer page, Integer size) {
		Article po = new Article();
		BeanUtils.copyProperties(dto, po);

		PageQueryDto<Article> pageQuery = new PageQueryDto<Article>(po, page,
				size);
		ReturnPage<Article> popageResult = articleMapper.findByPage(pageQuery);

		List<ArticleDto> dtolist = phraseList(popageResult.getObjList());

		PageResultDto<ArticleDto> dtoPageresult = new PageResultDto<ArticleDto>(
				popageResult.getTotalCount(), dtolist);

		return dtoPageresult;
	}
	
	private List<ArticleDto> phraseList(List<Article> poList) {

		List<ArticleDto> dtoList = new ArrayList<ArticleDto>();
		for (Article po : poList) {
			ArticleDto dto = new ArticleDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}


	@Override
	public int check(Long id, Byte status, String beizhu,String subTitle) {
		Article po = new Article();
		po.setId(id);
		po.setStatus(status);
		
		articleMapper.updateByPrimaryKeySelective(po);
		
		return 1;
	}

	@Override
	public List<CategoryDto> Category(int id) {
		return articleMapper.Category(id);
	}

	@Override
	public void savemodify(int id, int cat_id) {
		articleMapper.savemodify(id, cat_id);
	}
	
	@Override
	public Long selectusername(String username) {
		return articleMapper.selectusername(username);
	}

	@Override
	public void saverelated_goods(int gid, int type, String url, String name, String pic_def) {
		articleMapper.saverelated_goods(gid, type, url, name, pic_def);
	}
	

}	
