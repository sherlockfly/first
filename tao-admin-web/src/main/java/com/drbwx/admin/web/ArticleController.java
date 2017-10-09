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

import com.alibaba.fastjson.JSONObject;
import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.common.MenuConstant;
import com.drbwx.admin.common.PageResultDto;
import com.drbwx.admin.dto.ArticleDto;
import com.drbwx.admin.service.ArticleService;

/**
 * 	
 * @Description 会选文章
 * @author zhaolin
 * @2017年1月9日
 */
@Controller
@RequestMapping("huixuan")
public class ArticleController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/article/articleindex");
		
		return mv;
	}
	
	@RequestMapping("indextwo")
	public ModelAndView indextwo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/article/articleindextwo");
		
		return mv;
	}
	
	@RequestMapping("indexthree")
	public ModelAndView indexthree(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/article/Inform");
		
		return mv;
	}
	
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<ArticleDto> list(ArticleDto dto,Integer page,Integer rows,String username){
		if(username!=""&&username!=null){
			Long uid = articleService.selectusername(username);
			if(uid==null){
				uid = (long) 0;
			}
			dto.setUid(uid);
		}
		
		
		PageResultDto<ArticleDto> pageResult = articleService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
		@ResponseBody
		@RequestMapping("/listtwo")
		public PageResultDto<ArticleDto> listtwo(ArticleDto dto,Integer page,Integer rows,String username){
			if(username!=""&&username!=null){
				Long uid = articleService.selectusername(username);
				if(uid==null){
					uid = (long) 0;
				}
				dto.setUid(uid);
			}
			
			PageResultDto<ArticleDto> pageResult = articleService.findByPage(dto, page,rows);
			
			return pageResult;
		}
	
	
	//审核
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(HttpServletRequest request,ArticleDto dto){
		dto.setUpdDt(new Date());
		CurrentOper oper = getCurrentOper(request);
		
		articleService.check(dto.getId(), dto.getStatus(), "","");
		
		log.info(oper.getRealName()+"修改" + dto.getId() + "-status:" + dto.getStatus());
		
		return new AjaxResult(200,"操作成功");
	}
	
	//查看
	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request,Long id){
		ModelAndView mv = new ModelAndView();
		ArticleDto article = articleService.getInfo(id);
		
		mv.addObject("article", article);
		mv.setViewName("/article/articlecheck");
		
		return mv;
	}	
	
	@ResponseBody
	@RequestMapping("/selectcatId")
	public JSONObject selectcatId(int catId){	
		JSONObject A = new JSONObject();
		A.put("key", articleService.selectcatId(catId));
		return A;
	}
	
	
	@ResponseBody
	@RequestMapping("/selectuid")
	public JSONObject selectuid(int uid){	
		JSONObject A = new JSONObject();
		A.put("key", articleService.selectuid(uid));
		return A;
	}
	
	@ResponseBody
	@RequestMapping("/classificationone")
	public Object classificationone(){
		return articleService.classificationone();
	}
	
	@ResponseBody
	@RequestMapping("/classificationtwo")
	public Object classificationtwo(){
		int id = 2046;
		return articleService.classificationtwo(id);
	}
	
	@ResponseBody
	@RequestMapping("/classificationthree")
	public Object classificationthree(int id){
		return articleService.classificationthree(id);
	}
	
	@ResponseBody
	@RequestMapping("/savemodify")
	public AjaxResult savemodify(int id,int cat_id){		
		articleService.savemodify(id,cat_id);
		return new AjaxResult(200,"操作成功");
	}
	
	@ResponseBody
	@RequestMapping("/saveAddone")
	public AjaxResult saveAddone(int gid,int type,String url,String name){
		String pic_def = "未完成";
		articleService.saverelated_goods(gid, type, url, name, pic_def);
		return new AjaxResult(200,"操作成功");
	}
}
