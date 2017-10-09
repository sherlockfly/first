package com.drbwx.admin.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drbwx.admin.common.AjaxResult;
import com.drbwx.admin.common.CommonStatusEnum;
import com.drbwx.admin.common.CurrentOper;
import com.drbwx.admin.dto.AdminFunctionDto;
import com.drbwx.admin.dto.AdminOperDto;
import com.drbwx.admin.service.AdminFunctionService;
import com.drbwx.admin.service.AdminOperService;
import com.drbwx.admin.service.AdminRoleService;

@Controller
public class LoginController extends BaseController {
	
	private  Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private AdminOperService adminOperService;

	@Autowired
	private AdminRoleService adminRoleService;

	@Autowired
	private AdminFunctionService adminFunctionService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {

		return "login";
	}

	// 登录
	@ResponseBody
	@RequestMapping("login")
	public AjaxResult login(HttpServletRequest request,
			HttpServletResponse response, String loginName, String passworld) {

		// 校验密码
		AdminOperDto operDto = new AdminOperDto();
		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passworld)) {
			log.info("用户名或密码错误 name:" + loginName + "-pass:" + passworld + " -ip:"+ getRemortIP(request));
			return new AjaxResult(400, "请填写用户名与密码");
		}
		operDto.setLoginName(loginName);
		operDto.setPwd(passworld);
		operDto.setStatus(Integer.valueOf(CommonStatusEnum.keyong.getStatus()));
		List<AdminOperDto> list = adminOperService.findByWhere(operDto);
		if (list == null || list.size() < 1) {
			log.info("用户名或密码错误 name:" + loginName + "-pass:" + passworld + " -ip:"+ getRemortIP(request));
			return new AjaxResult(400, "用户名或密码错误");
		}
		AdminOperDto oper = list.get(0);
		// 获取角色权限
		String roles = adminRoleService.findByOperId(oper.getId().toString());

		Map<String, String> funMap = new HashMap<String, String>();
		// 功能
		if (!StringUtils.isEmpty(roles) && roles.length() > 1) {
			List<AdminFunctionDto> functionLists = adminFunctionService
					.findByRoles(roles);

			for (AdminFunctionDto function : functionLists) {
				String code = function.getCode();
				String[] codes = code.split("-");

				if (funMap.get(codes[0]) == null) {
					funMap.put(codes[0], codes[1]);
				} else {
					String funStr = funMap.get(codes[0]);
					funStr += "," + codes[1];
					funMap.put(codes[0], funStr);
				}

			}
		}

		// 登录信息 redis
		CurrentOper user = new CurrentOper();
		user.setUserid(oper.getId().toString());
		user.setRealName(oper.getRealName());
		user.setLoginName(oper.getLoginName());
		user.setRoles(roles);
		user.setFunctionMap(funMap);

		cacheCurrentOper(request, response, user);
		// 设置最后登录时间 ip信息
		AdminOperDto adminOper = new AdminOperDto();
		adminOper.setId(oper.getId());
		adminOper.setLastLoginDt(new Date());
		adminOper.setLastLoginIp(getRemortIP(request));

		adminOperService.update(adminOper);

		return new AjaxResult();
	}

	// 登录
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {

		logoutOper(request, response);

		try {
			response.sendRedirect("login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
