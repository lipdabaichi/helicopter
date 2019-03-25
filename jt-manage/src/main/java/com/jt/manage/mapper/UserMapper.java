package com.jt.manage.mapper;

import java.util.List;

import com.jt.manage.pojo.User;

public interface UserMapper {
	
	List<User> findUserAll(); //定义测试方法，查询全部用户信息
}
