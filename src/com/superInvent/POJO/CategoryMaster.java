package com.superInvent.POJO;

public class CategoryMaster {
	private int id;
	private String c_name;
	private int parentCategory;
	private boolean c_status;
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
	public int getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(int parentCategory) {
		this.parentCategory = parentCategory;
	}
	public boolean isC_status() {
		return c_status;
	}
	public void setC_status(boolean c_status) {
		this.c_status = c_status;
	}
	@Override
	public String toString() {
		return "CategoryMaster [id=" + id + ", c_name=" + c_name + ", parentCategory=" + parentCategory + ", c_status="
				+ c_status + "]";
	}
}
