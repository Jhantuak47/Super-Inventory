package com.superInvent.controllers.purchase_master;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.PurchaseDAO;

/**
 * Servlet implementation class GetProductRow
 */
@WebServlet(description = "return table row to add more products", urlPatterns = { "/get_purchase_item_row" })
public class GetProductRow extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			new PrintWriter(response.getWriter()).print(new PurchaseDAO().returnRow());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
