package com.superInvent.DAO;

import java.sql.ResultSet;

public class OrderDAO extends JDBCConnection {

		//get products as item for invoice..
		public String getRowItem() {
			String row = "";
			try {
				String sql = "SELECT id, p_name from products";
				ResultSet rs = this.createStatement(sql);
				if(rs.isBeforeFirst()) {
					row +="<tr>"
							+ "<td><b class=\"number\">1</b></td>" + 
							"<td><select name=\"pid[]\" class = \"form-control form-control-sm pid\"> ";
					row += "<option value = ''>choose product </option>";		
						
							while(rs.next()) {
								row += "<option value = "+ rs.getInt("id") +">"+ rs.getString("p_name") +"</option> ";
							}
						
					
					row	 += 
							"</select></td> " + 
							"<td><input name=\"tqty[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm tqty\" readonly=\"readonly\"></td> " + 
							"<td><input name=\"qty[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm qty\" required=\"required\"></td> " + 
							"<td><input name=\"price[]\" type=\"text\" " +
							"class=\"form-control form-control-sm price\" readonly=\"readonly\"></td> " + 
							"<span><input name=\"pro_name[]\" type=\"hidden\" " +
							"class=\"form-control form-control-sm pro_name\"></span> " + 
							"<td>Rs.<span class = 'amt'>0</td> " + 
							"</tr>";
				}
				
				return row;	
			} catch (Exception e) {
				System.out.println("error from orderdao getRowItem");
				System.out.println(e);
			}
			
			return "<h7>no items found ! please first enter some products into inventory</h7>";
		}
}
