package com.bilibili.manage.mapper;

import com.bilibili.manage.pojo.User;

import java.util.List;

public interface UserMapper {
	
	List<User> findUserAll(); //定义测试方法，查询全部用户信息
}
