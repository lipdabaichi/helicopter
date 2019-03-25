package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findAll")
	public String findUserAll(Model model){
		
		//从业务层获取数据
		List<User> userList = userService.findUserAll();
		//将数据存入Module对象中
		model.addAttribute("userList", userList);
		//跳转页面
		return "userList";
	}
}
