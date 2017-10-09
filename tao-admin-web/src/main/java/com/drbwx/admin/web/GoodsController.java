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
import com.drbwx.admin.dto.ExpertGoodsDto;
import com.drbwx.admin.service.ExpertGoodsService;

@RequestMapping("goods")
@Controller
public class GoodsController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExpertGoodsService expertGoodsService;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/goods/goodsindex");
		mv.addObject("picRootPath","local");
		return mv;
	}
	
	@RequestMapping("/indextwo")
	public ModelAndView indextwo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/goods/goodsindextwo");
		mv.addObject("picRootPath","local");
		return mv;
	}
	
	
	//list
	@ResponseBody
	@RequestMapping("/list")
	public PageResultDto<ExpertGoodsDto> list(ExpertGoodsDto dto,Integer page,Integer rows,String username){
		
		if(username!=""&&username!=null){
			Long uid = expertGoodsService.selectusername(username);
			if(uid==null){
				uid = (long) 0;
			}
			dto.setUid(uid);
		}
		
		PageResultDto<ExpertGoodsDto> pageResult = expertGoodsService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	//list
	@ResponseBody
	@RequestMapping("/listtwo")
	public PageResultDto<ExpertGoodsDto> listtwo(ExpertGoodsDto dto,Integer page,Integer rows,String username){
		
		if(username!=""&&username!=null){
			Long uid = expertGoodsService.selectusername(username);
			if(uid==null){
				uid = (long) 0;
			}
			dto.setUid(uid);
		}
			
		PageResultDto<ExpertGoodsDto> pageResult = expertGoodsService.findByPage(dto, page,rows);
		
		return pageResult;
	}
	
	//审核
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(HttpServletRequest request,ExpertGoodsDto dto){
		dto.setUpdDt(new Date());
		CurrentOper oper = getCurrentOper(request);
		
		expertGoodsService.check(dto.getId(), dto.getStatus());
		//更改商品信息
		
		log.info(oper.getRealName()+"修改" + dto.getId() + "-status:" + dto.getStatus());
		
		
		
		return new AjaxResult(200,"操作成功");
	}
	
	
	
	//查看
	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request,Long id){
		ModelAndView mv = new ModelAndView();
		ExpertGoodsDto goods = expertGoodsService.getExpertGoods(id);
		
		mv.addObject("goods", goods);
		mv.setViewName("/goods/goodscheck");
		mv.addObject("picRootPath","local");
		
		return mv;
	}	
	
	@ResponseBody
	@RequestMapping("/classificationone")
	public Object classificationone(){
//		System.out.println("classificationone1");
//		List<CategoryDto> a =  expertGoodsService.classificationone();
//		
//		Iterator<CategoryDto> i = a.iterator();
//		CategoryDto b = i.next();
//		
//		System.out.println("A:"+ b.getId());
		return expertGoodsService.classificationone();
	}
	
	@ResponseBody
	@RequestMapping("/classificationtwo")
	public Object classificationtwo(){
		int id = 2046;
		return expertGoodsService.classificationtwo(id);
	}
	
	@ResponseBody
	@RequestMapping("/classificationthree")
	public Object classificationthree(int id){
		return expertGoodsService.classificationthree(id);
	}
	
	@ResponseBody
	@RequestMapping("/savemodify")
	public AjaxResult savemodify(int id,int cat_id,String url){		
		expertGoodsService.savemodify(id,cat_id,url);
		return new AjaxResult(200,"操作成功");
	}
	
	@ResponseBody
	@RequestMapping("/saveAddone")
	public AjaxResult saveAddone(int gid,int type,String url,String name){
		String pic_def = "未完成";
		expertGoodsService.saverelated_goods(gid, type, url, name, pic_def);
		return new AjaxResult(200,"操作成功");
	}
	
//	@ResponseBody
//	@RequestMapping("/selectcatId")
//	public JSONObject selectcatId(int catId){	
//		JSONObject A = new JSONObject();
//		A.put("key", expertGoodsService.selectcatId(catId));
//		return A;
//	}
//	
//	@ResponseBody
//	@RequestMapping("/selectuid")
//	public JSONObject selectuid(int uid){	
//		JSONObject A = new JSONObject();
//		A.put("key", expertGoodsService.selectuid(uid));
//		return A;
//	}
	
	
	
}
