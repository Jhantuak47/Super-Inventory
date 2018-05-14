package com.superInvent.controllers.category_master;

import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.CategoryDAO;

/**
 * Servlet implementation class DeleteCategory
 */
@WebServlet(description = "for deleting category", urlPatterns = { "/delete_cat" })
public class DeleteCategory extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		CategoryDAO dao = new CategoryDAO();
		String message = "";
		try {

			if (request.getParameter("status").equals("multiple")) {
				System.out.println("from = " + request.getParameter("status"));
				String[] ids = request.getParameterValues("ids[]");
				message = dao.deleteMultiple(ids);
			} else {
				int cat_id = Integer.parseInt(request.getParameter("id"));
				message = dao.delete(cat_id);
			}

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
