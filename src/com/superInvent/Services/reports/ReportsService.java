package com.superInvent.Services.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Error;

import com.superInvent.POJO.ReportsPojo;

public class ReportsService {
	public List<ReportsPojo> reports(ResultSet rs){
			int i=0;
			List<String> item = null;
			List<Double> price = null;
			List<Integer> qty = null;
			ReportsPojo rp = null;
			List <Integer> a= new ArrayList<Integer>();
			
			try {
				
			if (rs.next()) {
				while(!rs.isAfterLast()  && !a.contains( rs.getInt("invoice_no"))) {
					a.add(rs.getInt("invoice_no"));
				    rp = new ReportsPojo();
					rp.setInvoice_no(rs.getInt(1));
					rp.setName(rs.getString(2));
					item = new ArrayList<String>();
				    price = new ArrayList<Double>();
				    qty = new ArrayList<>();
					 do{
						item.add(rs.getString(8));
						price.add(rs.getDouble(9));
						qty.add(rs.getInt(10));
					}while(rs.next() && a.contains(rs.getInt("invoice_no")));
					rp.setItem(item);
					rp.setPrice(price);
					rp.setQty(qty);
				}
			}
				System.out.println(rp.toString());
			} catch (Exception e) {
				System.out.println("error ...");
				System.out.println(e);
			}
		return null;
		
	}
}
