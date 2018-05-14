package com.superInvent.POJO;

import java.sql.Timestamp;
import java.util.Arrays;

public class PurchaseMaster {

		private String vendor_name;
		private Timestamp purchase_date;
		private String bill_no;
		private double purchase_price;
		
		private String[] pro_name;
		private int[] cat_id;
		private int[] brand_id;
		private int[] qty;
		private double[] price;
		private double[] cost_price;
		private String[] type;
		private String[] batch_no;
		private String[] exp_date;
		public String getVendor_name() {
			return vendor_name;
		}
		public void setVendor_name(String vendor_name) {
			this.vendor_name = vendor_name;
		}
		public Timestamp getPurchase_date() {
			return purchase_date;
		}
		public void setPurchase_date(Timestamp purchase_date) {
			this.purchase_date = purchase_date;
		}
		public String getBill_no() {
			return bill_no;
		}
		public void setBill_no(String string) {
			this.bill_no = string;
		}
		public double getPurchase_price() {
			return purchase_price;
		}
		public void setPurchase_price(double d) {
			this.purchase_price = d;
		}
		public String[] getPro_name() {
			return pro_name;
		}
		public void setPro_name(String[] pro_name) {
			this.pro_name = pro_name;
		}
		public int[] getCat_id() {
			return cat_id;
		}
		public void setCat_id(int[] cat_id) {
			this.cat_id = cat_id;
		}
		public int[] getBrand_id() {
			return brand_id;
		}
		public void setBrand_id(int[] brand_id) {
			this.brand_id = brand_id;
		}
		public int[] getQty() {
			return qty;
		}
		public void setQty(int[] qty) {
			this.qty = qty;
		}
		public double[] getPrice() {
			return price;
		}
		public void setPrice(double[] price) {
			this.price = price;
		}
		public double[] getCost_price() {
			return cost_price;
		}
		public void setCost_price(double[] cost_price) {
			this.cost_price = cost_price;
		}
		public String[] getType() {
			return type;
		}
		public void setType(String[] type) {
			this.type = type;
		}
		public String[] getBatch_no() {
			return batch_no;
		}
		public void setBatch_no(String[]  batch_no) {
			this.batch_no =  batch_no;
		}
		public String[] getExp_date() {
			return exp_date;
		}
		public void setExp_date(String[] exp_date) {
			this.exp_date = exp_date;
		}
		@Override
		public String toString() {
			return "PurchaseMaster [vendor_name=" + vendor_name + ", purchase_date=" + purchase_date + ", bill_no="
					+ bill_no + ", purchase_price=" + purchase_price + ", pro_name=" + Arrays.toString(pro_name)
					+ ", cat_id=" + Arrays.toString(cat_id) + ", brand_id=" + Arrays.toString(brand_id) + ", qty="
					+ Arrays.toString(qty) + ", price=" + Arrays.toString(price) + ", cost_price="
					+ Arrays.toString(cost_price) + ", type=" + Arrays.toString(type) + ", batch_no="
					+ Arrays.toString(batch_no) + ", exp_date=" + Arrays.toString(exp_date) + "]";
		}
		
}
