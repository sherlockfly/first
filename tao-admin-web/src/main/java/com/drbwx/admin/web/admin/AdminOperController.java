package com.drbwx.admin.web.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.AdminOperDto;
import com.drbwx.admin.service.AdminOperService;
import com.drbwx.admin.web.BaseController;

@Controller
@RequestMapping("oper")
public class AdminOperController extends BaseController{
	
	@Autowired
	private AdminOperService adminOperService;
	
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.YONGHUGUANLI);
		
		mv.setViewName("/auth/operindex");
		return mv;
	}
	
	//添加用户
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(HttpServletRequest request,AdminOperDto operDto){
		operDto.setCreateDt(new Date());
		//operDto.setPwd(MD5Util.stringMD5(operDto.getPwd()));
		//校验用户名重复
		AdminOperDto querydto = new AdminOperDto();
		querydto.setLoginName(operDto.getLoginName());
		
		List list = adminOperService.findByWhere(querydto);
		if(list != null && list.size() > 0){
			return new AjaxResult(300,"登录名已存在");
		}
		adminOperService.save(operDto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//更新
	@ResponseBody
	@RequestMapping("/update")
	public AjaxResult update(AdminOperDto operDto){
		adminOperService.update(operDto);
		
		return new AjaxResult(200,"操作成功");
	}
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<AdminOperDto> list(AdminOperDto dto,Integer page,Integer rows){
		
		PageResultDto<AdminOperDto> pageResult = adminOperService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	//update
	@ResponseBody
	@RequestMapping("/modifyPwd")
	public AjaxResult modifyPwd(int id){
		
		AjaxResult result = new AjaxResult();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public AjaxResult del(String id,HttpServletRequest request){
		CurrentOper cuser = getCurrentOper(request);
		if(id.equals(cuser.getUserid())){
			return new AjaxResult(300,"不能删除当前登录用户");
		}
		
		adminOperService.del(id);
		
		AjaxResult result = new AjaxResult();
		
		return result;
	} 

	@ResponseBody
	@RequestMapping("/updpwd")
	public AjaxResult updpwd(HttpServletRequest request,String oldpwd,String pwd){
		AjaxResult result = new AjaxResult();
		
		CurrentOper user = getCurrentOper(request);
		
		AdminOperDto operDto = new AdminOperDto();
		operDto.setId(Integer.parseInt(user.getUserid()));
		operDto.setPwd(oldpwd);
		List<AdminOperDto> list = adminOperService.findByWhere(operDto);
		if(list==null||list.size()<1){
			result.setStatus(300);
			result.setMsg("旧密码输入错误");
			return result;
		}
		
		operDto.setPwd(pwd);
		adminOperService.update(operDto);
		
		return result;
	} 
	
}
