package com.superInvent.POJO;

public class CategoryMaster {
	private int id;
	private String c_name;
	private String parentCategory;
	private int parent_id;
	private int c_status;
	
	
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String string) {
		this.parentCategory = string;
	}
	public int getC_status() {
		return c_status;
	}
	public void setC_status(int c_status) {
		this.c_status = c_status;
	}
	@Override
	public String toString() {
		return "CategoryMaster [id=" + id + ", c_name=" + c_name + ", parentCategory=" + parentCategory + ", c_status="
				+ c_status + "]";
	}
}
