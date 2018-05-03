package com.superInvent.controllers.category_master;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.CategoryDAO;

/**
 * Servlet implementation class UpdateStatus
 */
@WebServlet(description = "update status of each category", urlPatterns = { "/update_cat_status" })
public class UpdateStatus extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
				int id = Integer.parseInt(request.getParameter("id")) ;
				int status = Integer.parseInt(request.getParameter("status"));
				
				//calling update status then returning response..
				new PrintWriter(response.getWriter()).print(new CategoryDAO().updateStatus(id, status)); 
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("error from updatecategory servlet");
		}
	}

}
