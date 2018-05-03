package com.superInvent.controllers.category_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.CategoryDAO;

/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/add_category")
public class AddCategory extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
		try {
			request.setAttribute("allCategories", new CategoryDAO().getAllCategories());
			request.getRequestDispatcher("./views/modules/category_master/add_category.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			String cat_name = request.getParameter("cat_name");
			int parent_cat =Integer.parseInt(request.getParameter("parent_cat")) ;
			System.out.println("status = "+request.getParameter("status"));
			int status = (request.getParameter("status") == null) ? 0 : 1 ;
			String message = new CategoryDAO().addCategory(cat_name, parent_cat, status);
			new PrintWriter(response.getWriter()).print(message);
		} catch (Exception e) {
			System.out.println("error from doPost AddCategory");
		}
		
	}

}
