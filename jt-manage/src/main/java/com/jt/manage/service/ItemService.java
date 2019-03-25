package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

public interface ItemService {
	public List<Item> findAll();
	
	//定义返回值类型  并且传递分页参数
	public EasyUIResult findItemAll(int page, int rows);
	
	//通过ItemCatId获取商品分类名称
	public String queryItemCatName(Long itemCatId);
	
	//测试方法
	public int TestFindCount();
	
	//商品新增
	public void saveItem(Item item, String desc);
	
	//商品的修改
	public void updateItem(Item item, String desc);
	
	//商品的删除
	public void deleteItems(Long[] ids);
	
	//修改商品状态信息
	public void updateStatus(Long[] ids, int status);

	ItemDesc findItemDescById(Long itemId);
}
