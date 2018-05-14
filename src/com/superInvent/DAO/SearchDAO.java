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
				case "purchase_details":
					break;
				case "invoice":
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
}
