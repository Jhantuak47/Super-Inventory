package com.superInvent.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.mysql.jdbc.Statement;
import com.superInvent.POJO.ProductMaster;
import com.superInvent.POJO.PurchaseMaster;
import com.superInvent.POJO.Users;

public class ProductDAO extends JDBCConnection{
	
	public String insert(ProductMaster product, String bill_no, String vendor_name) {
		String query = "INSERT INTO `products`(`p_name`, `brand_id`, `category_master_id`, `price`, `avl_stock`,"
				+"`added_date`, `status`, `type`, `wt`, `exp_date`, `batch_no`, `Description`, `is_deleted`, `purchase_id`, `cost_price`)"
				 + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
				if(isAlreadyExist(product.getP_name())) {
					return product.getP_name() + ", already exist !";
				}
			
				int purchaseId = insertBill(product.getAdded_date(), bill_no, vendor_name, 0.0);
				if(purchaseId > 0) {
					
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
					preparedStatement.setInt(14, purchaseId);
					preparedStatement.setDouble(15, product.getCost_price());
					// execute insert SQL statement..
					if(preparedStatement.executeUpdate() > 0)
						return "success";
				}
				
		
		} catch (Exception e) {
			System.out.println("from productDAO insert");
			System.out.println(e);
			return "fail";
		}
		
		return "fail";
	}
	
	public int insertBill(Timestamp purchased_at, String bill_no, String vendor_name, double tot_price) {
		
		String query = "INSERT INTO `purchase_details`(`purchased_at`, `purchesed_from`, `purchesed_bill_no`, `total_purchesed_amount`) "
						+ "VALUES (?,?,?,?)";
		
		try {
			
			int purchase_id  = new PurchaseDAO().isBillExist(bill_no);
			
				if(purchase_id > 0) {
					return purchase_id;
				}
			
			PreparedStatement preparedStatement = con.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setTimestamp(1, purchased_at);
			preparedStatement.setString(2, vendor_name);
			preparedStatement.setString(3, bill_no);
			preparedStatement.setDouble(4, tot_price);
			
			int res = preparedStatement.executeUpdate();
				if(res > 0) {
					ResultSet rs = preparedStatement.getGeneratedKeys();
						if(rs.next()) {
						    purchase_id = rs.getInt(1);
							return  purchase_id;
						}
						return 0;
				}
				return 0;

		} catch (Exception e) {
			System.out.println("error from ProductDao");
			System.out.println("insertBill");
			System.out.println(e);
		}
		return 0;
	}
	
	//update product status
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
	
	public String delete(String[] ids) {
		String idString = "";
		int i;
		try {
			for(i=0; i<ids.length-1; i++) {
				idString += ids[i] + ", ";
			}
			idString += ids[i];
			String query = "UPDATE `products` SET is_deleted = 1 "
							+ "where id in (" + idString + ");";
			System.out.println("query  = " +query);
			
			int x = this.executeUpdate(query);
			System.out.println("x = "+ x);
			if(x > 0) {
				return "success";
			}
		      
		      con.close();				
		} catch (Exception e) {
			System.out.println("error product dao, delet()");
			System.out.println(e);
			return "fail";
		}
		
		return "fail";
	}
	
	//check if product is already exist..
	
	public boolean isAlreadyExist(String p_name) {
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
