package com.superInvent.POJO;

import java.util.Arrays;

public class InvoiceDetails{
	
	private int id;
	private int invoice_no;
	private int[] product_id;
	private double[] product_price;
	private int[] qty;
	private int[] tqty;
	private String[] pro_name;
	
	
	public String[] getPro_name() {
		return pro_name;
	}
	public void setPro_name(String[] pro_name) {
		this.pro_name = pro_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(int invoice_no) {
		this.invoice_no = invoice_no;
	}
	public int[] getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int[] product_id) {
		this.product_id = product_id;
	}
	public double[] getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double[] product_price) {
		this.product_price = product_price;
	}
	public int[] getQty() {
		return qty;
	}
	public void setQty(int[] qty) {
		this.qty = qty;
	}
	
	public int[] getTqty() {
		return tqty;
	}
	public void setTqty(int[] tqty) {
		this.tqty = tqty;
	}
	@Override
	public String toString() {
		return "InvoiceDetails [id=" + id + ", invoice_no=" + invoice_no + ", product_id=" + Arrays.toString(product_id)
				+ ", product_price=" + Arrays.toString(product_price) + ", qty=" + Arrays.toString(qty) + ", tqty="
				+ Arrays.toString(tqty) + ", pro_name=" + Arrays.toString(pro_name) + "]";
	}
	
	
	
}