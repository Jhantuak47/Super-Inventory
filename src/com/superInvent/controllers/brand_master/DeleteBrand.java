package com.superInvent.controllers.brand_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.BrandDAO;

/**
 * Servlet implementation class DeleteBrand
 */
@WebServlet(description = "delete brands", urlPatterns = { "/delete_brand" })
public class DeleteBrand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id  = Integer.parseInt(request.getParameter("id"));
		try {
			BrandDAO dao =  new BrandDAO();
			String message = dao.delete(id);
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
