package com.drbwx.admin.service;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ArticleCommentDto;

public interface ArticleCommentService {
	
	public void save(ArticleCommentDto dto);
	
	public void update(ArticleCommentDto dto);
	
	public PageResultDto<ArticleCommentDto> findByPage(ArticleCommentDto dto, Integer page, Integer size);
	
	public ArticleCommentDto getInfo(Long id); 
	
	public int Delectone(int id);
	
	public int Recoveryone(int id);
}
