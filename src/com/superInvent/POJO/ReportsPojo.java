package com.superInvent.POJO;
import java.util.List;

public class ReportsPojo {
	private int invoice_no;
	private String name;
	private List<String> item;
	private List<Double> price;
	private List<Integer> qty;
	public int getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(int invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getItem() {
		return item;
	}
	public void setItem(List<String> item) {
		this.item = item;
	}
	public List<Double> getPrice() {
		return price;
	}
	public void setPrice(List<Double> price) {
		this.price = price;
	}
	public List<Integer> getQty() {
		return qty;
	}
	public void setQty(List<Integer> qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "ReportsPojo [invoice_no=" + invoice_no + ", name=" + name + ", item=" + item + ", price=" + price
				+ ", qty=" + qty + "]";
	}	
}
