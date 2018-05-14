package com.superInvent.controllers.product_master;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.superInvent.DAO.Pagination;
import com.superInvent.POJO.Demo;
import com.superInvent.POJO.ProductMaster;
import com.superInvent.Services.product_master.ProductServices;

/**
 * Servlet implementation class GetProduct
 */
@WebServlet(description = "list product", urlPatterns = { "/list_product"})
public class ListProduct extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		int currentPage =( request.getParameter("page_no") == null ) ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
		String link = "list_product";
		try {
			List<ProductMaster> products = null;
			Object[] objects = new Pagination().listWithPagination("products", currentPage, numberOfResultPerPage, link);
			ResultSet rs = (ResultSet) objects[0];
			if(rs != null && rs.isBeforeFirst()){
				products = new ArrayList<ProductMaster>();
				while(rs.next()) {
					ProductMaster product = new ProductMaster();
					product.setId(rs.getInt("id"));
					product.setP_name(rs.getString("name"));
					product.setBrand(rs.getString("brand"));
					product.setCategory(rs.getString("category"));
					product.setPrice(rs.getDouble("price"));
					product.setStock(rs.getInt("stock"));
					product.setAdded_date(rs.getTimestamp("added_on"));
					product.setStatus(rs.getInt("state"));
					product.setWeight(rs.getDouble("wt"));
					product.setP_type(rs.getString("type"));
					product.setExpiry_date((rs.getString("exp_date").equals("")) ?"<i>Nill</i>" : rs.getString("exp_date"));
					product.setBatch_no(rs.getString("batch_no"));
					product.setDesc(rs.getString("dsc"));
					products.add(product);
				}
			}
				request.setAttribute("products", products);
				request.setAttribute("pagination", objects[1]);
				request.setAttribute("page_no", currentPage);
				request.getRequestDispatcher("/views/modules/product_master/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println("error from ListProductPagination servlet");
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String tbody = "fail";
		Gson gson = new Gson();
		int currentPage =( request.getParameter("page_no") == null ) ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
		String link = "list_product";
		
		 try {
				//calling to pagination class list with pagination method,,
				Object[] objects = new Pagination().listWithPagination("products", currentPage, numberOfResultPerPage, link);
				
				ResultSet rs = (ResultSet) objects[0];
				String pagination = (String) objects[1];
				
				//check if result set is not empty..
				if(rs !=  null && rs.isBeforeFirst()) {
				    tbody = new ProductServices().buieldProductsTable(rs, currentPage, numberOfResultPerPage);
				    if(tbody != null) {
				    	Demo obj = new Demo();
						obj.setPaginations(pagination);
						obj.setTbody(tbody);
						String jsonResult = gson.toJson(obj);
						new PrintWriter(response.getWriter()).print(jsonResult);
				    	
				    }else {
				    	new PrintWriter(response.getWriter()).print(tbody);
				    }
				}else {
					tbody = "empty table";
					new PrintWriter(response.getWriter()).print(tbody);
				}
		} catch (Exception e) {
			System.out.println("error form doPost() ListBrandPag..");
			System.out.println(e);
		}
	}

}
