package com.superInvent.DAO;

import java.sql.ResultSet;

public class OrderDAO extends JDBCConnection {

		//get products as item for invoice..
		public String getRowItem(int row_num) {
			String row = "";
			System.out.println("from getwor item");
			try {
				String sql = "SELECT id, p_name from products";
				ResultSet rs = this.createStatement(sql);
				if(rs.isBeforeFirst()) {
					row +="<tr>"
							+ "<td><b id=\"number\">"+ ++row_num +"</b></td>" + 
							"<td><select name=\"pid[]\" class = \"form-control form-control-sm\"> ";
					
						
							while(rs.next()) {
								row += "<option value = "+ rs.getInt("id") +">"+ rs.getString("p_name") +"</option> ";
							}
						
					
					row	 += 
							"</select></td> " + 
							"<td><input name=\"tqty[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm\" readonly=\"readonly\"></td> " + 
							"<td><input name=\"qty[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm\" required=\"required\"></td> " + 
							"<td><input name=\"price[]\" type=\"text\" " + 
							"class=\"form-control form-control-sm\" readonly=\"readonly\"></td> " + 
							"<td>Rs.1453</td> " + 
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
