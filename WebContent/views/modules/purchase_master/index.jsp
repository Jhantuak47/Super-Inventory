<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
<link rel = "stylesheet" href = "css/index.css" >
<div class="row">
	<% SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yy"); %>
	<div class="col-xs-12 pull-right">
		<div class="card parent-card" style = "box-shadow:0 0 25px 0 lightgrey;">
			<div class="card-header"><h5>New Purchase Details</h5></div>
			<div class="card-body">
				<form id = "purchase_form_data">
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Purchase Date :</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "pur_date" id = "pur_date" readonly value = "<%= formatter.format(new Date()) %>" >
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Vendor Name* :</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "ven_name" id = "ven_name" placeholder="Enter vendor name" required/>
						</div>
					</div>
					<div class = "card child-card" style = "box-shadow:0 0 15px 0 lightgrey;">
						<div class = "card-body">
							<h3>Make Products list</h3>
							<table align="center" style="width: 100%">
								<thead id = "purchase_item">
									<tr>
										<th>Item Name</th>
										<th style="text-align: center;">Brand</th>
										<th style="text-align: center;">Category</th>
										<th style="text-align: center;">Quantity</th>
										<th style="text-align: center;">Cost Price</th>
										<th style="text-align: center;">Price</th>
										<th style="text-align: center;">Type</th>
										<th style="text-align: center;">Batch_no</th>
										<th style="text-align: center;">Exp_date</th>
									</tr>
								</thead>
								<tbody id="purchase_item">
								
								</tbody>
							</table>
							<a href = "" id = "addRow" class = "pull-right" style = "padding:10px;"><i class = "fa fa-plus"></i>&nbsp;add more items</a>   <a href="" id = "remove" class = "pull-right" style = "padding:10px" ><i class = "fa fa-trash"></i>&nbsp;remove item</a>
						</div><!-- order list card body ends -->
					</div><!--order list card ends -->
					<p></p>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Total Purchased Price :</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "sub_total" id = "sub_total" readonly="readonly" />
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Bill Number *:</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "bill_no" id = "bill_no" required/>
						</div>
					</div>
					<div align="center">
						<input type="submit" id = "purchase_form_btn" style = "width:150px;" class = "btn btn-info" value = "Save">
						<input type="submit" id = "Print_invoice" style = "width:150px;" class = "btn btn-success d-none" value = "Print Invoice">
					</div>
				</form>
			</div><!--  end of card body -->
		</div><!-- end of card -->
	</div> <!-- end of col-md-10 -->
</div><!-- end of row -->

  <script type="text/javascript" src="./js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>