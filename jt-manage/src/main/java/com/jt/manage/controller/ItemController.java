package com.jt.manage.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jt.manage.pojo.ItemDesc;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	private static final Logger logger = Logger.getLogger(ItemController.class);
	
	//查询全部商品信息
	@RequestMapping("/findAll")		//定义请求路径
	@ResponseBody					//将返回值数据转化为JSON串
	public List<Item> findAll(){
		
		return itemService.findAll();
	}



	
	/**
	 * 1.页面url地址:http://localhost:8091/item/query?page=1&rows=20
	 * 2.进行分页处理
	 * 3.返回EasyUI能够识别的JSON数据 格式如下 {"total":2000,"rows":[{},{},{}]}  total表示记录总数  rows表述每页记录数
	 */
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult findItem(int page,int rows){
		
		return itemService.findItemAll(page,rows);
	}
	
	//@RequestMapping(value="/queryItemCatName")
	public void queryItemCatName(Long itemCatId,HttpServletResponse response){
		
		String result = itemService.queryItemCatName(itemCatId);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@RequestMapping(value="/queryItemCatName",produces="text/html;charset=utf-8")
	@ResponseBody()
	public String queryItemCatName(Long itemCatId){

		return itemService.queryItemCatName(itemCatId);
	}

	//根据商品id查询商品详情
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId){
		try {
			ItemDesc itemDesc =
					itemService.findItemDescById(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"查询失败");
	}



	
	@RequestMapping("/testFindCount")
	@ResponseBody
	public int TestFindCount(){
		
		return itemService.TestFindCount();
	}
	
	
	//商品新增  http://localhost:8091/item/save
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			logger.info("商品插入成功"+item.getId());
			return SysResult.build(200, "商品新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品新增失败");
		}
	}
	
	//http://localhost:8091/item/update
	@RequestMapping("/update")
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			logger.info("商品修改成功"+item.getId());
			return SysResult.build(200, "商品修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品修改成功");
		}
		
	}
	
	//商品的删除操作  http://localhost:8091/item/delete
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItems(Long[] ids){
		try {
			itemService.deleteItems(ids);
			logger.info("商品删除成功"+Arrays.toString(ids));
			return SysResult.build(200, "商品删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品删除成功");
		}
	}
	
	//商品的下架	http://localhost:8091/item/instock
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instockItems(Long[] ids){
		try {
			int status = 2;	//表示商品下架
			itemService.updateStatus(ids,status);
			logger.info("商品下架成功"+Arrays.toString(ids));
			return SysResult.build(200, "商品下架成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品下架成功");
		}
	}
	
	//商品的下架	http://localhost:8091/item/instock
		@RequestMapping("/reshelf")
		@ResponseBody
		public SysResult reshelfItems(Long[] ids){
			try {
				int status = 1;	//表示商品下架
				itemService.updateStatus(ids,status);
				logger.info("商品上架成功"+Arrays.toString(ids));
				return SysResult.build(200, "商品上架成功");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return SysResult.build(201, "商品上架成功");
			}
		}
	
	
	
	
	
	
	
	
	
}
