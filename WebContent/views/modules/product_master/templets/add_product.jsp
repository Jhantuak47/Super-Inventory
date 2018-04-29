<button type="submit" class="btn btn-success d-none" id = "add_product_button" data-toggle="modal" data-target="#addProduct"></button>
<%@page import="java.util.Date"%>
<div id="addProduct" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add Product</h5>
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
						<form id = "add_product_form">
							<div class="form-row">
								<div class="form-group col-md-3">
									<label>Date:</label> 
									<input type="text" class="form-control" id="p_date" value = "<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new Date()) %>" readonly="readonly" />
								</div>
								<div class="form-group col-md-5">
									<label for="inputPassword4">Product Name:</label> <span style="color: red;">*</span>
									<input type="text" class="form-control" id="p_name" name = "p_name" placeholder="Product name" required="required" />
								</div>
								<div class="form-group col-md-4">
									<label>Expiry date:</label> 
										<input type="date" id = "p_exp" name = "p_exp" class="form-control" placeholder="enter expiry date" />
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label>Category:</label> <span style="color: red;">*</span>
										<select id = "p_category" class="form-control" name = "p_category" required="required">
											<option value = "-1">--Select brand</option>
										</select>
								</div>
								<div class="form-group col-md-4">
									<label>Brand:</label> <span style="color: red;">*</span>
										<select id = "p_brand" class="form-control" name = "p_brand" required="required">
											<option value = "-1">--Select brand</option>
										</select>
								</div>
								<div class="form-group col-md-4">
									<label>Batch no. :</label> <span style="color: red;">*</span>
										<input type="text" id = "p_batch" name = "p_batch" class="form-control" placeholder="Enter batch number" required="required"/>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label>Price:</label> <span style="color: red;">*</span>
										<input type="number" class="form-control" id="p_price" name = "p_price" placeholder="In Rupees" required="required" />
								</div>
								<div class="form-group col-md-4">
									<label>Quantity:</label> <span style="color: red;">*</span>
										<input type="number" class="form-control" id="p_quantity" name = "p_quantity" placeholder="In Number" required="required" />
								</div>
								<div class="form-group col-md-4">
									<label>Weight:</label> <span style="color: red;">*</span>
										<input type="number" id = "p_weight" name = "p_weight" class="form-control" placeholder="In Kg" required="required"/>
								</div>
							</div> 
							 <div class="form-group">
							    <label>Description:</label>
							    <input type="text" class="form-control" id="p_desc" name = "p_desc" placeholder="Product description, not more than 25 words" />
							  </div>
							 <div class="form-group">
									<label>Type:</label> 
									<input type="text" class="form-control" id="p_type" name = "p_type" placeholder="Enter Type" />
							</div><hr>
							<button type="submit" class="btn btn-success" onclick = "add_product(event);"><i class = "fa fa-plus"></i>&nbsp;Add</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>