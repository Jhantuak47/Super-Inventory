package com.superInvent.controllers.brand_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.BrandDAO;

@WebServlet(description = "add and list brands", urlPatterns = { "/brand_master" })
public class BrandMaster extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			String b_name = request.getParameter("b_name");
			com.superInvent.POJO.BrandMaster brand = new com.superInvent.POJO.BrandMaster();
			brand.setName(b_name);
			brand.setCreated_at(new java.sql.Timestamp(new java.util.Date().getTime()));
			brand.setUpdated_at(new java.sql.Timestamp(new java.util.Date().getTime()));
			//calling insert of brand dao.
			String message = new BrandDAO().insert(brand);
			//getting response..
			new PrintWriter(response.getWriter()).print(message);
			
		} catch (Exception e) {
			System.out.println(e);
			try {
				new PrintWriter(response.getWriter()).print("fail");
			} catch (Exception e2) {
				System.out.println(e2);
			}
			
		}
		
	}

}
