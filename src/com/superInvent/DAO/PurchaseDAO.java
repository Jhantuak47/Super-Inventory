package com.superInvent.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;
import com.superInvent.POJO.ProductMaster;
import com.superInvent.POJO.PurchaseMaster;

public class PurchaseDAO extends JDBCConnection{

		public String returnRow() {
			try {
				String sql = "SELECT `id`, `b_name` FROM `brand`";
				String query = "select id, c_name from category_master";
				
					ResultSet rs = this.createStatement(sql);
				String row = "<tr><td><input name=\"pro_name[]\" type=\"text\" style = 'width:110px;' class=\"form-control form-control-sm pro_name\" ></td>";
				
				row += "<td><select name=\"b_id[]\" id = 'pro_id'class = \"form-control form-control-sm bid\"> "
							+ "<option value = ''>select brand</option>";
								
									if(rs.isBeforeFirst()) {
										while(rs.next()) {
											row += "<option value = "+ rs.getInt("id") +">"+ rs.getString("b_name") +"</option> ";
										}
									}
				row += "</select></td> "

						+ "<td><select name=\"cid[]\" class = \"form-control form-control-sm cid\"> ";
				row += "<option value = ''>choose category </option>";
						rs = this.createStatement(query);
							if(rs.isBeforeFirst()) {	
									while(rs.next()) {
										row += "<option value = "+ rs.getInt("id") +">"+ rs.getString("c_name") +"</option> ";
									}
								
							}
				row += "</select></td> "
							+	"<td><input name=\"qty[]\" type=\"text\" " + 
								"class=\"form-control form-control-sm qty\" style=\"width:80px;\"></td> " + 
								"<td><input name=\"cp[]\" type=\"text\" " + 
								"class=\"form-control form-control-sm cp\"  style = 'width:80px;'></td> " + 
								"<td><input name=\"price[]\" type=\"text\" " +
								"class=\"form-control form-control-sm price\" style = 'width:80px;'></td> " + 
								"<td><input name=\"type[]\" type=\"text\" " +
								"class=\"form-control form-control-sm type\" style = 'width:90px;'></td>" + 
								"<td><input name=\"b_no[]\" type=\"text\" " + 
								"class=\"form-control form-control-sm b_no\" style = 'width:90px;'></td> " + 
								"<td><input name=\"exp_date[]\" type=\"date\" " + 
								"class=\"form-control form-control-sm exp_date\" style=\"width:118px;\"></td> " +
								"</tr>";
				return row;
			} catch (Exception e) {
				System.out.println("error form purchaseDAO returnRow");
				System.out.println(e);
			}
			
			
			return "";
		}
		
		public String insert(PurchaseMaster product) {
			
			String query = "INSERT INTO `purchase_details`(`purchased_at`, `purchesed_from`, `purchesed_bill_no`, `total_purchesed_amount`) "
							+ "VALUES (?,?,?,?)";
			
			try {
				
				int purchase_id  = isBillExist(product.getBill_no());
				
					if(purchase_id > 0) {
						this.executeUpdate("UPDATE `purchase_details` SET total_purchesed_amount = " + product.getPurchase_price() + " where purchase_id = "+ purchase_id);
						return insertIntoProducts(product, purchase_id);
					}
				
				PreparedStatement preparedStatement = con.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setTimestamp(1, product.getPurchase_date());
				preparedStatement.setString(2, product.getVendor_name());
				preparedStatement.setString(3, product.getBill_no());
				preparedStatement.setDouble(4, product.getPurchase_price());
				
				int res = preparedStatement.executeUpdate();
					if(res > 0) {
						ResultSet rs = preparedStatement.getGeneratedKeys();
							if(rs.next()) {
							    purchase_id = rs.getInt(1);
								return insertIntoProducts(product, purchase_id);
							}
							return "ID_GEN_FAIL";
					}
					return "INSERTION_FAIL";

			} catch (Exception e) {
				System.out.println("error from purchaseDAO");
				System.out.println("from isBillExist");
				System.out.println(e);
			}
			
			return "";
		}
		
		private String insertIntoProducts(PurchaseMaster product, int purchase_id) {
			String sql = "INSERT INTO `products`(`p_name`, `brand_id`, `category_master_id`, `purchase_id`, `cost_price`, `price`, `avl_stock`, `added_date`,`type`, `exp_date`, `batch_no`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			String query = "UPDATE `products` SET `avl_stock` = ? , cost_price = ?, price = ?, `purchase_id`=?  WHERE p_name = ?";
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps2 = con.prepareStatement(query);
				for(int i=0; i < product.getPro_name().length ; i++) {
					
					//check if product already exist...
					if(new ProductDAO().isAlreadyExist(product.getPro_name()[i])) {
						int avl_stock = product.getQty()[i];
						ResultSet rs = this.createStatement("select avl_stock from `products` where p_name = '" + product.getPro_name()[i]  +"'");
						if(rs.next()) {
							 avl_stock = ( rs.getInt(1) + avl_stock ) ;
						}
						ps2.setInt(1, avl_stock);
						ps2.setDouble(2, product.getCost_price()[i]);
						ps2.setDouble(3, product.getPrice()[i]);
						ps2.setInt(4, purchase_id);
						ps2.setString(5, product.getPro_name()[i].toLowerCase());
						ps2.executeUpdate();

					}else {
						ps.setString(1, product.getPro_name()[i]);
						ps.setInt(2, product.getBrand_id()[i]);
						ps.setInt(3, product.getCat_id()[i]);
						ps.setInt(4, purchase_id);
						ps.setDouble(5, product.getCost_price()[i]);
						ps.setDouble(6, product.getPrice()[i]);
						ps.setInt(7, product.getQty()[i]);
						ps.setTimestamp(8, product.getPurchase_date());
						ps.setString(9, product.getType()[i]);
						ps.setString(10, product.getExp_date()[i]);
						ps.setString(11, product.getBatch_no()[i]);
						
						ps.executeUpdate();
					}
				}
			  con.close();
			  ps.close();
				ps2.close();
			  return "success";
			} catch (Exception e) {
				System.out.println("error form purchaseDAO insertIntoProducts()");
				System.out.println(e);
			}
		   return "fail";
		}
		
		public int isBillExist(String bill_no) {
			String query = "SELECT id from purchase_details where purchesed_bill_no = " + bill_no;
			try {
				ResultSet rs = this.createStatement(query);
					if(rs.next()) {
						return rs.getInt(1);
					}
			} catch (Exception e) {
				System.out.println("error from PurchaseDAO isBillExist");
				System.out.println(e);
			}
			
			return 0;
		}
		
}
