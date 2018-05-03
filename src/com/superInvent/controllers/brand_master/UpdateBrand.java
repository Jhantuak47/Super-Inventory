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
 * Servlet implementation class UpdateBrand
 */
@WebServlet(description = "update both status and brand", urlPatterns = { "/update" })
public class UpdateBrand extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				int id = Integer.parseInt(request.getParameter("id")) ;
				int status = Integer.parseInt(request.getParameter("status"));
				
				//calling update status then returning response..
				new PrintWriter(response.getWriter()).print(new BrandDAO().updateStatus(id, status)); 
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("error from updateBrand servlet");
		}
   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String brand_name = request.getParameter("edit_name");
			int id = Integer.parseInt(request.getParameter("brand_edit_id"));
			int status = (request.getParameter("status") == null) ? 0 : 1 ;
			String message = new BrandDAO().update(id, brand_name, status);
			new PrintWriter(response.getWriter()).print(message);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
