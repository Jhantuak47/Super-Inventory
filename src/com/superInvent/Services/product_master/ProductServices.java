package com.superInvent.Services.product_master;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductServices {

	public String buieldProductsTable(ResultSet rs, int page_no, int numberOfResultPerPage) {
		String tbody = "";
		 int i = ( (page_no - 1) * numberOfResultPerPage ) + 1;
		try {  
			while(rs.next()) {
			  tbody += "<tr id = "+ rs.getInt("id")+">"
			 				+ "<td><input type=\"checkbox\"  onclick = \"checkbox_on_click()\" name=\"record\"></td>"
			 				+ "<td>"+ i +"</td>"
			 				+ "<td>"+ rs.getString("name") +"</td>"
			 				+ "<td>"+ rs.getString("brand") +"</td>"
			 				+ "<td>"+ rs.getString("category") +"</td><td>";
			 				if(rs.getInt("state") == 1)
			 tbody  +="<a href = \"#\" onclick=\"Update_category_status("+ rs.getInt("id") +","+ page_no +", "+ rs.getInt("state") +" );\" class=\"btn btn-danger btn-sm\" style=\"margin-right:10px;\">Deactivate</a>";			
			 				else
	         tbody  +="<a href = \"#\" onclick=\"Update_category_status("+ rs.getInt("id") +","+ page_no +", "+ rs.getInt("state") +" );\" class=\"btn btn-success btn-sm\" style=\"margin-right:10px;\">Active</a>";			

			 tbody +="</td><td>"
			 		+ " <a onclick=\"edit('"+ rs.getInt("id") +"');\""
			 				+ " class=\"btn btn-primary btn-sm active\">"
			 				+ "<i class=\"fa fa-edit\" aria-hidden=\"true\"></i>&nbsp;Edit</a>&nbsp;";
			 tbody +=  "<a class = \"btn btn-danger active btn-sm\" "
			 				+ "onclick = \"delete_modal('"+ rs.getInt("id") +"', '"+ rs.getString("name") +"', "+ page_no +");\">Delete</a></td>";
			 tbody += "<td><a class = \"btn btn-info active btn-sm\" onclick = \"delete_modal('<%= product.getId() %>', "
			 		+ "'<%= product.getP_name() %>', <%= page_no %>);\"><i class=\"fa fa-eye\"></i>&nbsp;View</a></td>";
			 tbody +="</tr>";
			 	i++;
			}
			return tbody;
		} catch (SQLException e) {
			System.out.println("error from ProductService buildtable");
			System.out.println(e);
		}
		return null;
	}
}
