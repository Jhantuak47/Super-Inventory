package com.superInvent.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.superInvent.POJO.BrandMaster;

public class BrandDAO extends JDBCConnection {
	String query = "";
	BrandMaster brand = new BrandMaster();
	
	public String insert(BrandMaster brand) {
		query = "insert into brand (b_name, created_at, updated_at) values (?,?,?)";
		if(isBrandExist(brand.getName().toLowerCase()))
			return "Brand already exist !";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, brand.getName().toLowerCase());
			preparedStatement.setTimestamp(2, brand.getCreated_at());
			preparedStatement.setTimestamp(3, brand.getUpdated_at());
			// execute insert SQL stetement
			preparedStatement .executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return e.getMessage();
		}
		return "success";
	}
	
	//check if brand already there..
	private boolean isBrandExist(String b_name) {
		String sql = "select id from brand where b_name = '"+ b_name + "'";
		try {
			ResultSet rs = this.createStatement(sql);
			if(rs.isBeforeFirst()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//get brnad name and id..
	public List<BrandMaster> list(){
		query = "select * from brand";
		List<BrandMaster> brands = new ArrayList<BrandMaster>();
		try {
			ResultSet rs = this.createStatement(query);
			while(rs.next()) {
				BrandMaster brand = new BrandMaster();
				brand.setId(rs.getInt("id"));
				brand.setName(rs.getString("b_name"));
				brand.setCreated_at(rs.getTimestamp("created_at"));
				brand.setStatus(rs.getInt("status"));
				brand.setUpdated_at(rs.getTimestamp("updated_at"));
				brands.add(brand);
			}
			return brands;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("from list brandDAO");
		}
		return null;
	}
}
