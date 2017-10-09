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
	
	/**精选审核页面
	 * @author zpf   
	 * @2017.10.8
	 */
	@RequestMapping("/UnCheckIndex")
	public ModelAndView indextwo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		getFunctions(request, mv,MenuConstant.JINGXUANGUANLI);
		
		mv.setViewName("/goods/goodscheck");
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
	
	/**精选审核列表
	 * @author zpf   
	 * @2017.10.8
	 */
	@ResponseBody
	@RequestMapping("/UnCheckList")
	public PageResultDto<ExpertGoodsDto> listtwo(ExpertGoodsDto dto,Integer page,Integer rows,String username){
		dto.setStatus((short) 1);
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
	/**一级分类下拉
	 * @author zpf   
	 * @2017.10.8
	 */	
	@ResponseBody
	@RequestMapping("/CategoryOne")
	public Object CategoryOne(){
		int id = 2046;
		return expertGoodsService.Category(id);
	}
	/**二级分类下拉
	 * @author zpf   
	 * @2017.10.8
	 */	
	@ResponseBody
	@RequestMapping("/CategoryTwo")
	public Object CategoryTwo(int id){
		return expertGoodsService.Category(id);
	}
	/**保存分类URL修改
	 * @author zpf   
	 * @2017.10.8
	 */	
	@ResponseBody
	@RequestMapping("/savemodify")
	public AjaxResult savemodify(int id,int cat_id,String url){		
		expertGoodsService.savemodify(id,cat_id,url);
		return new AjaxResult(200,"操作成功");
	}
	/**保存到infrom，未完成
	 * @author zpf   
	 * @2017.10.8
	 */	
	@ResponseBody
	@RequestMapping("/saveAddone")
	public AjaxResult saveAddone(int gid,int type,String url,String name){
		String pic_def = "未完成";
		expertGoodsService.saverelated_goods(gid, type, url, name, pic_def);
		return new AjaxResult(200,"操作成功");
	}

}
