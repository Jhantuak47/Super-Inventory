package com.superInvent.Services.category_master;

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
}
