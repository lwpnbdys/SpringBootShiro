package com.lwp.controller;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lwp.pojo.User;
import com.lwp.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUserByName")
	@ResponseBody
	public String findUserByName(String name) {
		User user = userService.findUserByName(name);
		System.out.println(user);
		return "ok";
	}
	
	//测试thymeleaf
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model) {
		model.addAttribute("name","lwp");
		return "test";
	}
	
	@RequestMapping("/login")
	public String login(String name,String password,Model model) {
		/*
		 * 使用shiro编写认证操作
		 */
		//1.获取subject
		Subject subject = SecurityUtils.getSubject();
		
		//2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		
		//执行登录方法
		try {
			subject.login(token);
			//登录成功
			//跳转到test.html
			return "redirect:/testThymeleaf";
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			//登录失败：用户名不存在
			model.addAttribute("msg","用户不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			//登录失败：用户名不存在
			model.addAttribute("msg","密码错误");
			return "login";
		}
		
	}
	
	@RequestMapping("/unAuth")
	public String unAuth() {
		return "/unAuth";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "user/update";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "/login";
	}

}
