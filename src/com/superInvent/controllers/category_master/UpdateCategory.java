package com.superInvent.controllers.category_master;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.CategoryDAO;

/**
 * Servlet implementation class UpdateCategory
 */
@WebServlet(description = "update the category item", urlPatterns = { "/update_cat" })
public class UpdateCategory extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
			
			try {
				String c_name = request.getParameter("edit_name");
				int id = Integer.parseInt(request.getParameter("cat_edit_id"));
				int paretn_id = Integer.parseInt(request.getParameter("edit_type"));
				int status = (request.getParameter("status") == null) ? 0 : 1 ;
				String message = new CategoryDAO().update(id, c_name, paretn_id, status);
				new PrintWriter(response.getWriter()).print(message);
				
			} catch (Exception e) {
				System.out.println("eror from updatecategory servlet");
				System.out.println(e);
				try {
					new PrintWriter(response.getWriter()).print("fail");
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
	}

}
