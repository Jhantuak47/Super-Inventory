package com.superInvent.controllers.search_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.SearchDAO;

@WebServlet(description = "search by parameter", urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			String searchType = request.getParameter("searchType");
			String searchValue = request.getParameter("searchValue");
			String searchTable = request.getParameter("searchTable");
			
			new PrintWriter(response.getWriter()).print(new SearchDAO().search(searchTable, searchType, searchValue));
		} catch (Exception e) {
			System.out.println("error form searchServlet");
			System.out.println(e);
		}
	}

}
