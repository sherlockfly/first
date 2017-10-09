package com.drbwx.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.zookeeper.server.quorum.Election;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drbwx.admin.dao.CategoryMapper;
import com.drbwx.admin.po.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CategoryTest {

	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	public void testCat() throws IOException, BadHanyuPinyinOutputFormatCombination{
		File file = new File("F:/html/tb-category/淘宝首页行业市场.html");
	 	Document doc=Jsoup.parse(file, "UTF-8");
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		
		Date now = new Date();
		 //获取一级分类
/*         Elements oneCats=doc.getElementsByClass("J_category_hash");
         
         int i = 1;
         for(Element e:oneCats){
        	 String catname = e.text();
        	 Category cat = new Category();
        	 cat.setCode(PinyinHelper.toHanYuPinyinString(catname,defaultFormat,"", false));
        	 cat.setCreator("真管");
        	 cat.setCreatTime(now);
        	 cat.setGrade((byte)1);
        	 cat.setName(catname);
        	 cat.setSort(i);
        	 cat.setStatus((byte)1);
        	 
        	 CategoryMapper.insert(cat);
             i++;
         }*/
         
         //获取所有二级分类
         Elements twoCats=doc.getElementsByClass("category-list-item");
         int tid = 1963;
		 for(int i = 180;i < 189; i++){
			 
			tid++;
			int id2 = tid;
			Element e = twoCats.get(i);
			 
			String cat = e.text();
			
			System.out.println("cat"+cat);			
			String[] strs = cat.split(" ");
			String name2 = strs[0];
			String code2 = PinyinHelper.toHanYuPinyinString(name2,defaultFormat,"", false);
			Category cat2 = new Category();
			cat2.setCode(code2);
			cat2.setName(name2);
			cat2.setCreator("真管");
			cat2.setGrade((byte)2);
			cat2.setCreatTime(now);
			cat2.setName(name2);
			
			/*  需要改  -----   */
			cat2.setPid(17); 
			cat2.setpCode("nongzicaigou");
			
			/*   end -----   */
			
			cat2.setSort(i+1);
			cat2.setStatus((byte)1);
			
			cat2.setId(tid);
			
			System.out.println(cat2.toString());
			categoryMapper.insertSelective(cat2);
			for(int j = 1;j < strs.length; j++){
				tid++;
				Category cat3 = new Category();
				
				String cat3name = strs[j]; 
				String cat3code = PinyinHelper.toHanYuPinyinString(cat3name,defaultFormat,"", false);
				
				cat3.setCode(cat3code);
				cat3.setName(cat3name);
				cat3.setCreator("真管");
				cat3.setGrade((byte)3);
				cat3.setCreatTime(now);
				cat3.setPid(id2);
				cat3.setpCode(code2);
				cat3.setSort(j);
				cat3.setStatus((byte)1);
				cat3.setId(tid);
				
				System.out.println(cat3.toString());
				categoryMapper.insertSelective(cat3);
			}
		 }
	}
}
