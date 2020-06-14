package com.example.app.controller;

import javax.annotation.Resource;

import com.example.app.entity.DevUser;
import com.example.app.service.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


//开发者登陆
@Controller
@RequestMapping("DevUser")
@SessionAttributes("")
public class DevUserController {
	@Resource
	private DevUserService devUserService;
	
	//跳转登陆页面
	@RequestMapping("login")
	public String login(){
		return "devlogin";
	}
	
	//执行登录方法
	@RequestMapping(value="dologin",method=RequestMethod.POST)
	public ModelAndView dologin(ModelAndView md,@RequestParam("devCode") String devCode,@RequestParam("devPassword") String devPassword){
		DevUser devUser = devUserService.DevLogin(devCode, devPassword);
		if (devUser!=null) {
			md.addObject("devUserSession", devUser);
			md.setViewName("developer/main");
		}else{
			md.addObject("error", "用户名或密码错误");
			md.setViewName("backendlogin");
		}
		return md;
	}
	
	
	//退出登录
	@RequestMapping("logout")
	public String logout(SessionStatus sessionStatus){
		sessionStatus.setComplete();
		return "redirect:../index.jsp";
	}
	

	
	
	

}
