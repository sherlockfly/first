package com.drbwx.admin.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	public static String toString(Object obj){
		System.out.println(gson.toJson(obj));
		return gson.toJson(obj);
	}
	
	public static Object toObject(Class<?> clazz,String json){
		return gson.fromJson(json, clazz);
	}
	
/*	public static String toPageString(ReturnPage<?> page){
		String jsonString = toString(page.getObjList());
		String pageJsonString = "{\"total\":"+page.getTotalCount()+",\"rows\":"+jsonString+"}";
	System.out.println(pageJsonString);	
		return pageJsonString;
	}*/
	
	public static void main(String[] args) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("menuid","xxxx");
		map1.put("iocn","xxxx");
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("menuid","xxxx");
		map.put("iocn","xxxx");
		map.put("chilren",map1);
		
		List list = new ArrayList();
		list.add(map1);
		list.add(map);
		list.add(map);
	
	System.out.println(	JSONUtils.toJSONString(list));
	}
}
