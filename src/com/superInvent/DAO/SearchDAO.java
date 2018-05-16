package com.superInvent.DAO;

import java.sql.ResultSet;

import com.superInvent.Services.brand_master.BrandServices;
import com.superInvent.Services.category_master.CategoryServices;
import com.superInvent.Services.product_master.ProductServices;

public class SearchDAO extends JDBCConnection{

		public String search(String table_name, String searchType, String searchValue) {
			String sql = "";
			String tbody = "";
			try {
				
				switch(table_name) {
				case "products":
					sql = "SELECT * FROM (SELECT * FROM(SELECT p.id, p.p_name as name, b.b_name brand, c.c_name category, p.price as price, p.avl_stock as stock, p.added_date added_on, "
							+ "p.status state,wt, type, exp_date, batch_no, Description as dsc, p.is_deleted from products p LEFT JOIN brand b ON p.brand_id = b.id "
							+ "LEFT JOIN category_master c on p.category_master_id = c.id)"
							+ " as temp WHERE is_deleted != 1 ORDER BY id DESC) as myTemp WHERE `"+ searchType +"` LIKE '"+ searchValue +"%'";
					
						switch(searchType) {
						case "expired":
							sql = "SELECT * FROM(SELECT * FROM(SELECT p.id, p.p_name as name, b.b_name brand, c.c_name category, p.price as price, p.avl_stock as stock, p.added_date added_on, "
									+ "p.status state,wt, type, exp_date, batch_no, Description as dsc, p.is_deleted from products p LEFT JOIN brand b ON p.brand_id = b.id "
									+ "LEFT JOIN category_master c on p.category_master_id = c.id)"
									+ "as temp WHERE is_deleted != 1 ORDER BY id DESC)"
									+ " as myTemp WHERE exp_date <= CURDATE()";
							break;
						case "price":
							sql = "SELECT * FROM(SELECT * FROM(SELECT p.id, p.p_name as name, b.b_name brand, c.c_name category, p.price as price, p.avl_stock as stock,"
									+ " p.added_date added_on, p.status state,wt, type, exp_date, batch_no, Description as dsc, p.is_deleted from products p LEFT JOIN brand b ON p.brand_id = b.id "
									+ "LEFT JOIN category_master c on p.category_master_id = c.id)as temp WHERE is_deleted != 1 ORDER BY id DESC) as myTemp WHERE price > '"+ searchValue +"'";
							break;
						}
						
					break;
				case "brand":
					sql = "SELECT * FROM brand WHERE `b_name` LIKE '"+ searchValue +"%'";
					break;
				case "category_master":
					sql = "SELECT * FROM(SELECT * FROM "
							+ "(SELECT p.id as id, p.c_name as category, c.c_name as parent, c.id as parent_id, p.c_status as status, p.is_deleted as is_deleted "
							+ "FROM category_master p LEFT JOIN category_master c ON c.id = p.parrent_category)"
							+ "as searchTBL WHERE category LIKE '"+ searchValue +"%')as myTable WHERE myTable.is_deleted != 1 ORDER BY id DESC";
					break;
				default :
					break;
				}
				ResultSet rs = this.createStatement(sql);
					if(rs.isBeforeFirst()) {
						if(table_name.equals("products"))
							tbody = new ProductServices().buieldProductsTable(rs, 1, 0);
						if(table_name.equals("brand"))
							tbody = new BrandServices().buieldBrandsTable(rs, 1, 0);
						if(table_name.equals("category_master"))
							tbody = new CategoryServices().buildCatgoryTable(rs, 1, 0);
					}
				
			} catch (Exception e) {
				System.out.println("error form search dao..");
				System.out.println(e);
			}
			return tbody;
		}
		
		public ResultSet reports(String table, String[] param) {
			String sql = "";
			try {
				switch (table) {
				case "purchase_details":
					break;
				case "invoice":
					switch (param.length) {
					case 2:
						switch (param[0]) {
						case "product":
							
							break;
						case "name":
							
							break;
						default:
							break;
						}
						break;
					case 4:
						//for two parameters
						switch (param[0]) {
						case "DATE_PRODUCT":
							//for 2 parameter (date and p_name)...
							sql = "SELECT * FROM (SELECT invoice_no, cust_name, order_date, net_total, paid_amount,due,payment_method, products.p_name as name,product_price,qty "
									+ "FROM (SELECT c.invoice_no , cust_name,order_date, sub_total,paid_amount, product_id, due, payment_method,product_price,net_total, qty"
									+ " FROM (SELECT * FROM invoice WHERE order_date BETWEEN '"+param[2]+"' AND '"+ param[3] +"') as c LEFT JOIN invoice_details p ON c.invoice_no = p.invoice_no)as report"
									+ " LEFT JOIN products on report.product_id = products.id)as finalTbl WHERE name in ('"+ param[1] +"')";
							break;
						case "DATE_NAME":
							//for 2 parameter (date and name)...
							sql = "SELECT invoice_no, cust_name, order_date, net_total, paid_amount,due,payment_method, products.p_name as name,product_price,qty "
									+ "FROM (SELECT c.invoice_no , cust_name,order_date, sub_total,paid_amount, product_id, due, payment_method,product_price,net_total, qty "
									+ "FROM (SELECT * FROM invoice WHERE cust_name in ('"+ param[1] +"') and order_date BETWEEN '"+param[2]+"' AND '"+ param[3] +"') as c "
									+ "LEFT JOIN invoice_details p ON c.invoice_no = p.invoice_no)as report LEFT JOIN products on report.product_id = products.id";
							break;
						default:
							System.out.println("from product and name");
							break;
						}
						break;
					default:
						//for all parameters
						sql = "SELECT * FROM(SELECT invoice_no, cust_name, order_date, net_total, paid_amount,due,payment_method, products.p_name as name,product_price,qty "
								+ "FROM (SELECT c.invoice_no , cust_name,order_date, sub_total,paid_amount, product_id, due, payment_method,product_price,net_total, qty"
								+ " FROM (SELECT * FROM invoice WHERE cust_name in ('"+ param[1] +"') and order_date BETWEEN '"+param[2]+"' AND '"+ param[3] +"')as c "
								+ "LEFT JOIN invoice_details p ON c.invoice_no = p.invoice_no)as report LEFT JOIN products on report.product_id = products.id) tempTbl WHERE name in ('"+param[4]+"')";
						System.out.println("from here also");
						break;
					}//end of second switch..
					break;
				default:
					System.out.println("nither invoice nor purchase...");
					break;
				}//end of first switch..
				System.out.println(sql);
				ResultSet rs = this.createStatement(sql);
				if(rs.isBeforeFirst()) {
					return rs;
				}
				
			} catch (Exception e) {
				System.out.println("error from SearchDAO reports..");
				System.out.println(e);
			}

			return null;
		}
}
