package com.superInvent.controllers.order_master;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.OrderDAO;
import com.superInvent.POJO.Invoice;
import com.superInvent.POJO.InvoiceDetails;

/**
 * Servlet implementation class InsertInvoice
 */
@WebServlet(description = "feed invoice data into database", urlPatterns = { "/insert_invoice" })
public class InsertInvoice extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			
				//inserting invoice pojo...
			
				Invoice invoice = new Invoice();
				Timestamp created_at  = new Timestamp(new Date().getTime());
				invoice.setOrder_date(created_at);
				invoice.setCust_name(request.getParameter("cust_name"));
				invoice.setSub_tot(Double.parseDouble( request.getParameter("sub_total")));
				invoice.setDsicount(Double.parseDouble(request.getParameter("discount")));
				invoice.setGst(Double.parseDouble(request.getParameter("gst")));
				invoice.setNet_tot(Double.parseDouble(request.getParameter("net_tot")));
				invoice.setPaid_amt(Double.parseDouble(request.getParameter("paid")));
				invoice.setDue(Double.parseDouble(request.getParameter("due")));
				invoice.setPayment_method(request.getParameter("payment_type"));
				
				//	inserting invoiice details pojo....
				InvoiceDetails invoicedetails = new InvoiceDetails();
				String[] pidStirng = request.getParameterValues("pid[]");
				int[] pidInt = new int[pidStirng.length]; 
				// Creates the integer array.
				for (int i = 0; i < pidStirng.length; i++){
					pidInt[i] = Integer.parseInt(pidStirng[i]); 
				//Parses the integer for each string.
				}
				
				String[] priceStirng = request.getParameterValues("price[]");
				double[] priceDbl = new double[priceStirng.length]; 
				// Creates the integer array.
				for (int i = 0; i < priceStirng.length; i++){
					priceDbl[i] = Integer.parseInt(priceStirng[i]); 
				//Parses the integer for each string.
				}
				
				String[]  qtyStirng= request.getParameterValues("qty[]");
				int[] qtyInt = new int[qtyStirng.length]; 
				// Creates the integer array.
				for (int i = 0; i < qtyStirng.length; i++){
					qtyInt[i] = Integer.parseInt(qtyStirng[i]); 
				//Parses the integer for each string.
				}
				String[]  tqtyStirng= request.getParameterValues("tqty[]");
				int[] tqtyInt = new int[tqtyStirng.length]; 
				// Creates the integer array.
				for (int i = 0; i < tqtyStirng.length; i++){
					tqtyInt[i] = Integer.parseInt(tqtyStirng[i]); 
				//Parses the integer for each string.
				}
				invoicedetails.setProduct_id(pidInt);
				invoicedetails.setProduct_price(priceDbl);
				invoicedetails.setQty(qtyInt);
				invoicedetails.setTqty(tqtyInt);
				
				new PrintWriter(response.getWriter()).print(new OrderDAO().insert(invoice, invoicedetails));
				
		} catch (Exception e) {
			System.out.println("error from InsertVoice servlet ");
			System.out.println(e);
		}
	}

}
