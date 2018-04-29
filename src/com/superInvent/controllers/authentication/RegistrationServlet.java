package com.superInvent.controllers.authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.LoginRegistrationDAO;
import com.superInvent.POJO.Users;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(description = "user registration", urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		try{
			Users user = new Users();
			user.setName(request.getParameter("name"));
			user.setAddress(request.getParameter("address"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setIs_admin(false);
			user.setMobile(request.getParameter("mobile"));
			user.setLast_login(new java.sql.Timestamp(new java.util.Date().getTime()));//set current date..
			user.setCreatedAt(new java.sql.Timestamp(new java.util.Date().getTime()));
			
			out.print(new LoginRegistrationDAO().insert(user));
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

}
