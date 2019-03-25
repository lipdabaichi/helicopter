package com.jt.manage.service;

import java.util.List;

import com.jt.manage.pojo.ItemCat;

public interface ItemCatService {
	//根据父级ID查询商品分类信息
	List<ItemCat> findItemCatList(Long parentId);

}
