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
		BrandDAO dao =  new BrandDAO();
		String message = "fail";
		try {
				if(request.getParameter("status").equals("multiple")) {
					
					String[] ids = request.getParameterValues("ids[]");
					message = dao.deleteMultiple(ids);
				}else {
					int id  = Integer.parseInt(request.getParameter("id"));
				    message = dao.delete(id);
				}
				
				new PrintWriter(response.getWriter()).print(message);
			
		} catch (Exception e) {
			try {
				System.out.println("error form DeleteBrand servlet !");
				System.out.println(e);
				new PrintWriter(response.getWriter()).print("fail");
			} catch (Exception e2) {
				System.out.println("error form e2");
			}
			
		}
	}

}
