package com.superInvent.controllers.report_master;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenerateReports
 */
@WebServlet(description = "will generate all reports", urlPatterns = { "/reports" })
public class GenerateReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
				System.out.println(request.getParameter("name"));
				String name = request.getParameter("name");
				String date = request.getParameter("date");
				String table = request.getParameter("table");
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("error from GenerateReports servlet");
		}
	}

}
