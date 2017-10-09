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
import com.drbwx.admin.dto.SysMsgDto;
import com.drbwx.admin.service.SysMsgService;

/**
 * 系统消息管理	
 * @Description
 * @author zhaolin
 * @2017年1月9日
 */
@Controller
@RequestMapping("sysMsg")
public class SystemMsgController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysMsgService sysMsgService;
	
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.XITONGXIAOXIGUANLI);
		
		mv.setViewName("/msg/sysmsg");
		return mv;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<SysMsgDto> list(SysMsgDto dto,Integer page,Integer rows){
		
		PageResultDto<SysMsgDto> pageResult = sysMsgService.findUserMsg(dto, page, rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public AjaxResult edit(HttpServletRequest request,SysMsgDto dto){
		sysMsgService.updateUserMsg(dto);
		
		log.info(getCurrentOper(request).getRealName() + "修改 " +dto.getTitle());
		
		return new AjaxResult();
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult add(HttpServletRequest request,SysMsgDto dto){
		
		dto.setCreator(getCurrentOper(request).getRealName());
		dto.setCreatDt(new Date());
		sysMsgService.saveUserMsg(dto);
		
		log.info(getCurrentOper(request).getRealName() + "添加 " +dto.getTitle());
		
		return new AjaxResult();
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public AjaxResult del(HttpServletRequest request,SysMsgDto dto){
		sysMsgService.delSysMsg(dto.getId());
		
		log.info(getCurrentOper(request).getRealName() + "删除 " +dto.getTitle());
		
		return new AjaxResult();
	}
}
