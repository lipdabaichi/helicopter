package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//实现页面跳转    采用restFul结构接收参数
	@RequestMapping("/page/{moduleName}")
	public String index(@PathVariable String moduleName){
		
		return moduleName;
	}
}
