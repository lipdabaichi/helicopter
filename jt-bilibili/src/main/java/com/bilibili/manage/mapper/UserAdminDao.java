package com.bilibili.manage.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserAdminDao {
    int updatePassword(
            @Param("username")String username,
            @Param("newPwd")String newPwd,
            @Param("salt")String salt);

    String queryPasswordByUsername(String username);
}
