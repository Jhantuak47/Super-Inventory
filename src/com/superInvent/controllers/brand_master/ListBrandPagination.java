package com.superInvent.controllers.brand_master;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.superInvent.DAO.Pagination;
import com.superInvent.POJO.BrandMaster;
import com.superInvent.POJO.Demo;
import com.superInvent.Services.brand_master.BrandServices;

/**
 * Servlet implementation class ListBrandPagination
 */
@WebServlet(description = "list the brands with pagination", urlPatterns = { "/list_brand" })
public class ListBrandPagination extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage =( request.getParameter("page_no") == null ) ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
		String link = "list_brand";
		try {
			List<BrandMaster> brands = null;
			Object[] objects = new Pagination().listWithPagination("brand", currentPage, numberOfResultPerPage, link);
			ResultSet rs = (ResultSet) objects[0];
			if(rs != null && rs.isBeforeFirst()){
				brands = new ArrayList<BrandMaster>();
				while(rs.next()) {
					BrandMaster brand = new BrandMaster();
					brand.setId(rs.getInt("id"));
					brand.setName(rs.getString("b_name"));
					brand.setCreated_at(rs.getTimestamp("created_at"));
					brand.setStatus(rs.getInt("status"));
					brand.setUpdated_at(rs.getTimestamp("updated_at"));
					brand.setIs_deleted(rs.getInt("is_deleted"));
					brands.add(brand);
				}
			}
				request.setAttribute("brands", brands);
				request.setAttribute("pagination", objects[1]);
				request.setAttribute("page_no", currentPage);
				request.getRequestDispatcher("./views/modules/brands/index.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println("error from ListBrandPagination servlet");
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		String tbody = "fail";
		Gson gson = new Gson();
		int currentPage =( request.getParameter("page_no") == null ) ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
		String link = "list_brand";
		
		 try {
				//calling to pagination class list with pagination method,,
				Object[] objects = new Pagination().listWithPagination("brand", currentPage, numberOfResultPerPage, link);
				
				ResultSet rs = (ResultSet) objects[0];
				String pagination = (String) objects[1];
				
				//check if result set is not empty..
				if(rs !=  null && rs.isBeforeFirst()) {
				    tbody = new BrandServices().buieldBrandsTable(rs, currentPage, numberOfResultPerPage);
				    if(tbody != null) {
				    	Demo obj = new Demo();
						obj.setPaginations(pagination);
						obj.setTbody(tbody);
						String jsonResult = gson.toJson(obj);
						new PrintWriter(response.getWriter()).print(jsonResult);
				    	
				    }else {
				    	new PrintWriter(response.getWriter()).print(tbody);
				    }
				}else {
					tbody = "empty table";
					new PrintWriter(response.getWriter()).print(tbody);
				}
		} catch (Exception e) {
			System.out.println("error form doPost() ListBrandPag..");
			System.out.println(e);
		}
	}

}
