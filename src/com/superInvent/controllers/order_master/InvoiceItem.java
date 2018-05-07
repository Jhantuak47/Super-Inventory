package com.superInvent.controllers.order_master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.OrderDAO;

@WebServlet(description = "get Invoice item", urlPatterns = { "/get_item" })
public class InvoiceItem extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int row_num = (request.getParameter("row_num") != "" ) ? Integer.parseInt(request.getParameter("row_num")) : 0 ;
		System.out.println("row" + row_num);
		try {
			new PrintWriter(response.getWriter()).print( new OrderDAO().getRowItem(row_num));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
