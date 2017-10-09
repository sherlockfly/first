package com.drbwx.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.InformDto;
import com.drbwx.admin.service.InformService;

@Controller
@RequestMapping("inform")
public class InformController extends BaseController {
	
	@Autowired
	private InformService informService;
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/article/Inform");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<InformDto> list(InformDto dto,Integer page,Integer rows,String username){
		PageResultDto<InformDto> pageResult = informService.findByPage(dto, page,rows);
		return pageResult;
	}
	
	/**改变状态，1已处理   2未处理
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/change")
	public AjaxResult change(int id,int status){
		informService.change(id, status);
		return new AjaxResult(200,"操作成功");
	}
	
	

}
