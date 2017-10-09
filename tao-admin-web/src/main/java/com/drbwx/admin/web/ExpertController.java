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
import com.drbwx.admin.common.CommonStatusEnum;
import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ExpertDto;
import com.drbwx.admin.service.ExpertService;

/**
 * 
 * @author zhaolin
 *
 */

@RequestMapping("expert")
@Controller
public class ExpertController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@Autowired
	ExpertService expertService;
	
	//index
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		//getFunctions(request, mv,MenuConstant.YONGHUGUANLI);
		
		mv.setViewName("/expert/index");
		return mv;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<ExpertDto> list(ExpertDto dto,Integer page,Integer rows){
		dto.setStatus(CommonStatusEnum.keyong.getStatus());
		PageResultDto<ExpertDto> pageResult = expertService.findInfoByPage(dto, page, rows);
		
		return pageResult;
	}
	
	//修改状态
	@RequestMapping("update")
	public AjaxResult updateStatus(ExpertDto expertDto,HttpServletRequest request){
		CurrentOper oper = getCurrentOper(request);
		
		expertService.updateExpert(expertDto);
		log.info("oper" + oper.getRealName() + "修改 expert uid-" + oper.getUserid() + "-status:" + expertDto.getStatus());
		
		return new AjaxResult();
	}
	
}
