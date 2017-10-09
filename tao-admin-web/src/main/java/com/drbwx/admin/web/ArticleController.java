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
	
	/**会选审核页面
	 * @author zpf   
	 * @2017.10.8
	 */
	@RequestMapping("UnCheckIndex")
	public ModelAndView indextwo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/article/articlecheck");
		
		return mv;
	}
	
	/**举报管理 ，你说你要改我就写这儿来了，变量也随便写得 
	 * @author zpf   
	 * @2017.10.8
	 */
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
			//判断输入的suername是否存在
			Long uid = articleService.selectusername(username);
			if(uid==null){
				uid = (long) 0;
			}
			dto.setUid(uid);
		}
		
		
		PageResultDto<ArticleDto> pageResult = articleService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	/**会选审核列表
	 * @author zpf 
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/UnCheckList")
	public PageResultDto<ArticleDto> listtwo(ArticleDto dto, Integer page, Integer rows, String username) {
		dto.setStatus((byte) 1);
		if (username != "" && username != null) {
			Long uid = articleService.selectusername(username);
			if (uid == null) {
				uid = (long) 0;
			}
			dto.setUid(uid);
		}

		PageResultDto<ArticleDto> pageResult = articleService.findByPage(dto, page, rows);

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
	
	/**分类下拉 一级
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/CategoryOne")
	public Object CategoryOne(){
		int id = 2046;
		return articleService.Category(id);
	}
	/**分类下拉 二级
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/CategoryTwo")
	public Object CategoryTwo(int id){
		return articleService.Category(id);
	}
	/**保存修改分类
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/savemodify")
	public AjaxResult savemodify(int id,int cat_id){		
		articleService.savemodify(id,cat_id);
		return new AjaxResult(200,"操作成功");
	}
	/**添加到inform，未完成
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/saveAddone")
	public AjaxResult saveAddone(int gid,int type,String url,String name){
		String pic_def = "未完成";
		articleService.saverelated_goods(gid, type, url, name, pic_def);
		return new AjaxResult(200,"操作成功");
	}
}
