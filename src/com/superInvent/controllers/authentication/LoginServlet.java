package com.superInvent.controllers.authentication;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.superInvent.DAO.LoginRegistrationDAO;
import com.superInvent.POJO.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "user login servlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String email  = request.getParameter("login_email");
			String pass = request.getParameter("login_password");
			 Date dt = new Date();  
	         SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yy, hh:mm");
			Users user = new LoginRegistrationDAO().getUserDetails(pass, email);
				if(user != null && user instanceof Users) {
					HttpSession session = request.getSession();
					session.setAttribute("name", user.getName());
					session.setAttribute("email", user.getEmail());
					session.setAttribute("is_admin", user.getIs_admin());
					session.setAttribute("mobile", user.getMobile());
					session.setAttribute("last_login", formatter.format(user.getLast_login()));
					session.setAttribute("createdAt", user.getCreatedAt());
					//updating login time..
					new LoginRegistrationDAO().updateLastLogin(email);
					response.sendRedirect("./views/dashboard.jsp");
				}else {
					System.out.println("fail");
				}
			
		} catch (Exception e) {
			System.out.println("error form LoginServlet");
			System.out.println(e);
		}
		
	}

}
