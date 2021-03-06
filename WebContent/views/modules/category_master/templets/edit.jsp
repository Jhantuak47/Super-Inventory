  <!-- Edit Modal -->	
<button type="button" id = "editCategory" class="btn btn-primary d-none" data-toggle="modal" data-target="#editCategoryModal">
</button>
<div id="editCategoryModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
	        <div class="modal-body">
	        	<div class="card">
					 <h5 class="card-header">Edit Category</h5>
	                <div class="card-body">
	                    <form class="form-horizontal" method="POST" action="" id = "edit_category_form">
	
	                        <div class="form-group">
	                            <label for="email" class="col-md-4 control-label">Category Name :</label>
	
	                            <div class="col-md-6">
	                                <input list="Category" id="edit_name" class="form-control" name="edit_name" required autofocus>
									<datalist id="Category">
										<option value="Software ">
										<option value="Clothing">
										<option value="Electronics">
										<option value="Medicine">
										<option value="Game">
										<option value="Electronics">
										<option value="Medicine">
										<option value="Game">
									</datalist>
								</div>
	                        </div>
							<input type="hidden" id = "cat_id" name = "cat_edit_id">
							<input type="hidden" id = "page_no" name = "page_no" />
	                        <div class="form-group">
	                            <label class="col-md-4 control-label">Category Type :</label>
	
	                            <div class="col-md-6">
	                            	<select class="custom-select" id = "edit_type" name = "edit_type">
									  <option selected>--select type</option>
									  <option value="0">root</option>
									</select>
	                                    <span class="help-block">
	                                        <strong id = "login_pass_error"></strong>
	                                    </span>
	                            </div>
	                        </div>
	
	                        <div class="form-group">
	                        	<label class="col-md-3 control-label">Category Status :</label>
	                        		<input id = "is_active" type="checkbox" name="status"> &nbsp;Is Active ..?
	                        </div>
	
	                        <div class="form-group">
	                            <div class="col-md-8 col-md-offset-4">
	                                <button type="submit" class="btn btn-success" onclick = "editCategory(event);">
	                                    <i class="fa fa-edit" aria-hidden="true"></i>&nbsp;Update
	                                </button>
	                                
	                            </div>
	                        </div>
	                    </form>
	                </div><!--end of card body  -->
           		 </div><!--end of card  -->
	        </div>
	        <div class="modal-footer">
       			 	<button type="button" id = "edit_close_modal" class="btn btn-danger float-left" data-dismiss="modal">Close</button>
      		</div>
		</div>
	</div>
</div>
  <!-- End edit modal  -->