package com.superInvent.Services.brand_master;
import java.util.List;

public class BrandMaster {
	String result = "";
	public String getAllBrands(List<com.superInvent.POJO.BrandMaster> brands){
		for(com.superInvent.POJO.BrandMaster brand : brands) {
			result += "<option value ='"+ brand.getId() +"'>"+ brand.getName() +"</option>";
		}
		return result;
	}
}
