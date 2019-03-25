package com.bilibili.manage.service.impl;

import com.bilibili.manage.mapper.UserAdminDao;
import com.bilibili.manage.pojo.SysUser;
import com.bilibili.manage.service.UserAdminService;
import com.jt.common.exception.ArgumentException;
import com.jt.common.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserAdminServiceImpl implements UserAdminService {
    //此属性会基于spring中的DI机制进行赋值
    @Autowired
    private UserAdminDao userAdminDao;


    @Override
    public int updatePassword(String pwd, String newPwd, String cfgPwd) {
        System.out.println("#######service#########"+pwd+">>>>>>"+newPwd+">>>>>>>>>>>>>>>>>>>"+cfgPwd);
        //1.参数校验
        if(StringUtils.isEmpty(pwd))
            throw new ArgumentException("原密码不能为空");
        if(StringUtils.isEmpty(newPwd))
            throw new ArgumentException("新密码不能为空");
        if(StringUtils.isEmpty(cfgPwd))
            throw new ArgumentException("确认密码不能为空");
        if(!newPwd.equals(cfgPwd))
            throw new ArgumentException("两次输入的新密码不一致");
        //获得用户的密码
//        SysUser sysUser=(SysUser)
//                SecurityUtils.getSubject().getPrincipal();
//
//        if(sysUser==null)
//            throw new ServiceException("请先登录");
//        String sourcePwd=sysUser.getPassword();
//        SimpleHash sHash=new SimpleHash("MD5",
//                pwd, sysUser.getSalt(), 1);
//        String hashPwd=sHash.toHex();
//        if(!sourcePwd.equals(hashPwd)) {
//            throw new ServiceException("输入的原密码不正确");
//        }
//        //3.更新密码
//        String newSalt= UUID.randomUUID().toString();
//        sHash=new SimpleHash("MD5",newPwd,newSalt,1);
//        int rows=userAdminDao.updatePassword(
//                sysUser.getUsername(),
//                sHash.toHex(),
//                newSalt);

        /** 临时性步骤*/
        String sourcePwd = userAdminDao.queryPasswordByUsername("admin");  //写死的 admin
        if(!sourcePwd.equals(pwd)) {
            throw new ServiceException("输入的原密码不正确");
        }
        String newSalt= UUID.randomUUID().toString();
        int rows = userAdminDao.updatePassword("admin", newPwd, newSalt);

        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }
}
