
<div id="addBrand" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
	        <h5 class="modal-title">Add New Brand</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id = "close_addBrand">
	          <span aria-hidden="true">&times;</span>
	        </button>
      	</div>
	        <div class="modal-body">
	          <div class="panel panel-default">

                <div class="panel-body">
                    <form class="form-horizontal" method="POST" id = "brand_form">

                        <div class="form-group">
                            <label for="b_name" class="col-md-4 control-label">Brand Name:</label>

                            <div class="col-md-8">
                                <input id="b_name" type="text" class="form-control" name="b_name" required autofocus>
                                    <span id  = "b_error"></span>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-primary btn-sm" onclick = "add_brand(event);">
                                    <i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Add
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
              <div class="modal-footer">
       			 	<!-- <button type="button" class="btn btn-danger btn-sm float-left" data-dismiss="modal">Close</button> -->
      			</div>
	        </div>
		</div>
	</div>
</div>