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
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.UserMsgDto;
import com.drbwx.admin.service.UserMsgService;

/**
 * 	
 * @Description
 * @author zhaolin
 * @2017年1月9日
 */
@Controller
@RequestMapping("userMsg")
public class UserMsgController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserMsgService userMsgService;
	
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.GERENXIAOXIGUANLI);
		
		mv.setViewName("/msg/usermsg");
		return mv;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<UserMsgDto> list(UserMsgDto dto,Integer page,Integer rows){
		
		PageResultDto<UserMsgDto> pageResult = userMsgService.findUserMsg(dto, page, rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public AjaxResult edit(HttpServletRequest request,UserMsgDto dto){
		userMsgService.updateUserMsg(dto);
		
		log.info(getCurrentOper(request).getRealName() + "修改 " +dto.getTitle());

		
		return new AjaxResult();
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult add(HttpServletRequest request,UserMsgDto dto){
		dto.setCreatDt(new Date());
		dto.setCreator(getCurrentOper(request).getRealName() );
		
		userMsgService.saveUserMsg(dto);
		
		log.info(getCurrentOper(request).getRealName() + "添加 " +dto.getTitle());
		
		return new AjaxResult();
	}

}
