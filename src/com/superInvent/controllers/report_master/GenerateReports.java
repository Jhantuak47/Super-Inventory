package com.superInvent.controllers.report_master;


import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.superInvent.DAO.SearchDAO;
import com.superInvent.POJO.ReportsPojo;
import com.superInvent.Services.reports.ReportsService;

/**
 * Servlet implementation class GenerateReports
 */
@WebServlet(description = "will generate all reports", urlPatterns = { "/reports" })
public class GenerateReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
				String param[] = null;
				String name = request.getParameter("name");
				String Fromdate = request.getParameter("dateFrom");
				String Todate = request.getParameter("dateTo");
				String table = request.getParameter("table");
				String p_name = request.getParameter("p_name");
					if(name.equals("") && Fromdate.equals("")) {
						//pruduct is full
						param = new String[2];
						param[0] = "product";
						param[1] = p_name;
					}else if(name.equals("") && p_name.equals("")) {
						//date is full
						param = new String[2];
						param[0] = Fromdate;
						param[1] = Todate;
					}else if( Fromdate.equals("") && p_name.equals("")) {
						//name is full..
					    param = new String[2];
						param[0] = "name";
						param[1] = name;
					}else if(p_name.equals("")) {
						//date and name is pull
						 param = new String[4];
						 param[0] = "DATE_NAME";
						 param[1] = name;
						 param[2] = Fromdate;
						 param[3] = Todate;
						
					}else if(name.equals("")) {
						//date and product is full...
						 param = new String[4];
						 param[0] = "DATE_PRODUCT";
						 param[1] = p_name;
						 param[2] = Fromdate;
						 param[3] = Todate;
					}else if(Fromdate.equals("")){
						//name and p name is full
						param = new String[4];
						param[0] = "NAME_PRODUCT";
						param[1] = name;
						param[2] = p_name;
					}else {
						//all parameters are present..
						param = new String[5];
						param[0] = "NAME_PRODUCT_DATE";
						param[1] = name;
						param[4] = p_name;
						param[2] = Fromdate;
						param[3] = Todate;
						
					}
					request.setAttribute("result", new SearchDAO().reports(table, param));
					RequestDispatcher rd = request.getRequestDispatcher("/views/modules/report/templets/reports.jsp");
					rd.forward(request, response);
					//new ReportsService().reports(new SearchDAO().reports(table, param));
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("error from GenerateReports servlet");
		}
	}

}
