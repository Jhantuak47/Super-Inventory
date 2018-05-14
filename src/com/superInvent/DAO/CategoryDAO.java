package com.superInvent.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.superInvent.POJO.CategoryMaster;

public class CategoryDAO extends JDBCConnection{
	String query;
	CategoryMaster cm;
	
	public Object[] list(int currentPage, int numberOfResultPerPage, String link) {
		List<CategoryMaster> CategoryLists = new ArrayList<CategoryMaster>();
		try {
			Object[] objects= new Pagination().listWithPagination("category_master", currentPage, numberOfResultPerPage, link);
			//converting into category object...
			ResultSet rs = (ResultSet) objects[0];
			while(rs.next()){
				cm = new CategoryMaster();
				cm.setId(rs.getInt("id"));
				cm.setC_name(rs.getString("category"));
				cm.setParentCategory(rs.getString("parent"));
				cm.setC_status(rs.getInt("status"));
				cm.setParent_id(rs.getInt("parent_id"));
				CategoryLists.add(cm);
			}
			
			// Object [ categoryLists, pagination, totalNoOfPages ]..
			return new Object[] {CategoryLists, objects[1], objects[2]} ;
		} catch (Exception e) {
			System.out.println("error form categoryDAO list");
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
		query = "insert into category_master (c_name, parrent_category, c_status, is_deleted, created_at) values (?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, cat_name.toLowerCase().trim());
			preparedStatement.setInt(2, parent_cat);
			preparedStatement.setInt(3, status);
			preparedStatement.setInt(4, 0);
			preparedStatement.setTimestamp(5,new Timestamp(new Date().getTime()));
			// execute insert SQL stetement
			preparedStatement .executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			return "fail";
		}
	  return "success";
	}
	
	//udate category
	public String update(int id, String cat_name, int parent_id, int status) {
		String query = "UPDATE `category_master` "
						+ "SET c_name = ?, parrent_category = ?, c_status = ?"
						+ " WHERE id = ?";
		
			try {
				
				 PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setString(1, cat_name);
			      preparedStmt.setInt(2, parent_id);
			      preparedStmt.setInt(3, status);
			      preparedStmt.setInt(4, id);
			      preparedStmt.execute();
			      
			      con.close();
				
			} catch (Exception e) {
				System.out.println("from update() of category dao");
				System.out.println(e);
				return "fail";
			}
			return "UPDATED";
	}
	
	public String updateStatus(int id, int status) {
		try {
			String query = "UPDATE `category_master` "
					+ "SET c_status = ?"
					+ " WHERE id = ?";
			
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setInt(1, status);
		      preparedStmt.setInt(2, id);
		      preparedStmt.execute();
		      
		      con.close();
			
		} catch (Exception e) {
			System.out.println("from update() of category dao");
			System.out.println(e);
			return "fail";
		}
		return "UPDATED";
	}
	
	//check if category allready exist or not..
	public boolean isCategoryPresent(String cat_name) {
		query = "select id from category_master where c_name = '"+cat_name+"'"; 
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
	
	//Delete Category .. 
	public String delete(int cat_id) {
		try {
			if(isCategoryDependent(cat_id)) {
				return "dependend";
			}
			if(isCategoryhasProduct(cat_id)) {
				return "hasProduct";
			}
			String query = "UPDATE `category_master` SET is_deleted = 1 WHERE id = ?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setInt(1, cat_id);
		      
		     boolean response =  preparedStmt.execute();
		      
		      con.close();				
		} catch (Exception e) {
			System.out.println("error catery dao, delet()");
			System.out.println(e);
			return "fail";
		}
		
		return "success";
	}
	
	//delete multiple records...
	public String deleteMultiple(String[] ids) {
		String idString = "";
		for(String id : ids) {
			if(isCategoryDependent(Integer.parseInt(id))) {
				return "dependend";
			}
			if(isCategoryhasProduct(Integer.parseInt(id))) {
				return "hasProduct";
			}
		}
		int i;
		try {
			for(i=0; i<ids.length-1; i++) {
				idString += ids[i] + ", ";
			}
			idString += ids[i];
			String query = "UPDATE `category_master` SET is_deleted = 1 "
							+ "where id in (" + idString + ");";
			System.out.println(query);
			int x = this.executeUpdate(query);
			if(x > 0) {
				return "success";
			}
		      
		      con.close();				
		} catch (Exception e) {
			System.out.println("error brand dao, delet()");
			System.out.println(e);
			return "fail";
		}
		
		return "fail";
	}
	
	//check if categroy has child category..!!
	
	public boolean isCategoryDependent(int cat_id) {
		String query = "SELECT * FROM `category_master` WHERE `parrent_category` = "+ cat_id ;
		return isDependent(query);
		
	}
	
	private boolean isCategoryhasProduct(int cat_id) {
		String query = "SELECT id from products where category_master_id = "+ cat_id;
		return isDependent(query);
		
	}
	
	private boolean isDependent(String query) {
		try {
			ResultSet rs = this.createStatement(query);
			if(rs.isBeforeFirst()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("error from isDependent");
			System.out.println(e);
			return true;
		} 
	    return false;
	}
}
