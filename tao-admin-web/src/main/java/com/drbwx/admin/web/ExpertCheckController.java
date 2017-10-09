package com.drbwx.admin.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.ApplyStatusEnum;
import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ExpertApplyDto;
import com.drbwx.admin.service.ExpertApplyService;

/**
 * 暂时不许要审核了， 所有注册用户都可以发表	
 * @Description
 * @author zhaolin
 * @2017年1月8日
 */
@Controller
@RequestMapping("expertcheck")
public class ExpertCheckController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ExpertApplyService expertApplyService;
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		//getFunctions(request, mv,MenuConstant.YONGHUGUANLI);
		
		mv.setViewName("/expert/check");
		return mv;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<ExpertApplyDto> list(ExpertApplyDto dto,Integer page,Integer rows){
		dto.setStatus(ApplyStatusEnum.shenhezhong.getStatus());
		PageResultDto<ExpertApplyDto> pageResult = expertApplyService.findExpert(dto, page, rows);
		
		return pageResult;
	}
	
	@RequestMapping("savecheck")
	@ResponseBody
	public AjaxResult check(HttpServletRequest request,ExpertApplyDto dto){
		
		CurrentOper oper = getCurrentOper(request);
		Date now = new Date();
		
		dto.setVerifyTime(now);
		//更新状态
/*		ExpertApplyActionDto actionDto = new ExpertApplyActionDto();
		actionDto.setActionDate(new Date());
		actionDto.setApplyId(dto.getId());
		actionDto.setUid(dto.getUid());
		actionDto.setVerifyId(Long.parseLong(oper.getUserid()));
		actionDto.setVerifyName(oper.getLoginName());
		actionDto.setVerifyStatus(dto.getStatus());*/
		dto.setVerifier(oper.getRealName());
		expertApplyService.updateApplyExpert(dto,null);
		
		log.info("审核达人- 管理员："+oper.getRealName() +" applyId:" + dto.getId() );
		
		return new AjaxResult(200,"操作成功");
	}
	
}
