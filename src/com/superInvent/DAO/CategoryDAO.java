package com.superInvent.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.superInvent.POJO.CategoryMaster;

public class CategoryDAO extends JDBCConnection{
	String query;
	CategoryMaster cm;
	
	public List<CategoryMaster> list() {
		query = "select * from category_master";
		List<CategoryMaster> CategoryLists = new ArrayList<CategoryMaster>();
		try {
			ResultSet rs = this.createStatement(query);
			while(rs.next()) {
				cm = new CategoryMaster();
				cm.setId(rs.getInt("id"));
				cm.setC_name(rs.getString("c_name"));
				cm.setParentCategory(rs.getInt("parrent_category"));
				cm.setC_status(rs.getBoolean("c_status"));
				CategoryLists.add(cm);
			}
			return CategoryLists;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<Object[]> getAllCategories(){
		query = "select id, c_name from category_master";
		List<Object[]> allCategories = new ArrayList<Object[]>();
		try {
			ResultSet rs = this.createStatement(query);
			while(rs.next()) {	
				allCategories.add(new Object[]{rs.getString("c_name"), rs.getInt("id")});
			}
//			for(Object[] objects: allCategories) {
//				for(Object obj : objects)
//					System.out.print(obj+", ");
//				System.out.println();
//			}
			return allCategories;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	//Add category..
	public String addCategory(String cat_name, int parent_cat, int status) {
		if(isCategoryPresent(cat_name.toLowerCase().trim()))
			return "category allready exist";
		query = "insert into category_master (c_name, parrent_category, c_status) values (?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, cat_name);
			preparedStatement.setInt(2, parent_cat);
			preparedStatement.setInt(3, status);
			// execute insert SQL stetement
			preparedStatement .executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return e.getMessage();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				return e.getMessage();
			}
			
		}
	  return "success";
	}
	
	//check if category allready exist or not..
	public boolean isCategoryPresent(String cat_name) {
		query = "select id from category_master where c_name = '"+cat_name+"'"; 
		System.out.println(query);
		try {
			ResultSet rs = this.createStatement(query);
			if(rs.isBeforeFirst()){
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return true;
		}
		return false;
	}
}
