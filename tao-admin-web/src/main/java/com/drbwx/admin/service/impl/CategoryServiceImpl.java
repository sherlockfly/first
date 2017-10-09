package com.drbwx.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbwx.admin.common.PageQueryDto;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.common.ReturnPage;
import com.drbwx.admin.dao.CategoryMapper;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.po.Category;
import com.drbwx.admin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public int save(CategoryDto dto) {
		Category oper = new Category();
		BeanUtils.copyProperties(dto, oper);

		categoryMapper.insert(oper);
		return 1;
	}

	@Override
	public int del(Integer id) {
		
		return categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(CategoryDto dto) {
		Category po = new Category();
		BeanUtils.copyProperties(dto, po);

		return categoryMapper.updateByPrimaryKeySelective(po);
	}

	@Override
	public PageResultDto<CategoryDto> findByPage(CategoryDto dto, Integer page,
			Integer size) {
		Category po = new Category();
		BeanUtils.copyProperties(dto, po);
		PageQueryDto<Category> pageQuery = new PageQueryDto<Category>(po, page,
				size);
		System.out.println("cat service page:" + page);		
		System.out.println("cat service pageQuery:" + pageQuery.getCurrentPage());
		ReturnPage<Category> poPageResult = categoryMapper
				.findByPage(pageQuery);

		List<CategoryDto> dtoList = phraseList(poPageResult.getObjList());

		PageResultDto<CategoryDto> pageResult = new PageResultDto<CategoryDto>(
				poPageResult.getTotalCount(), dtoList);
		return pageResult;
	}

	@Override
	public List<CategoryDto> findByWhere(CategoryDto dto) {
		Category po = new Category();
		BeanUtils.copyProperties(dto, po);
		List<Category> list = categoryMapper.findByWhere(po);

		return phraseList(list);
	}

	private List<CategoryDto> phraseList(List<Category> poList) {

		List<CategoryDto> dtoList = new ArrayList<CategoryDto>();
		for (Category po : poList) {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(po, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

}
