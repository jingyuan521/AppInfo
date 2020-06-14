package com.example.app.controller;
//app管理者登陆页面
import com.example.app.dao.AppInfoMapper;
import com.example.app.entity.AppInfo;
import com.example.app.entity.BackendUser;
import com.example.app.entity.DTO.AppinfoDto;
import com.example.app.service.AppInfoService;
import com.example.app.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("manager")
@SessionAttributes("")
public class ManagerController {
	@Autowired
	@Qualifier("backendUserServiceImpl")
	private BackendUserService backendUserService;
	@Resource(name = "AppInfoServiceImpl")
	private AppInfoService appInfoService;
	
	//登录跳转
	@RequestMapping("login")
	public String login(){
		return "backendlogin";
	}


	//登录
	@RequestMapping("dologin")
	public ModelAndView doLogin(BackendUser backendUser, ModelAndView modelAndView){
		BackendUser userSession=backendUserService.adminLogin(backendUser);
		//开启userSession
		if (userSession!=null) {
			modelAndView.addObject("userSession", userSession);
			modelAndView.setViewName("backend/main");
		}else{
			//使用error
			modelAndView.addObject("error", "�û������������");
			modelAndView.setViewName("backendlogin");
		}
		return modelAndView;	
	}
	//退出
	@RequestMapping("logout")
	public String logout(SessionStatus sessionStatus){
		//移除session
		sessionStatus.setComplete();
		return "redirect:../index.jsp";
	}
}
