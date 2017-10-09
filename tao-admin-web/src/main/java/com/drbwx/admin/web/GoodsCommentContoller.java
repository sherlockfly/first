package com.drbwx.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.GoodsCommentDto;
import com.drbwx.admin.service.GoodsCommentService;

/**
 * 	
 * @Description 精选商品评论
 * @author zhaolin
 * @2017年1月9日
 */
@Controller
@RequestMapping("goodsComment")
public class GoodsCommentContoller extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GoodsCommentService goodsCommentService;
	
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.SHANGPINPINGLUN);
		
		mv.setViewName("/goods/comment");
		return mv;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<GoodsCommentDto> list(GoodsCommentDto dto,Integer page,Integer size){
		if(size == null ){
			size = 20;
		}
		PageResultDto<GoodsCommentDto> pageResult = goodsCommentService.findByPage(dto, page, size);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public AjaxResult edit(HttpServletRequest request,GoodsCommentDto dto){
		goodsCommentService.update(dto);
		
		log.info(getCurrentOper(request).getRealName() + "修改 " +dto.getId());

		
		return new AjaxResult();
	}
	/**状态改变，删除
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/Delectone")
	public AjaxResult Delectone(int id){
		goodsCommentService.Delectone(id);
		return new AjaxResult();
	}
	/**状态改变，恢复
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/Recoveryone")
	public AjaxResult Recoveryone(int id){
		goodsCommentService.Recoveryone(id);
		return new AjaxResult();
	}

}
