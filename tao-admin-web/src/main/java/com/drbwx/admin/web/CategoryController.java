package com.drbwx.admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.CategoryDto;
import com.drbwx.admin.service.CategoryService;

/**
 * 
 * <p>Description: <／p>
 * <p>Company: drbwx<／p>
 * @author zhaolin
 * @date 2016年11月1日
 * @version 1.0
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		getFunctions(request, mv,MenuConstant.YONGHUGUANLI);
		mv.setViewName("/category/index");
		return mv;
	}
	/**
	 * 根据父分类ID查询下一级分类
	 * @param id 分类ID
	 * @return JSONObject类型
	 * @createTime 2017-3-4 下午2:59:18
	 * @author liuxushan@dongao.com
	 */
	@ResponseBody
	@RequestMapping(path = "/findChildren", produces = "text/html;charset=UTF-8")
	public String findChildren(Integer id){
		CategoryDto dto = new CategoryDto();
		dto.setStatus(Byte.parseByte("1"));
		if(id ==null){
			dto.setGrade(Byte.parseByte("1"));
		}else{
			dto.setPid(id);
		}
		List<CategoryDto> findByWhere = categoryService.findByWhere(dto);
		String jsonString = JSONObject.toJSONString(findByWhere);
		return jsonString;
	}
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<CategoryDto> list(CategoryDto dto,Integer page,Integer rows){
		
		PageResultDto<CategoryDto> pageResult = categoryService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public AjaxResult edit(HttpServletRequest request,CategoryDto dto){
		categoryService.update(dto);
		
		log.info(getCurrentOper(request).getRealName() + "修改 " +dto.getName());

		
		return new AjaxResult();
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public AjaxResult add(HttpServletRequest request,CategoryDto dto){
		
		categoryService.save(dto);
		
		log.info(getCurrentOper(request).getRealName() + "添加 " +dto.getName());
		
		return new AjaxResult();
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public AjaxResult del(HttpServletRequest request,Integer id){
		categoryService.del(id);
		log.info(getCurrentOper(request).getRealName() + "删除 " +id);
		return new AjaxResult();
	}
}
