package com.superInvent.controllers.brand_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.BrandDAO;
import com.superInvent.Services.brand_master.BrandMaster;

/**
 * Servlet implementation class GetBrand
 */
@WebServlet(description = "listing all brands", urlPatterns = { "/get_brand" })
public class GetBrand extends HttpServlet {
	String result ="";
	//list user only name and id..
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		BrandDAO dao = new BrandDAO();
		try {
			//calling service getAllbrand passing list of brands.
			result= new BrandMaster().getAllBrands(dao.list());
			
			new PrintWriter(response.getWriter()).print(result);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("error from GetBrand servlet");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
