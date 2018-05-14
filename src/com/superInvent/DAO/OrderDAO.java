package com.superInvent.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;
import com.superInvent.POJO.Invoice;
import com.superInvent.POJO.InvoiceDetails;

public class OrderDAO extends JDBCConnection {

		//get products as item for invoice..
		public String getRowItem() {
			String row = "";
			try {
				String sql = "SELECT id, p_name from products";
				ResultSet rs = this.createStatement(sql);
				
					row +="<tr>"
							+ "<td><b class=\"number\">1</b></td>" + 
							"<td><select name=\"pid[]\" class = \"form-control form-control-sm pid\"> ";
					row += "<option value = ''>choose product </option>";		
					if(rs.isBeforeFirst()) {	
							while(rs.next()) {
								row += "<option value = "+ rs.getInt("id") +">"+ rs.getString("p_name") +"</option> ";
							}
						
					}
					row	 += 
							"</select></td> " + 
							"<td><input name=\"tqty[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm tqty\" readonly=\"readonly\"></td> " + 
							"<td><input name=\"qty[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm qty\" required=\"required\"></td> " + 
							"<td><input name=\"price[]\" type=\"text\" " +
							"class=\"form-control form-control-sm price\" readonly=\"readonly\"></span> " + 
							"<span><input name=\"pro_name[]\" type=\"hidden\" " +
							"class=\"pro_name\"></td>" + 
							"<td>Rs.<span class = 'amt'>0</td> " + 
							"</tr>";
				
				return row;	
			} catch (Exception e) {
				System.out.println("error from orderdao getRowItem");
				System.out.println(e);
			}
			
			return "<h7>no items found ! please first enter some products into inventory</h7>";
		}
		
		public String insert(Invoice invoice, InvoiceDetails invoicedetails) {
			try {
				String query = "INSERT INTO `invoice`(`cust_name`, `order_date`, `sub_total`, `gst_tax`, `discount`, `net_total`, `paid_amount`, `due`, `payment_method`) "
						+ "VALUES (?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement preparedStatement = con.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, invoice.getCust_name());
				preparedStatement.setTimestamp(2, invoice.getOrder_date());
				preparedStatement.setDouble(3, invoice.getSub_tot());
				preparedStatement.setDouble(4, invoice.getGst());
				preparedStatement.setDouble(5, invoice.getDsicount());
				preparedStatement.setDouble(6, invoice.getNet_tot());
				preparedStatement.setDouble(7, invoice.getPaid_amt());
				preparedStatement.setDouble(8, invoice.getDue());
				preparedStatement.setString(9, invoice.getPayment_method());
			
				 int res = preparedStatement.executeUpdate();
				 if(res > 0) {
					ResultSet  rs = preparedStatement.getGeneratedKeys();
					if(rs.next()) {
						int invoice_no = rs.getInt(1);
						return insertIntoInvoiceDetails(invoice_no, invoicedetails);
					}else
						return "creating invoice failed !, no id obtained";
					
				 }else
					 return "fail to feed data into database !";
				 
				 
				
			} catch (Exception e) {
				System.out.println("error from orderDAO insert()");
				System.out.println(e);
				return "fail";
			}
			
		}
		
		private String insertIntoInvoiceDetails(int invoice_no, InvoiceDetails invoicedetails) {

			String sql = "INSERT INTO `invoice_details`(`invoice_no`, `product_id`, `product_price`, `qty`) "
						+ "VALUES (?,?,?,?)";
			/*	try {
					PreparedStatement ps = con.prepareStatement(sql);
					for(int i=0; i < invoicedetails.getProduct_id().length ; i++) {
						
						//System.out.println(invoicedetails.getTqty()[i]);
						//System.out.println(invoicedetails.getQty()[i]);
						
						int remainQty =  ( invoicedetails.getTqty()[i] - invoicedetails.getQty()[i] );
						
//						System.out.println("reamining qty "+ invoicedetails.getProduct_id()[i] + "= " + remainQty );
						
						
						if(invoicedetails.getQty()[i] > invoicedetails.getTqty()[i]) {
							return "QUANTITY_EXCEED";
						}else {
							//updating stock of products table...
							String updateQuery = "UPDATE products SET avl_stock = "+ remainQty + " WHERE id = " + invoicedetails.getProduct_id()[i];
							
							 this.executeUpdate(updateQuery);
						
						}
						
						ps.setInt(1, invoice_no);
						ps.setInt(2, invoicedetails.getProduct_id()[i]);
						ps.setDouble(3, invoicedetails.getProduct_price()[i]);
						ps.setDouble(4, invoicedetails.getQty()[i]);
						ps.executeUpdate();
					}
				  con.close();
				  ps.close();
				} catch (Exception e) {
					System.out.println("error form OrderDAO insertIntoInvoiceDetails()");
					System.out.println(e);
				}*/
			return "success";
		}
}
