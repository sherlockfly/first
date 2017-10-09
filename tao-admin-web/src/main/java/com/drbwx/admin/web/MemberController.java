package com.drbwx.admin.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.UserDto;
import com.drbwx.admin.service.UserService;

/**
 * 
 * <p>Description: 会员管理<／p>
 * <p>Company: drbwx<／p>
 * @author zhaolin
 * @date 2016年11月10日
 * @version 1.0
 */
@Controller
@RequestMapping("member")
public class MemberController extends BaseController {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.HUIYUANGUANLI);
		
		mv.setViewName("/member/index");
		return mv;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<UserDto> list(UserDto dto,Integer page,Integer rows){
		
		PageResultDto<UserDto> pageResult = userService.findByPage(dto, page, rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public AjaxResult update(UserDto dto,HttpServletRequest request){
		
		CurrentOper oper = getCurrentOper(request);
		
		log.info("当前操作员:"+ oper.getRealName() + "修改userid: " + dto.getId());
		
		userService.updateUser(dto);
		return new AjaxResult();
	}
	
	@RequestMapping("/view")
	public ModelAndView view(Long uid,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(uid == null){
			
			return null;
		}
		
		UserDto dto = userService.getUser(uid);
		
		mv.addObject("user", dto);
		mv.setViewName("/member/view");
		
		return mv;
		
	}
	
	@ResponseBody
	@RequestMapping("/saveremark")
	public AjaxResult saveremark(int id,String myremark){
		userService.saveremark(id, myremark);
		return new AjaxResult();
	}
	
}
