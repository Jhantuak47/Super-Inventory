package com.superInvent.controllers.order_master;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.superInvent.POJO.Invoice;
import com.superInvent.POJO.InvoiceDetails;

/**
 * Servlet implementation class PrintInvoice
 */
@WebServlet(description = "generate invoice pdf and print", urlPatterns = { "/print_invoice" })
public class PrintInvoice extends HttpServlet {
	//public StarDocs starDocs = null; 
    public PrintInvoice() {
        /*super();
        //starDocs = new StarDocs(new ConnectionInfo(new URI("https://api.gnostice.com/stardocs/v1"), "be70f87a151741ecb8a637dbb4583e4d", "42bdef08f3634f43a74074a6437e5cb2"));
        try {
        	// Set up connection details
        	StarDocs starDocs = new StarDocs();
        	starDocs.auth.loginApp();
		} catch (StarDocsException e) {
			System.out.println("error from constructor");
			System.out.println(e);
		}*/
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		 response.setContentType("application/pdf");
		Document document = new Document();
		//inserting invoice pojo...
		
		Invoice invoice = new Invoice();
		SimpleDateFormat created_at = new SimpleDateFormat("dd/MM/yyyy");
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
		String[]  pro_nameStirng= request.getParameterValues("pro_name[]");
		invoicedetails.setProduct_id(pidInt);
		invoicedetails.setProduct_price(priceDbl);
		invoicedetails.setQty(qtyInt);
		invoicedetails.setTqty(tqtyInt);
		invoicedetails.setPro_name(pro_nameStirng);
		try {
			
			PdfWriter writer2 = PdfWriter.getInstance(document, new FileOutputStream("/home/jhantu/EclipseWorkspace/adv_java/superInvent/WebContent/documents/invoice.pdf"));
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			
			document.open();
			
			Chunk underline = new Chunk("Welcome to superInvent. ");
			underline.setUnderline(0.2f, -2f); //0.1 thick, -2 y-location
				Paragraph heading = new Paragraph(underline);
				heading.setIndentationLeft(150);
				document.add(heading);
				 document.add( Chunk.NEWLINE );
				 
				 Chunk chunk = new Chunk("Date : " + created_at.format(new Date()));
				 Paragraph date = new Paragraph(chunk);
				 document.add(date);
				 Paragraph Cust_name = new Paragraph("Customer Name : " + request.getParameter("cust_name"));
				 document.add(Cust_name);
				 
				 Paragraph PaymentType = new Paragraph("Payment Type : " + request.getParameter("payment_type"));
				 document.add(PaymentType);
				 
				 document.add( Chunk.NEWLINE );
				 Paragraph InvoiceBill = new Paragraph(new Chunk("Invoice Bill ").setUnderline(0.1f, -2f));
				 InvoiceBill.setIndentationLeft(180);
				 document.add(InvoiceBill);
				 
				 document.add( Chunk.NEWLINE );
				 
				 //first table ...
			        PdfPTable table = new PdfPTable(5);
					table.setWidthPercentage(90);
					table.setSpacingAfter(5f);
					table.setSpacingBefore(5f);
					
					float[] colWidth = new float[] {5f,15f,5f,10f,10f};
					table.setWidths(colWidth);
					PdfPCell cl = new PdfPCell(new Paragraph("#"));
					cl.setIndent(30);
					PdfPCell cl2 = new PdfPCell(new Paragraph("Item"));
					cl2.setIndent(40);
					PdfPCell cl3 = new PdfPCell(new Paragraph("qty"));
					cl3.setIndent(10);
					PdfPCell cl4 = new PdfPCell(new Paragraph("price"));
					cl4.setIndent(30);
					PdfPCell cl5 = new PdfPCell(new Paragraph("tot_price"));
					cl5.setIndent(20);
					table.addCell(cl);
					table.addCell(cl2);
					table.addCell(cl3);
					table.addCell(cl4);
					table.addCell(cl5);
					int j=1;
					for(int i = 0 ; i < invoicedetails.getProduct_id().length ; i++) {
						  chunk = new Chunk(
					              "" + (j++) + ". ");
						 cl = new PdfPCell(new Paragraph(chunk));
						 cl.setIndent(30);
						 cl2 = new PdfPCell(new Paragraph(invoicedetails.getPro_name()[i]));
						 	cl2.setIndent(10);
						 cl3 = new PdfPCell(new Paragraph(new Integer(invoicedetails.getQty()[i]).toString()));
							cl3.setIndent(10);
						 cl4 = new PdfPCell(new Paragraph(new Double(invoicedetails.getProduct_price()[i]).toString()));
							cl4.setIndent(20);
							float tot_price = invoicedetails.getQty()[i] * (float) invoicedetails.getProduct_price()[i];
						 cl5 = new PdfPCell(new Paragraph(new Float(tot_price).toString()));
							cl5.setIndent(20);
							table.addCell(cl);
							table.addCell(cl2);
							table.addCell(cl3);
							table.addCell(cl4);
							table.addCell(cl5);
					}
					
			        document.add(table);
			        //end of first table ....
			        document.add( Chunk.NEWLINE );
			        
			        //second table...
			        PdfPTable table2 = new PdfPTable(6);
					table2.setWidthPercentage(90);
					table2.setSpacingAfter(5f);
					table2.setSpacingBefore(5f);
					
					table2.setWidths(new float[] {5f,8f,5f,5f,5f,5f});
					PdfPCell ncl = new PdfPCell(new Paragraph("Sub Total"));
					ncl.setIndent(5);
					PdfPCell ncl2 = new PdfPCell(new Paragraph("GST(18%)"));
					ncl2.setIndent(5);
					PdfPCell ncl3 = new PdfPCell(new Paragraph("Discount"));
					ncl3.setIndent(5);
					PdfPCell ncl4 = new PdfPCell(new Paragraph("Net Total"));
					ncl4.setIndent(5);
					PdfPCell ncl5 = new PdfPCell(new Paragraph("Paid"));
					ncl5.setIndent(25);
					PdfPCell ncl6 = new PdfPCell(new Paragraph("Due"));
					ncl6.setIndent(25);
					
					table2.addCell(ncl);
					table2.addCell(ncl2);
					table2.addCell(ncl3);
					table2.addCell(ncl4);
					table2.addCell(ncl5);
					table2.addCell(ncl6);

					//second row...
					 ncl = new PdfPCell(new Paragraph(Double.toString(invoice.getSub_tot())));
					ncl.setIndent(15);
					 ncl2 = new PdfPCell(new Paragraph(Double.toString(Math.ceil(invoice.getGst()))));
					ncl2.setIndent(15);
					 ncl3 = new PdfPCell(new Paragraph(Double.toString(invoice.getDsicount())));
					ncl3.setIndent(15);
					 ncl4 = new PdfPCell(new Paragraph(Double.toString(invoice.getNet_tot())));
					ncl4.setIndent(15);
					 ncl5 = new PdfPCell(new Paragraph(Double.toString(invoice.getPaid_amt())));
					ncl5.setIndent(15);
					 ncl6 = new PdfPCell(new Paragraph(Double.toString(invoice.getDue())));
					ncl6.setIndent(15);
					
					table2.addCell(ncl);
					table2.addCell(ncl2);
					table2.addCell(ncl3);
					table2.addCell(ncl4);
					table2.addCell(ncl5);
					table2.addCell(ncl6);
					document.add(table2);
					//end of second table...
					
				
					//signature..
					 Paragraph sign = new Paragraph("Jhantu Nandi");
					 sign.setAlignment(Element.ALIGN_RIGHT);
				//date	
					  chunk = new Chunk(" " + created_at.format(new Date()));
					 Paragraph date2 = new Paragraph(chunk);
					 date2.setAlignment(Element.ALIGN_RIGHT);
				
					 
					 
					 document.add(sign);
					 document.add(date2);
			        document.close();
			        writer.close();
				
				//String fileToView  = "/home/jhantu/EclipseWorkspace/adv_java/superInvent/WebContent/documents/invoice.pdf";
				//ViewResponse file = starDocs.viewer.createView(new FileObject(fileToView));
				//response.sendRedirect(file.getUrl());
				
		} catch (DocumentException e) {
			System.out.println("Error from PrintInvoice servlet");
			System.out.println(e);
		}catch (FileNotFoundException f) {
			System.out.println("\"Error from PrintInvoice servlet");
			System.out.println(f);
		}catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("\"Error from PrintInvoice servlet I'm in line #" + 
				    e.getStackTrace()[0].getLineNumber());
			System.out.println(e);
			
		}
		
	}

}
