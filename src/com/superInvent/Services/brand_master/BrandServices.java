package com.superInvent.Services.brand_master;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrandServices {
	public String buieldBrandsTable(ResultSet rs, int page_no, int numberOfResultPerPage) {
		String tbody = "";
		 int i = ( (page_no - 1) * numberOfResultPerPage ) + 1;
		try {
			Date dt = new Date();  
            SimpleDateFormat formatter = new SimpleDateFormat("dd,  MMMM yyyy");
			while(rs.next()) {
			  tbody += "<tr id = "+ rs.getInt("id")+">"
			 				+ "<td><input type=\"checkbox\"  onclick = \"checkbox_on_click()\" name=\"record\"></td>"
			 				+ "<td>"+ i +"</td>"
			 				+ "<td>"+ rs.getString("b_name") +"</td>"
			 				+ "<td>"+formatter.format(rs.getTimestamp("created_at")) +"</td><td>";
			 				if(rs.getInt("status") == 1)
			 tbody  +="<a href = \"#\" onclick=\"Update_category_status("+ rs.getInt("id") +","+ page_no +", "+ rs.getInt("status") +" );\" class=\"btn btn-danger btn-sm pull-right\" style=\"margin-right:10px;\">Deactivate</a>";			
			 				else
	         tbody  +="<a href = \"#\" onclick=\"Update_category_status("+ rs.getInt("id") +","+ page_no +", "+ rs.getInt("status") +" );\" class=\"btn btn-success btn-sm pull-right\" style=\"margin-right:10px;\">Active</a>";			

			 tbody +="</td><td> <a onclick=\"edit('"+ rs.getInt("id") +"', '"+ rs.getString("b_name") +"',"
			 				+ "'"+ rs.getInt("status") +"',"+ page_no +", '"+formatter.format(rs.getTimestamp("created_at")) +"');\""
			 				+ " class=\"btn btn-primary btn-sm active pull-right\" style=\"margin-left:100px\">"
			 				+ "<i class=\"fa fa-edit\" aria-hidden=\"true\"></i>&nbsp;Edit</a></td>";
			 tbody +=  "<td><a class = \"btn btn-danger active btn-sm\" "
			 				+ "onclick = \"delete_modal('"+ rs.getInt("id") +"', '"+ rs.getString("b_name") +"', "+ page_no +");\">Delete</a></td>";
			 tbody +="</tr>";
			 	i++;
			}
			return tbody;
		} catch (SQLException e) {
			System.out.println("error from BrandService buildbrand");
			System.out.println(e);
		}
		return null;
	}
}
