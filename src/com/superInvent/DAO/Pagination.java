package com.superInvent.DAO;

import java.sql.ResultSet;

public class Pagination extends JDBCConnection{
		int currentPage = 0;
		int numberOFRecordsPerPage = 10;
		public Object[] pagination(String table, int currentPage, int numberOFRecordsPerPage, String link){
			String pagination = "<ul class=\"pagination list-inline text-center d-flex justify-content-center align-items-center\">";
			String limit = "";
			String query = "SELECT COUNT(*) as rows FROM "+ table + " where is_deleted != 1";
			int totalRecords = 0;
			int lastPage = 0;
			
			this.currentPage = currentPage;
			this.numberOFRecordsPerPage = numberOFRecordsPerPage;
			
			try {
				ResultSet rs = this.createStatement(query);
				if(rs.isBeforeFirst()) {
					rs.next();
					totalRecords = rs.getInt("rows");
				}
			    lastPage = (int)Math.ceil(((double)totalRecords)/this.numberOFRecordsPerPage);
				if(lastPage > 1) {
					if(this.currentPage > 1) {
						int previous = 0;
						previous = this.currentPage - 1;
						pagination += "<li class=\"page-item\"><a class=\"page-link\" href ='./"+ link +"?page_no="+ previous +"' style = 'color:#333;'> previous </a></li>";
					}
					
					for(int i = (this.currentPage - 5); i < this.currentPage; i++) {
						if(i > 0)
						pagination += "<li class=\"page-item\"><a class=\"page-link\" href = './"+ link +"?page_no="+ i +"'>" + i +"</a></li>"; 
					}
					
					pagination += "<li class=\"page-item active\"><a class=\"page-link\" href = './"+ link +"?page_no=" + this.currentPage +"' style = 'color:#333' >"+ this.currentPage +"</a></li>" ;
					
					for(int i = this.currentPage+1; i <= lastPage; i++) {
						pagination += "<li class=\"page-item\"><a class=\"page-link\" href ='./"+ link +"?page_no="+ i +"'>"+ i +"</a></li>";
						if(i > this.currentPage + 4)
							break;	
					}
					
					if(lastPage > this.currentPage) {
						int next = this.currentPage+1;
						pagination += "<li class=\"page-item\"><a class=\"page-link\" href = './"+ link +"?page_no="+ next +"'>next</a></li>";
						
					}
					limit = "LIMIT " + (this.currentPage - 1) * numberOFRecordsPerPage  +"," + numberOFRecordsPerPage ;
				}
				pagination +="</ul>";
			} catch (Exception e) {
				System.out.println("eror form pagination class");
			}		
			return new Object[] {pagination, limit, lastPage};
	   }
		
		public Object[] listWithPagination(String table,int currentPage, int numberOFRecordsPerPage,String link) {
			ResultSet rs = null;
			Object[] objects = new Object[] {};
			String sql = "";
			try {
				objects = this.pagination(table, currentPage, numberOFRecordsPerPage, link);
				//listing categories..
				if(table.equals("category_master")) {
					sql = "SELECT * FROM ("
							+ "SELECT p.id as id, p.c_name as category, c.c_name as parent, c.id as parent_id, p.c_status as status, p.is_deleted as is_deleted"
					 		+" FROM category_master p"
					 			+" LEFT JOIN category_master c"
					 				+" ON c.id = p.parrent_category "
					 					 +")as myTable"
					 							+ " WHERE myTable.is_deleted != 1 "
					 							 +"ORDER BY id DESC "
					 							 + objects[1];
				}else if(table.equals("products")){
					sql = "SELECT * FROM(SELECT p.id, p.p_name as name, b.b_name brand, c.c_name category, p.price as price, p.avl_stock as stock, "
							+ "p.added_date added_on, p.status state,wt, type, exp_date, batch_no, Description as dsc, p.is_deleted from products p "
							+ "LEFT JOIN brand b ON p.brand_id = b.id LEFT JOIN category_master c on p.category_master_id = c.id) as temp"
							+ " WHERE is_deleted != 1 "
							 +"ORDER BY id DESC "
							 + objects[1];
				}else {
					//listing for other tables..
					 sql = "SELECT * from " + table +" where is_deleted != 1 ORDER BY `id` DESC "+ objects[1];
				}
				 rs = this.createStatement(sql);
				
			} catch (Exception e) {
				System.out.println("error from pegination Dao");
				System.out.println(e);
			}
			 
			//Object [ resultSet, pagination, lastPage ]  ...
			return new Object[] {rs, objects[0], objects[2]};
		}
		
