package com.superInvent.controllers.product_master;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.ProductDAO;
import com.superInvent.POJO.ProductMaster;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(description = "add and edit products", urlPatterns = { "/add_product" })
public class AddProduct extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
			
		try {
				
				ProductMaster product   = new ProductMaster();
				product.setAdded_date(new Timestamp(new Date().getTime()));
				product.setP_name(request.getParameter("p_name").toLowerCase());
				product.setExpiry_date(request.getParameter("p_exp"));
				product.setCategory_master_id(Integer.parseInt(request.getParameter("p_category")));
				product.setBrand_id(Integer.parseInt(request.getParameter("p_brand")));
				product.setBatch_no(request.getParameter("p_batch"));
				product.setPrice(Double.parseDouble(request.getParameter("p_price")));
				product.setQty(Integer.parseInt(request.getParameter("p_quantity")));
				product.setWeight(Double.parseDouble(request.getParameter("p_weight")));
				product.setDesc(request.getParameter("p_desc"));
				product.setP_type(request.getParameter("p_type"));
				product.setStatus(1);
				
				new PrintWriter(response.getWriter()).print( new ProductDAO().insert(product));
		} catch (Exception e) {
			try {
				System.out.println("error form AddProduct servlet");
				new PrintWriter(response.getWriter()).print("fail");
			} catch (Exception e2) {
				System.out.println("Add product this will not execute");
				System.out.println(e2.getMessage());
			}
		}
		
		
	}

}
