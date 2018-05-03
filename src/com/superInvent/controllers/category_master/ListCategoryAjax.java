package com.superInvent.controllers.category_master;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.superInvent.DAO.Pagination;
import com.superInvent.POJO.Demo;
import com.superInvent.Services.category_master.CategoryServices;

/**
 * Servlet implementation class ListCategoryAjax
 */
@WebServlet(description = "listing category from js file using ajax.", urlPatterns = { "/list_cat_ajax" })
public class ListCategoryAjax extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String page_no = request.getParameter("page_no");
		page_no = (page_no != null) ? page_no : "1";
		int currentPage = Integer.parseInt(page_no);
		int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
		
		try {
				Gson gson = new Gson();
			//calling method of pagination DAO..
			Object[] objects = new Pagination().listCategoryWithPaginationUsingAjax("category_master", currentPage, numberOfResultPerPage);
			
			if(objects != null) {
				if(objects[0] != "" || objects[1] != "") {
					String pagination  = (String)objects[1];
					ResultSet rs = (ResultSet) objects[0];
					String tbody = "fail";
					if(rs.isBeforeFirst()) {
					//Calling service for building table...
					 tbody =new CategoryServices().buildCatgoryTable(rs, currentPage, numberOfResultPerPage);
					}
					Demo obj = new Demo();
					obj.setPaginations(pagination);
					obj.setTbody(tbody);
					String jsonResult = gson.toJson(obj);
					new PrintWriter(response.getWriter()).print(jsonResult);	
				}else {
					System.out.println("from empty table of ListCategroyAjax doget");
					new PrintWriter(response.getWriter()).print("empty table");
				}
			}else {
				System.out.println("from fail of ListCategroyAjax doget");
				new PrintWriter(response.getWriter()).print("fail");
			}
			
		} catch (Exception e) {
			System.out.println("from listcategoryajax servlet servlet");
			System.out.println(e);
		}
		
	}
}
