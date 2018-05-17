package com.superInvent.controllers.purchase_master;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.superInvent.DAO.ProductDAO;
import com.superInvent.DAO.PurchaseDAO;
import com.superInvent.POJO.ProductMaster;
import com.superInvent.POJO.PurchaseMaster;

@WebServlet(description = "insert product for each bill.", urlPatterns = { "/insert_product_bill" })
public class InsertNewProduct extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
			try {
				
					// Initializing pojos...
						PurchaseMaster product = new PurchaseMaster();
						
					product.setVendor_name(request.getParameter("ven_name"));
					product.setPurchase_date(new Timestamp(new Date().getTime()));
					product.setPurchase_price(Double.parseDouble(request.getParameter("sub_total")));
					product.setBill_no(request.getParameter("bill_no"));
					
					//brand
					String[] brandStirng = request.getParameterValues("b_id[]");
					int[] brandInt = new int[brandStirng.length]; 
					// Creates the integer array.
					for (int i = 0; i < brandStirng.length; i++){
						brandInt[i] = Integer.parseInt(brandStirng[i]); 
					//Parses the integer for each string.
					}
					//category
					String[] cidStirng = request.getParameterValues("cid[]");
					int[] cidInt = new int[cidStirng.length]; 
					// Creates the integer array.
					for (int i = 0; i < cidStirng.length; i++){
						cidInt[i] = Integer.parseInt(cidStirng[i]); 
					//Parses the integer for each string.
					}
					//qty
					String[] qtyStirng = request.getParameterValues("qty[]");
					int[] qtyInt = new int[qtyStirng.length]; 
					// Creates the integer array.
					for (int i = 0; i < qtyStirng.length; i++){
						qtyInt[i] = Integer.parseInt(qtyStirng[i]); 
					//Parses the integer for each string.
					}
					//cost_price
					String[] cpStirng = request.getParameterValues("cp[]");
					double[] cpDouble = new double[cpStirng.length]; 
					// Creates the integer array.
					for (int i = 0; i < cpStirng.length; i++){
						cpDouble[i] = Double.parseDouble(cpStirng[i]); 
					//Parses the integer for each string.
					}
					//selling_price
					String[] spStirng = request.getParameterValues("price[]");
					double[] spDouble = new double[spStirng.length];
					// Creates the integer array.
					for (int i = 0; i < spStirng.length; i++){
						spDouble[i] = Double.parseDouble(spStirng[i]); 
					//Parses the integer for each string.
					}
					
					product.setPro_name(request.getParameterValues("pro_name[]"));
					product.setBrand_id(brandInt);
					product.setCat_id(cidInt);
					product.setQty(qtyInt);
					product.setCost_price(cpDouble);
					product.setPrice(spDouble);
					product.setType(request.getParameterValues("type[]"));
					product.setExp_date(request.getParameterValues("exp_date[]"));
					product.setBatch_no(request.getParameterValues("b_no[]"));
					new PrintWriter(response.getWriter()).print(new PurchaseDAO().insert(product));
					
					
			} catch (Exception e) {
				System.out.println("\"Error from InsertNewProduct servlet I'm in line #" + 
					    e.getStackTrace()[0].getLineNumber());
				System.out.println(e);
			}
	}

}
