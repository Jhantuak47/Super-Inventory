<button type="submit" class="btn btn-success d-none" id = "add_category_button" data-toggle="modal" data-target="#addCategory"></button>
<%@page import="java.util.Date"%>
<div id="addCategory" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add Category</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" id = "add_cat_close_btn">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-body">
						<!--error alerts  -->
							<div class="alert alert-danger alert-dismissible" id="catErr" style = "display:none;">
							 	<button type="button" class="close" data-dismiss="alert">&times;</button>
							    <strong>Error !</strong>&nbsp;<span id = "cat_errMsg"></span>
							 </div>
						<!--end error alerts  -->
						<form class="form-horizontal" method="POST" action= "" id = "add_cat_form">
	
	                        <div class="form-group">
	             <span style="color: red;">*</span><label  class="col-md-4 control-label">Category Name :</label>
	
	                            <div class="col-md-8">
	                                <input list="Category" id="cat_name" class="form-control" name="cat_name" required />
									<datalist id="Category">
										<option value="Softwares">
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
							<br>
	                        <div class="form-group">
	             <span style="color: red;">*</span><label for="parent_cat" class="col-md-4 control-label">Category Type :</label>
	                            <div class="col-md-8">
	                            	<select class="custom-select" id = "parent_cat" name ="parent_cat" required="required" >
									  <option selected value = "">--select category</option>
									  <option value="0">root</option>
									</select>
	                                    <span class="help-block">
	                                        <strong id = "login_pass_error"></strong>
	                                    </span>
	                            </div>
	                        </div>
							<br>
	                        <div class="form-group">
	                        	<label for="status" class="col-md-4 control-label">Category Status :</label>
	                        		<input type="checkbox" name="status"> &nbsp;Is Active ..?
	                        </div>
							<hr>
	                        <div class="form-group">
	                            <div class="col-md-8 col-md-offset-4">
	                                <button type="submit" class="btn btn-success" onclick="add_category(event);">
	                                   <i class="fa fa-plus" aria-hidden="true"></i>&nbsp; Add
	                                </button>
	                            </div>
	                        </div>
	                    </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>