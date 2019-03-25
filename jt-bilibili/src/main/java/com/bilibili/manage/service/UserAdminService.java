package com.bilibili.manage.service;

public interface UserAdminService {
    int updatePassword(String pwd,
                       String newPwd,String cfgPwd);

}