		public Object[] paginationWithAjax(String table, int currentPage, int numberOFRecordsPerPage){
			String pagination = "<ul class=\"pagination list-inline text-center d-flex justify-content-center align-items-center\">";
			String limit = "";
			String query = "SELECT COUNT(*) as rows FROM "+ table + " where is_deleted != 1";
			int totalRecords = 0;
			int lastPage = 0;
			
			this.currentPage = currentPage;
			this.numberOFRecordsPerPage = numberOFRecordsPerPage;
			
			try {
				ResultSet rs = this.createStatement(query);
				if(rs.isBeforeFirst()) {
					rs.next();
					totalRecords = rs.getInt("rows");
				}
			    lastPage = (int)Math.ceil(((double)totalRecords)/this.numberOFRecordsPerPage);
				
				if(lastPage > 1) {
					if(this.currentPage > 1) {
						int previous = 0;
						previous = this.currentPage - 1;
						pagination += "<li class=\"page-item\"><a class=\"page-link\" pn = '"+ previous +"' href ='#' style = 'color:#333;'> previous </a></li>";
					}
					
					for(int i = (this.currentPage - 5); i < this.currentPage; i++) {
						if(i > 0)
						pagination += "<li class=\"page-item\"><a class=\"page-link\" pn = '"+ i +"' href = '#'>" + i +"</a></li>"; 
					} 
					
					pagination += "<li class=\"page-item active\"><a class=\"page-link\" pn = '"+ this.currentPage +"' href = '#' style = 'color:#333' >"+ this.currentPage +"</a></li>" ;
					
					for(int i = this.currentPage+1; i <= lastPage; i++) {
						pagination += "<li class=\"page-item\"><a class=\"page-link\" pn = '"+ i +"' href ='#'>"+ i +"</a></li>";
						if(i > this.currentPage + 4)
							break;	
					}
					
					if(lastPage > this.currentPage) {
						int next = this.currentPage+1;
						pagination += "<li class=\"page-item\"><a class=\"page-link\" pn = '"+ next +"' href = '#'>next</a></li>";
						
					}
					limit = "LIMIT " + (this.currentPage - 1) * numberOFRecordsPerPage  +"," + numberOFRecordsPerPage ;
				}
				pagination +="</ul>";
			} catch (Exception e) {
				System.out.println("eror form pagination class");
			}		
			return new Object[] {pagination, limit};
	   }
		
		public Object[] listCategoryWithPaginationUsingAjax(String table,int currentPage, int numberOFRecordsPerPage) {
			ResultSet rs = null;
			Object[] objects = new Object[] {};
			String sql = "";
			try {
				objects = this.paginationWithAjax(table, currentPage, numberOFRecordsPerPage);
				
					//listing categories..
					if(table.equals("category_master")) {
						sql =  "SELECT * FROM ("
								+ "SELECT p.id as id, p.c_name as category, c.c_name as parent, c.id as parent_id, p.c_status as status, p.is_deleted as is_deleted"
						 		+" FROM category_master p"
						 			+" LEFT JOIN category_master c"
						 				+" ON c.id = p.parrent_category "
						 					 +")as myTable"
						 							+ " WHERE myTable.is_deleted != 1 "
						 							 +"ORDER BY id DESC "
						 							 + objects[1];
						
					}else {
						//listing for other tables..
						 sql = "SELECT * from " + table +" where is_deleted != 1 ORDER BY `id` DESC "+ objects[1];
					}
					rs = this.createStatement(sql);
					
					if(rs.isBeforeFirst()) {
						//Object [ resultSet, pagination ]  ...
						return new Object[] {rs, objects[0]};
					}else {
						return new Object[] {"", ""};
					}
					
				 
			} catch (Exception e) {
				System.out.println("error from pegination Dao");
				System.out.println(e);
			}
			 
			return null;
		}
}
