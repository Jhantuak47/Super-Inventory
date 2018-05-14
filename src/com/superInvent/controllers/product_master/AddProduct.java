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
			
				String bill_no = request.getParameter("bill_no");
				String vendor_name = request.getParameter("ven_name");
				ProductMaster product   = new ProductMaster();
				product.setAdded_date(new Timestamp(new Date().getTime()));
				product.setP_name(request.getParameter("p_name").toLowerCase());
				product.setExpiry_date(request.getParameter("p_exp"));
				product.setCategory_master_id(Integer.parseInt(request.getParameter("p_category")));
				product.setBrand_id(Integer.parseInt(request.getParameter("p_brand")));
				product.setBatch_no(request.getParameter("p_batch"));
				product.setPrice(Double.parseDouble(request.getParameter("p_price")));
				product.setCost_price(Double.parseDouble(request.getParameter("cost_price")));
				product.setStock(Integer.parseInt(request.getParameter("p_quantity")));
				product.setDesc(request.getParameter("p_desc"));
				product.setP_type(request.getParameter("p_type"));
				product.setStatus(1);
				product.setIs_deleted(0);
				new PrintWriter(response.getWriter()).print( new ProductDAO().insert(product, bill_no, vendor_name));
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
