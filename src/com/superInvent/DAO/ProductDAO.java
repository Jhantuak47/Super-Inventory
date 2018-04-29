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
			String query = "INSERT INTO `products`(`p_name`, `brand_id`, `category_master_id`, `price`, `quantity`,"
							+"`added_date`, `status`, `type`, `weight(kg)`, `exp_date`, `batch_no`, `Description`)"
							 + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, product.getP_name());
				preparedStatement.setInt(2, product.getBrand_id());
				preparedStatement.setInt(3, product.getCategory_master_id());
				preparedStatement.setDouble(4, product.getPrice());
				preparedStatement.setInt(5, product.getQty());
				preparedStatement.setTimestamp(6, product.getAdded_date());
				preparedStatement.setInt(7, product.getStatus());
				preparedStatement.setString(8, product.getP_type());
				preparedStatement.setDouble(9, product.getWeight());
				preparedStatement.setString(10, product.getExpiry_date());
				preparedStatement.setString(11, product.getBatch_no());
				preparedStatement.setString(12, product.getDesc());
				// execute insert SQL stetement
				preparedStatement.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("from productDAO insert");
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
