package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public List<Item> findAll() {
		
		return itemMapper.findAll();
		//return itemMapper.select(null);
	}

	/*@Override
	public EasyUIResult findItemAll(int page, int rows) {
		
		//查询商品记录总数
		int total = itemMapper.findItemCount();
		*//**
		 * 手动完成分页    
		 * 第1页    0-20条数据   select * from tb_item order by updated desc limit 0,20
		 * 第2页    20-40条数据 select * from tb_item order by updated desc limit 20,20
		 * 第3页    40-60条数据 select * from tb_item order by updated desc limit 40,20
		 * 第N页	 (page-1) * rows 
		 *//*
		int beginNum = (page - 1) * rows;
		List<Item> itemList = itemMapper.findItemByPage(beginNum,rows);
		
		return new EasyUIResult(total, itemList);
	}*/
	
	@Override
	public EasyUIResult findItemAll(int page, int rows) {
		//引入分页插件
		PageHelper.startPage(page, rows);
		/**
		 * 分页插件 已经实现分页查询
		 */
		List<Item> itemList = itemMapper.findAll();
		
		//设定分页参数
		PageInfo<Item> itemInfo = new PageInfo<Item>(itemList);
		return new EasyUIResult(itemInfo.getTotal(), itemInfo.getList());
	}
	
	
	@Override
	public String queryItemCatName(Long itemCatId) {
		// 
		return itemMapper.queryItemCatName(itemCatId);
	}

	@Override
	public int TestFindCount() {
		//return itemMapper.TextFindCount();        // ?????
		return 1;
	}

	@Override
	public void saveItem(Item item,String desc) {
		//同时入库2张表
		item.setStatus(1);  //表示状态正常   //1.上架状态  2下架
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		/**
		 * 通用Mapper动态获取主键
		 * Executing: SELECT LAST_INSERT_ID()
		 */
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		
		itemMapper.insert(item); //新增完成后一定有id
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item,String desc) {
		item.setUpdated(new Date());
		//表示动态修改
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void deleteItems(Long[] ids) {
		
		itemMapper.deleteByIDS(ids);
	}
	
	//商品的下架
	@Override
	public void updateStatus(Long[] ids, int status) {
		
		itemMapper.updateStatus(ids,status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
}
