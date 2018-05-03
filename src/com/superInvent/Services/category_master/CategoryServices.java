package com.superInvent.Services.category_master;

import java.sql.ResultSet;
import java.util.List;

//return all categroy name with id..
public class CategoryServices {
	String result = "";
	public String getAllCategory(List<Object[]> categories) {
		for(Object[] categoryObj : categories) {
			result += "<option value = '"+ categoryObj[1] +"'>"+ categoryObj[0] +"</option>";
		}
		return result;
	}
	public String buildCatgoryTable(ResultSet rs, int page_no, int numberOfResultPerPage) {
		String table = "";
			 int i = ( (page_no - 1) * numberOfResultPerPage ) + 1;
		try {
			while(rs.next()) {
				table += "<tr id = "+ rs.getInt("id") +">"
							+ "<td><input type=\"checkbox\"  onclick = \"checkbox_on_click()\" name=\"record\"></td>"
							+ "<td>"+ i+"</td>"
							+ "<td>"+ rs.getString("category") +"</td>"
							+ "<td>";
							if(rs.getString("parent") == null) table+= "root"; else table += rs.getString("parent");
							
				table +=    "</td>";
							if(rs.getInt("status") == 1){
								table += "<td><a href = \"#\" onclick=\"Update_category_status("+ rs.getInt("id")  +", "+ page_no +", "+ rs.getInt("status") +");\" class=\"btn btn-danger btn-sm pull-right\" style=\"margin-right:10px;\">Deactivate</a></td>";
							}else {
								table += "<td><a href = \"#\" onclick=\"Update_category_status("+ rs.getInt("id")  +", "+ page_no +", "+ rs.getInt("status") +");\"  class=\"btn btn-success btn-sm pull-right\" style=\"margin-right:10px;\">Activate</a></td>";
							}
				table += "<td><a onclick=\"edit('"+ rs.getInt("id") +"', '"+ rs.getString("category") +"', '"+ rs.getInt("parent_id") +"','"+ page_no +"', '"+ rs.getInt("status") +"');\"";
				table += "class=\"btn btn-primary btn-sm active pull-right\" style=\"margin-left:100px\"><i class=\"fa fa-edit\" aria-hidden=\"true\"></i>&nbsp;Edit</a></td>";
				
				table += "<td><a class = \"btn btn-danger active btn-sm\" onclick = \"delete_modal('"+ rs.getInt("id") +"', '"+ rs.getString("category") +"', "+ page_no +");\">"
						+ "Delete</a></td>";
				table += "</tr>";
				i++;
			}
		} catch (Exception e) {
			System.out.println("error from category service build table");
			System.out.println(e);
		}
			
		return table;
	}
}
