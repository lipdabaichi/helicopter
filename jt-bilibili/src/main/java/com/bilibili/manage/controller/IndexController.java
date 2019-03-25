package com.bilibili.manage.controller;

import com.jt.common.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@Controller
public class IndexController {

    @RequestMapping("/userIndex")
    public String userIndex(){
        return "userAdmin/starter";
    }

    //实现页面跳转    采用restFul结构接收参数
    @RequestMapping("/{moduleName}")
    public String index(@PathVariable String moduleName){
        return "userAdmin/"+moduleName;
    }

    @RequestMapping("/aaa")
    @ResponseBody
    public String index1(){
        return "aaa";
    }

}