package com.superInvent.POJO;

import java.sql.Timestamp;

public class Invoice {
	private int invoice_no;
	private String cust_name;
	private Timestamp order_date;
	private double sub_tot;
	private double gst;
	private double dsicount;
	private double net_tot;
	private double paid_amt;
	private double due;
	private String payment_method;
	public int getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(int invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public double getSub_tot() {
		return sub_tot;
	}
	public void setSub_tot(double sub_tot) {
		this.sub_tot = sub_tot;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public double getDsicount() {
		return dsicount;
	}
	public void setDsicount(double dsicount) {
		this.dsicount = dsicount;
	}
	public double getNet_tot() {
		return net_tot;
	}
	public void setNet_tot(double net_tot) {
		this.net_tot = net_tot;
	}
	public double getPaid_amt() {
		return paid_amt;
	}
	public void setPaid_amt(double paid_amt) {
		this.paid_amt = paid_amt;
	}
	public double getDue() {
		return due;
	}
	public void setDue(double due) {
		this.due = due;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	@Override
	public String toString() {
		return "InvoiceDetails [invoice_no=" + invoice_no + ", cust_name=" + cust_name + ", order_date=" + order_date
				+ ", sub_tot=" + sub_tot + ", gst=" + gst + ", dsicount=" + dsicount + ", net_tot=" + net_tot
				+ ", paid_amt=" + paid_amt + ", due=" + due + ", payment_method=" + payment_method + "]";
	}
}
