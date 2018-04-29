package com.superInvent.controllers.category_master;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.CategoryDAO;
import com.superInvent.POJO.CategoryMaster;
import com.superInvent.Services.category_master.CategoryServices;

/**
 * Servlet implementation class ListCategory
 */
@WebServlet(description = "Use to list all categories", urlPatterns = { "/list_category" })
public class ListCategory extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<CategoryMaster> categoryLists =  new ArrayList<CategoryMaster>();
			
			categoryLists = new CategoryDAO().list();
			
			if(categoryLists != null && !categoryLists.isEmpty()) {
				request.setAttribute("categoryLists", categoryLists);
				request.getRequestDispatcher("./views/modules/category_master/index.jsp").forward(request, response);
			}else {
				response.sendRedirect("./error.jsp");
			}
		
			/*for(CategoryMaster categoryList:categoryLists) {
				System.out.println(categoryList.getC_name());
			}*/
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("from here");
		String result = "";
		try {
			List<Object[]> categories= new CategoryDAO().getAllCategories();
			
			//calling service method to get all category name and id's..
			result = new CategoryServices().getAllCategory(categories);
			new PrintWriter(response.getWriter()).print(result);
		} catch (Exception e) {
			System.out.println("from do post of list category..");
			System.out.println(e.getMessage());
		}

	}

}
