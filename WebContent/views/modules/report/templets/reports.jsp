	<%@page import="java.sql.ResultSet"%>
<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
<div class="row">
	<% SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yy");
		ResultSet rs = (ResultSet)request.getAttribute("result");
		String table = (String)request.getAttribute("table");
		List<Integer> x = new ArrayList<Integer>();
	%>
	<div class="col-md-10 mx-auto">
		<div class="card" style = "box-shadow:0 0 15px 0 lightgrey;">
			<div class="card-header">
				<h5>Reports</h5>
			</div>
			<div class="card-body primary">
				<% if(rs != null){ %>
					<% if(table.equals("purchase")){ %>
					<%
						int i = 0;
								rs.next();
								while (!rs.isAfterLast() && !x.contains(rs.getInt(1))) {
									x.add(rs.getInt(1));
					%>
				<table class="table">
								<thead class = "thead-dark">
									<tr>
										<th>vendor</th>
										<th>Date</th>
										<th>amount</th>
										<th>bill_no</th>
									</tr>
								</thead>
								<tbody>
									
									<tr>
										<td><%=(rs.getString(2).equals("")) ? "<i>Nill</i>" : rs.getString(2)%></td>
										<td><%=formatter.format(rs.getTimestamp(3))%></td>
										<td><%=rs.getDouble(4)%></td>
										<td><%=rs.getInt(5)%></td>
									</tr>
								</tbody>
							</table>
										<table class="table table-bordered table-sm sub_table">
											<thead class="table-warning">
												<tr>
													<th style="text-align: center;">#</th>
													<th>Item</th>
													<th>Cost Price</th>
													<th>Price</th>
													<th>Quantity</th>
													<th>exp_date</th>
												</tr>
											</thead>
											<tbody>
												<%
													int j = 0;
																do {
												%>
												<tr class="table-info">
													<td style="text-align: center;"><%=++j%></td>
													<td><%=rs.getString(6)%></td>
													<td><%=rs.getDouble(7)%></td>
													<td><%=rs.getDouble(8)%></td>
													<td><%=rs.getInt(9)%></td>
													<td><%= rs.getString(10) %></td>
												</tr>
												<%
													} while (rs.next() && x.contains(rs.getInt(1)));
												%>
											</tbody>
										</table>
									<%}%>

				<%}else{ %>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>order date</th>
							<th>net tot</th>
							<th>payed amt</th>
							<th>due</th>
							<th>Payment mode</th>
						</tr>
					</thead>					
					<tbody>
					<% int i = 0;rs.next();while(!rs.isAfterLast() && !x.contains(rs.getInt(1))){
								x.add(rs.getInt(1));
							%>
								 <tr>
									<td><%=++i %></td><td><%= (rs.getString(2).equals("")) ? "<i>Nill</i>" : rs.getString(2)  %></td><td><%= formatter.format(rs.getTimestamp(3)) %></td><td><%=rs.getDouble(4) %></td><td><%=rs.getDouble(5) %></td>
									<td><%=rs.getInt(6) %></td><td><%=rs.getString(7) %></td>
								  </tr>
								  <tr>
									<table class="table">
										<thead>
											<tr>
												<th style = "text-align: center;">#</th>
												<th class="w-25">Item</th>
												<th class="w-25">Price</th>
												<th class="w-25">Quantity</th>
											</tr>
										</thead>
										<tbody>
										<% int j= 0;do{%>
										<tr><td style = "text-align: center;"><%= ++j %></td><td><%=rs.getString(8) %></td><td><%=rs.getDouble(9) %></td><td><%=rs.getInt(10) %></td></tr>
										<%
										}while(rs.next() && x.contains(rs.getInt(1))); %>
										</tbody>
									</table>
								</tr>
						<%}%>
						</tbody>
				</table>
					<%}}else{ %>
	
					<p style="text-align: center;">
						<span style="color: red;">Sorry! no result found. Please
							try with some different inputs.</span>
					</p>
					
					<%}%>
					
	
			</div>
			<!-- primary cards body end -->
		</div>
		<!-- primary cards primary end -->
	</div>
</div>
  <script type="text/javascript" src="../js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>