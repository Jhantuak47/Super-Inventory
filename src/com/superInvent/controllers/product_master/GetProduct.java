package com.superInvent.controllers.product_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.superInvent.DAO.ProductDAO;
import com.superInvent.POJO.ProductMaster;

/**
 * Servlet implementation class GetProduct
 */
@WebServlet(description = "getiting products detail on passing the id.", urlPatterns = { "/get_product" })
public class GetProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		System.out.println(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Gson json = new Gson();
			ProductMaster product = new ProductDAO().getProdcutById(id);
			String result = json.toJson(product);
			
			new PrintWriter(response.getWriter()).print(result);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
