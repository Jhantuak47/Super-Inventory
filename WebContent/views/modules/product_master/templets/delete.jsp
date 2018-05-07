  <!-- Delete Modal -->	
<button type="button" id = "delete_modal_btn" class="btn btn-primary d-none" data-toggle="modal" data-target="#deleteModal">
</button>
<div id="deleteModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Delete Category</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" id = "close_delet">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
	        <div class="modal-body">
	        	<form>
	        	<i>Are you sure you want to</i> <strong> Delete ?</strong>
	        	<h5><span style = "color:red;" id = "hidden_parent"></span>..!</h5> 
	        	<input type="hidden" id = "hidden_id"/>
	        	<input type="hidden" id = "hidden_page"/>
	        	<hr>
	        	<button type="button" onclick = "deletebrand();" class="btn btn-danger btn-sm float-left">Delete</button>
	        	</form>
	        </div>
		</div>
	</div>
</div>
  <!-- End Delete modal  -->