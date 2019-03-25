package com.bilibili.manage.controller;

import com.bilibili.manage.service.UserAdminService;
import com.jt.common.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("userAdmin")
@Controller
public class UserAdminController {
    @Autowired
    UserAdminService userAdminService;

   @RequestMapping("doUpdatePassword")
   @ResponseBody
   public JsonResult doUpdatePassword(
           String pwd,String newPwd,String cfgPwd){
       System.out.println("################"+pwd+">>>>>>"+newPwd+">>>>>>>>>>>>>>>>>>>"+cfgPwd);
       userAdminService.updatePassword(pwd,
               newPwd, cfgPwd);
       return new JsonResult("update ok");
   }
   @RequestMapping("doLoginPasswordList")
   public String doLoginPasswordList() {
       return "userAdmin/userUpdateLoginPassword_list";
   }

    @RequestMapping("doUserAdminListUI")
    public String doUserAdminListUI(){
        return "userAdmin/userCentre_list";
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



    /*// 修改邮箱界面
    @RequestMapping("Update_email.sf")
    public String Update_email(HttpServletRequest request, String emial, String newemial) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || emial != null || newemial != null) {
            boolean bl = update_login_password_Service_Impl.Update_login_Emial(userName, emial, newemial);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro2", "对不起,原邮箱输入错误!");
            }
        } else {
            request.setAttribute("PassWordErro", "输入不能为空!");
        }

        return "Update_email";

    }

    // 修改手机界面
    @RequestMapping("Update_Phone.sf")
    public String Update_Phone(HttpServletRequest request, String userPhone, String newuserPhone) {
        // 得到session里面的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || userPhone != null || newuserPhone != null) {
            boolean bl = update_login_password_Service_Impl.Update_login_Phone(userName, userPhone, newuserPhone);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro3", "对不起,原手机号码错误!");
            }
        } else {
            request.setAttribute("PassWordErro3", "输入不能为空!");
        }
        return "Update_Phone";// User_full_information.sf

    }

    // 修改支付密码界面
    @RequestMapping("Update_PayPassword.sf")
    public String Update_PayPassword(String paypassword, String newpaypassword, String newpaypassword2,
                                     HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (paypassword != null || newpaypassword != null || newpaypassword2 != null) {
            if (newpaypassword.equals(newpaypassword2)) {
                boolean bl = update_login_password_Service_Impl.Update_login_payPassword(userName, paypassword,
                        newpaypassword);
                if (bl) {
                    return "User_full_information";
                } else {
                    request.setAttribute("PassWordErro5", "对不起,旧密码输入有误!");
                }
            } else {
                request.setAttribute("PassWordErro5", "两次密码输入有误!");
            }
        }
        return "Update_PayPassword";

    }*/

}