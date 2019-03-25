package com.bilibili.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("userOrder")
@Controller
public class UserOrderController {
   /* @Autowired
    UserAdminService userAdminService;*/

    @RequestMapping("doUserOrderListUI")
    public String doUserOrderListUI(){
        return "userAdmin/userOrder_list";
    }

    @RequestMapping("doUserEditUI")
    public String doUserEditUI(){
        return "userAdmin/user_edit";
    }
    @RequestMapping("doPwdEditUI")
    public String doPwdEditUI(){
        return "userAdmin/pwd_edit";
    }

    /*@RequestMapping("doUpdatePassword")
    @ResponseBody
    public JsonResult doUpdatePassword(
            String pwd,String newPwd,String cfgPwd){
        userAdminService.updatePassword(pwd,
                newPwd, cfgPwd);
        return new JsonResult("update ok");
    }

    @RequestMapping("doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(
            Integer id){
        Map<String,Object> map=
                userAdminService.findObjectById(id);
        return new JsonResult(map);
    }*/

}