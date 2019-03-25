package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{

	List<Item> findAll();	//查询全部商品信息

	int findItemCount();	//查询商品总数
	
	List<Item> findItemByPage(@Param("beginNum") int beginNum, @Param("rows") int rows); //根据分页查询
	
	String queryItemCatName(Long itemCatId);	//通过itemCatId获取分类名称
	
	void updateStatus(@Param("ids") Long[] ids, @Param("status") int status);	//修改状态信息
	
}
