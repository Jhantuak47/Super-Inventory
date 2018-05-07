 <% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
<div class="row">

	<div class="col-md-10 mx-auto">
		<div class="card" style = "box-shadow:0 0 25px 0 lightgrey;">
			<div class="card-header"><h5>New Orders</h5></div>
			<div class="card-body">
				<form>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Order Date</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control-sm" name = "" id = "" readonly value = "37485">
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Customer Name*</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control" name = "" id = "" placeholder="Enter customer name" required="required"/>
						</div>
					</div>
					<div class = "card" style = "box-shadow:0 0 15px 0 lightgrey;">
						<div class = "card-body">
							<h3>Make an order list</h3>
							<table align="center" style="width: 800px">
								<thead>
									<tr>
										<th>#</th>
										<th style="text-align: center;">Item Name</th>
										<th style="text-align: center;">Total Quantity</th>
										<th style="text-align: center;">Quantity</th>
										<th style="text-align: center;">Price</th>
										<th>Total</th>
									</tr>
								</thead>
								<tbody id="invoice_item">
								
								</tbody>
							</table>
							<a href = "" id = "add" class = "pull-right" style = "padding:10px;"><i class = "fa fa-plus"></i>&nbsp;add more items</a>   <a href="" id = "remove" class = "pull-right" style = "padding:10px" ><i class = "fa fa-trash"></i>&nbsp;remove item</a>
						</div><!-- order list card body ends -->
					</div><!--order list card ends -->
					<p></p>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Sub Total</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "sub_total" id = "sub_total" required="required"/>
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">GST(18%)</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "gst" id = "gst" required="required"/>
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Discount</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "discount" id = "discount" required="required"/>
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Net Total</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "net_tot" id = "net_tot" required="required"/>
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Paid</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "paid" id = "paid" required="required"/>
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Due</label>
						<div class = "col-sm-6">
							<input type = "text" class = "form-control form-control-sm" name = "due" id = "due" required="required"/>
						</div>
					</div>
					<div class = "form-group row">
						<label class = "col-sm-3" align = "right">Payment Method</label>
						<div class = "col-sm-6">
							<select class = "form-control form-control-sm" name = "payment_type" id = "payment_type" required="required">
								<option>Cash</option>
								<option>Card</option>
								<option>Cheque</option>
								<option>Draft</option>
							</select>
						</div>
					</div>
					<div align="center">
						<input type="submit" id = "order_form_btn" style = "width:150px;" class = "btn btn-info" value = "Order">
						<input type="submit" id = "Print_invoice" style = "width:150px;" class = "btn btn-success d-none" value = "Print Invoice">
					</div>
				</form>
			</div><!--  end of card body -->
		</div><!-- end of card -->
	</div> <!-- end of col-md-10 -->
</div><!-- end of row -->


<%-- 	<jsp:include page="<%=\"templets/edit.jsp\"%>"/>
  <jsp:include page="<%=\"templets/add_product.jsp\"%>"/>
  <jsp:include page="<%=\"templets/delete.jsp\"%>"/> --%>
  <script type="text/javascript" src="./js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>