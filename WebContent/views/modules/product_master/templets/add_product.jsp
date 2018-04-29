
<div id="addProduct" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add Product</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-body">
						<form>
							<div class="form-row">
								<div class="form-group col-md-3">
									<label>Date:</label> 
									<input type="text" class="form-control" id="p_date" value = "<% %>" readonly="readonly" />
								</div>
								<div class="form-group col-md-5">
									<label for="inputPassword4">Product Name:</label> 
									<input type="text" class="form-control" id="p_name" placeholder="Product name" required="required" />
								</div>
								<div class="form-group col-md-4">
									<label>Expiry date:</label> 
										<input type="date" id = "p_exp" name = "p_exp" class="form-control" placeholder="enter expiry date" />
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label>Category:</label> 
										<select id = "p_category" class="form-control" name = "p_category" required="required">
											<option value = "-1">--Select categroy</option>
										</select>
								</div>
								<div class="form-group col-md-4">
									<label>Brand:</label> 
										<select id = "p_brand" class="form-control" name = "p_brand" required="required">
											<option value = "-1">--Select brand</option>
										</select>
								</div>
								<div class="form-group col-md-4">
									<label>Batch no. :</label> 
										<input type="text" id = "p_batch" name = "p_batch" class="form-control" placeholder="Enter batch number" />
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label>Price:</label> 
										<input type="number" class="form-control" id="p_price" name = "p_price" placeholder="In Rupees" required="required" />
								</div>
								<div class="form-group col-md-4">
									<label>Quantity:</label> 
										<input type="number" class="form-control" id="p_quantity" name = "p_quantity" placeholder="In Number" required="required" />
								</div>
								<div class="form-group col-md-4">
									<label>Weight:</label> 
										<input type="number" id = "p_weight" name = "p_weight" class="form-control" placeholder="In Kg" />
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
							<button type="submit" class="btn btn-success"><i class = "fa fa-plus" onclick = "add_product(event);"></i>&nbsp;Add</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>