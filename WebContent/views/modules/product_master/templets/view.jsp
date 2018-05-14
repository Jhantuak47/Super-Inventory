<button type="submit" class="btn btn-success d-none" id = "view_product_button" data-toggle="modal" data-target="#viewModel"></button>
<%@page import="java.util.Date"%>
<div id="viewModel" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Product Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" id = "add_product_close_btn">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-body">
						<!--error alerts  -->
							<div class="alert alert-danger alert-dismissible" id="p_err" style = "display:none;">
							 	<button type="button" class="close" data-dismiss="alert">&times;</button>
							    <strong>Error !</strong>&nbsp;<span id = "p_errMsg"></span>
							 </div>
						<!--end error alerts  -->
						<form id = "edit_product_form">
							<div class="form-row">
								<div class="form-group col-md-3">
									<label>Created on:</label> 
									<input type="text" class="form-control" id="view_date" value = "<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date()) %>" readonly="readonly" />
								</div>
								<div class="form-group col-md-5">
									<label for="inputPassword4">Product Name:</label> <span style="color: red;">*</span>
									<input type="text" class="form-control" id="view_name" readonly="readonly"/>
								</div>
								<div class="form-group col-md-4">
									<label>Expiry date:</label> 
										<input type="date" id = "view_exp_dt" class="form-control" readonly="readonly"/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Batch no. :</label> <span style="color: red;">*</span>
										<input type="text" id = "view_batch_no" class="form-control" readonly="readonly"/>
								</div>
								<div class="form-group col-md-6">
									<label>Price:</label> <span style="color: red;">*</span>
										<input type="number" class="form-control" id="viewPrice" readonly="readonly" />
								</div>
							</div>
							<div class="form-row">
								
								<div class="form-group col-md-6">
									<label>stock:</label> <span style="color: red;">*</span>
										<input type="number" class="form-control" id="view_stock" readonly="readonly"/>
								</div>
								<div class="form-group col-md-6">
									<label>Weight:</label> <span style="color: red;">*</span>
										<input type="number" id = "view_weight" class="form-control" readonly="readonly"/>
								</div>
							</div> 
							 <div class="form-group">
							    <label>Description:</label>
							    <input type="text" class="form-control" id="view_desc" readonly="readonly" />
							  </div>
							 <div class="form-group">
									<label>Type:</label> 
									<input type="text" class="form-control" id="view_type" readonly="readonly"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>