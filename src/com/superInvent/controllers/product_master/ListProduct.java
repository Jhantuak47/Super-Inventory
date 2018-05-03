package com.superInvent.controllers.product_master;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.Pagination;
import com.superInvent.POJO.BrandMaster;
import com.superInvent.POJO.ProductMaster;

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
					product.setQty(rs.getInt("qty"));
					product.setAdded_date(rs.getTimestamp("added_on"));
					product.setStatus(rs.getInt("state"));
					product.setWeight(rs.getDouble("wg"));
					product.setP_type(rs.getString("type"));
					product.setExpiry_date(rs.getString("exp_date"));
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
