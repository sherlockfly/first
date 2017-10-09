package com.drbwx.admin.service;

import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.GoodsCommentDto;

public interface GoodsCommentService {
	
	public void save(GoodsCommentDto dto);
	
	public void update(GoodsCommentDto dto);
	
	public PageResultDto<GoodsCommentDto> findByPage(GoodsCommentDto dto, Integer page, Integer size);
	
	public GoodsCommentDto getInfo(Long id);
	
    public int Delectone(int id);
	
	public int Recoveryone(int id);
}
