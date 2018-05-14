<%
	if (session.getAttribute("name") == null)
		response.sendRedirect("./");
%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>" />
	<div class="row">
	<% SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yy, hh:mm"); %>
	<div class="col-md-10 mx-auto">
		<div class="card" style = "box-shadow:0 0 25px 0 lightgrey;">
			<div class="card-header"><h5>Reports</h5></div>
			<div class="card-body">
				<form id = "report_form_data" onsubmit = "return false">
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Select Transition Mode* :</label>
						<div class = "col-sm-6">
							<select class = "form-control form-control-sm" name = "mode_type" id = "mode_type" required="required">
								<option value = "1">Sales</option>
								<option value = "2">Purchase</option>
							</select>
						</div>
					</div>
										<br><br>
					<div class = "card secondary" style = "box-shadow:0 0 15px 0 lightgrey;">
						<div class = "card-body">
							<h3 id = "heading">Provide Seller Info</h3>
							<table align="center" style="width: 500px">
								 <thead>
									<tr>
										<th>#</th>
										<th style="text-align: center;">Parameter</th>
										<th style="text-align: center;">Inputs</th>
									</tr>
								</thead>
								<tbody id="report_generate">
									<tr><td>1</td><td style="text-align: center;">Seller Name :</td><td><input type = "text" class = "form-control form-control-sm" name = "name" placeholder="Select Name" required="required"></td></tr>
									<tr><td>2</td><td style="text-align: center;">Selling Date :</td> <td><input type = "date" class = "form-control form-control-sm" name = "date" required="required"></td> </tr>
									<tr><td>3</td><td style="text-align: center;">Due :</td> <td> </td> </tr>
								</tbody>
							</table> 
							
						</div><!-- order list card body ends -->
					</div><!--order list card ends -->
					<br>
					<hr>
					<div align="center">
						<input type="submit" id = "report_form_btn" style = "width:150px;" class = "btn btn-success" value = "Generate Reports">
						<input type="submit" id = "Print_invoice" style = "width:150px;" class = "btn btn-success d-none" value = "Print Invoice">
					</div>
				</form>
			</div>
				<!--order list card ends -->
			</div><!--  end of card body -->
		</div><!-- end of card -->
	</div> <!-- end of col-md-10 -->
</div><!-- end of row -->
	

<%--   <jsp:include page="<%=\"templets/edit.jsp\"%>"/>
  <jsp:include page="<%=\"templets/add_cat.jsp\"%>"/>
  <jsp:include page="<%=\"templets/delete.jsp\"%>"/> --%>
<script type="text/javascript" src="js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>" />