package com.superInvent.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.superInvent.POJO.ProductMaster;
import com.superInvent.POJO.Users;

public class ProductDAO extends JDBCConnection{
	
	public String insert(ProductMaster product) {
		try {
				if(isAlreadyExist(product.getP_name())) {
					return product.getP_name() + ", already exist !";
				}
			String query = "INSERT INTO `products`(`p_name`, `brand_id`, `category_master_id`, `price`, `avl_stock`,"
							+"`added_date`, `status`, `type`, `wt`, `exp_date`, `batch_no`, `Description`, `is_deleted`)"
							 + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, product.getP_name());
				preparedStatement.setInt(2, product.getBrand_id());
				preparedStatement.setInt(3, product.getCategory_master_id());
				preparedStatement.setDouble(4, product.getPrice());
				preparedStatement.setInt(5, product.getStock());
				preparedStatement.setTimestamp(6, product.getAdded_date());
				preparedStatement.setInt(7, product.getStatus());
				preparedStatement.setString(8, product.getP_type());
				preparedStatement.setDouble(9, product.getWeight());
				preparedStatement.setString(10, product.getExpiry_date());
				preparedStatement.setString(11, product.getBatch_no());
				preparedStatement.setString(12, product.getDesc());
				preparedStatement.setInt(13, product.getIs_deleted());
				// execute insert SQL stetement
				preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("from productDAO insert");
			System.out.println(e);
			return "fail";
		}
		
		return "success";
	}
	
	//update brand status
	public String updateStatus(int id, int status){
		
		try {
			String query = "UPDATE `products` "
					+ "SET status = ?"
					+ " WHERE id = ?";
			
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setInt(1, status);
		      preparedStmt.setInt(2, id);
		      preparedStmt.execute();
		      
		      con.close();
			
		} catch (Exception e) {
			System.out.println("from update() of brand dao");
			System.out.println(e);
			return "fail";
		}
		return "UPDATED";
		
	}
	//edit product...
	public String update(ProductMaster product) {
		try {
			
		String query = "UPDATE `products` SET `p_name`=?,`brand_id`=?,`category_master_id`=?,"
				+ "`price`=?,`avl_stock`=?,`type`=?,`wt`=?,"
				+ "`exp_date`=?,`batch_no`=?,`Description`=? WHERE `id`=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, product.getP_name());
			preparedStatement.setInt(2, product.getBrand_id());
			preparedStatement.setInt(3, product.getCategory_master_id());
			preparedStatement.setDouble(4, product.getPrice());
			preparedStatement.setInt(5, product.getStock());
			preparedStatement.setString(6, product.getP_type());
			preparedStatement.setDouble(7, product.getWeight());
			preparedStatement.setString(8, product.getExpiry_date());
			preparedStatement.setString(9, product.getBatch_no());
			preparedStatement.setString(10, product.getDesc());
			preparedStatement.setInt(11, product.getId());
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
	
	} catch (Exception e) {
		System.out.println("from productDAO update()");
		System.out.println(e);
		return "fail";
	}
		return "success";
	}
	//getting product detail on passing the id..
	public ProductMaster getProdcutById(int id) {
		String sql = "select * from products where id = "+id;
		ProductMaster product = new ProductMaster();	
		try {
			ResultSet rs = this.createStatement(sql);
			if(rs.isBeforeFirst()) {
				rs.next();
				product.setP_name(rs.getString("p_name"));
				product.setCategory_master_id(rs.getInt("category_master_id"));
				product.setBrand_id(rs.getInt("brand_id"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("avl_stock"));
				product.setAdded_date(rs.getTimestamp("added_date"));
				product.setStatus(rs.getInt("status"));
				product.setP_type(rs.getString("type"));
				product.setWeight(rs.getDouble("wt"));
				product.setDesc(rs.getString("Description"));
				product.setExpiry_date(rs.getString("exp_date"));
				product.setBatch_no(rs.getString("batch_no"));
				return product;
			}
		} catch (Exception e) {
			System.out.println("error from productDAO getProductById");
			System.out.println(e);
		}
		return null;
	}
	
	public String delete(int id) {
		try {
			String query = "UPDATE `products` SET is_deleted = 1 WHERE id = ?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setInt(1, id);
		      
		     boolean response =  preparedStmt.execute();
		      
		      con.close();				
		} catch (Exception e) {
			System.out.println("error product dao, delet()");
			System.out.println(e);
			return "fail";
		}
		
		return "success";
	}
	
	//check if product is already exist..
	
	private boolean isAlreadyExist(String p_name) {
		String query = "select id from products where p_name = '"+ p_name + "'";
		try {
			ResultSet rs = this.createStatement(query);
				if(rs.isBeforeFirst()) {
					return true;
				}
		} catch (Exception e) {
			System.out.println("from product dao isalready");
			System.out.println(e);
			return true;
		}
		return false;
	}
}
