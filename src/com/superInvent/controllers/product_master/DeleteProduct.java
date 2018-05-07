package com.superInvent.controllers.product_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.ProductDAO;

@WebServlet(description = "for deleting product", urlPatterns = { "/delete_product" })
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prod_id  = Integer.parseInt(request.getParameter("id"));
		try {
			ProductDAO dao =  new ProductDAO();
			String message = dao.delete(prod_id);
			new PrintWriter(response.getWriter()).print(message);
			
		} catch (Exception e) {
			try {
				System.out.println("error form DeleteCategory servlet !");
				System.out.println(e);
				new PrintWriter(response.getWriter()).print("fail");
			} catch (Exception e2) {
				System.out.println("error form e2");
			}
			
		}
	}

}
