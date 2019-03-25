package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item_cat")
public class ItemCat extends BasePojo{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;			//定义分类目录Id
	private Long parentId;		//上级目录Id
	private String name;		//商品分类名称
	private Integer status;		//1正常，2删除'
	private Integer sortOrder;	//商品排序号
	private Boolean isParent;	//是否为父级
	
	//EasyTree树形结构
	public String getState(){
		
		return isParent ? "closed":"open";
	}
	
	public String getText(){
		return name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	

}
