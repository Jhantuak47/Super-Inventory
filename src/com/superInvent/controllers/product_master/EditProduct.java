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
 * Servlet implementation class EditProduct
 */
@WebServlet(description = "edit products", urlPatterns = { "/edit_prod" })
public class EditProduct extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			ProductMaster product   = new ProductMaster();
			product.setId(Integer.parseInt(request.getParameter("edit_id")));
			product.setP_name(request.getParameter("p_edit_name").toLowerCase());
			product.setExpiry_date(request.getParameter("p_edit_exp"));
			product.setCategory_master_id(Integer.parseInt(request.getParameter("p_edit_category")));
			product.setBrand_id(Integer.parseInt(request.getParameter("p_edit_brand")));
			product.setBatch_no(request.getParameter("p_edit_batch"));
			product.setPrice(Double.parseDouble(request.getParameter("p_edit_price")));
			product.setStock(Integer.parseInt(request.getParameter("p_edit_stock")));
			product.setWeight(Double.parseDouble(request.getParameter("p_edit_weight")));
			product.setDesc(request.getParameter("p_edit_desc"));
			product.setP_type(request.getParameter("p_edit_type"));
			new PrintWriter(response.getWriter()).print( new ProductDAO().update(product));
	} catch (Exception e) {
		try {
			System.out.println("error form AddProduct servlet");
			System.out.println(e);
			new PrintWriter(response.getWriter()).print("fail");
		} catch (Exception e2) {
			System.out.println("Add product this will not execute");
			System.out.println(e2.getMessage());
		}
	}
	
	}

}
