package com.superInvent.POJO;

import java.sql.Timestamp;

public class ProductMaster {
	private int id;
	private String p_name;
	private int brand_id;
	private int category_master_id;
	private double price;
	private int qty;
	private Timestamp added_date;
	private int status;
	private String p_type;
	private double weight;
	private String expiry_date;
	private String batch_no;
	private String desc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public int getCategory_master_id() {
		return category_master_id;
	}
	public void setCategory_master_id(int category_master_id) {
		this.category_master_id = category_master_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Timestamp getAdded_date() {
		return added_date;
	}
	public void setAdded_date(Timestamp added_date) {
		this.added_date = added_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "ProductMaster [id=" + id + ", p_name=" + p_name + ", brand_id=" + brand_id + ", category_master_id="
				+ category_master_id + ", price=" + price + ", qty=" + qty + ", added_date=" + added_date + ", status="
				+ status + ", p_type=" + p_type + ", weight=" + weight + ", expiry_date=" + expiry_date + ", batch_no="
				+ batch_no + ", desc=" + desc + "]";
	}
}
