
//update category status..
function Update_category_status(id, page_no, status){
		console.log("page_no = "+page_no);
		var message = "Are you sure you want to Activate ?";
		if(status == 1){
			status =0;
			message = "Are you sure you want to Deactiveate ?";
		}else
			status = 1;
		if(confirm(message)){
			$.ajax({
			     type: "POST",
			     url: './update_cat_status',
			     data: {id : id, status : status},
			     beforeSend:   function(){$('.loadingDiv').show();},
			     success: function(data) {
			    	 
			    	 console.log(data);
			    	 
			         if(data == "UPDATED"){
			        	 //alert('category updated successfully !');
			        	 $('#successMessage').text('successfully status updated !');
			        	 $('#success_alert').show();
			        	 $('#error_alert').hide();
			        	 ListCategroyWithAjax(page_no);
			         }else{
			        	 $("#errorMessage").text("fail to update status , please try after some time !");
			        	 $('#success_alert').hide();
			        	 $('#error_alert').show();
			         }
			        
			         $('.loadingDiv').hide();
			     }
			});
		}
	}
	
// edit modal
function edit(id, name, parent_id, page_no, is_check){
	$('#edit_name').val(name);
	$('#edit_type').val(parent_id);
	$('#cat_id').val(id);
	$('#page_no').val(page_no);
	
	if(is_check == 1){
		$('#is_active').prop('checked', true);
	}else
		$('#is_active').prop('checked', false);
	
	localStorage.setItem("edit_name", name);
	localStorage.setItem("edit_type", parent_id);
	localStorage.setItem("is_check", is_check);
	$('#editCategory').click();
}

//update category..
function editCategory(e){
	e.preventDefault();
	var categoryName = $("#edit_name").val();
	var catType = $("#edit_type").val();
	var id = $("#cat_id").val();
	var page_no = $('#page_no').val();
	var catStatus = ($('#is_active').prop('checked') == true) ? 1 : 0;
	if(categoryName != localStorage.getItem("edit_name") || catType != localStorage.getItem("edit_type") || catStatus != localStorage.getItem("is_check")){
			
			var formData = $("#edit_category_form").serialize();
			console.log(formData);
			$.ajax({
			     type: "POST",
			     url: './update_cat',
			     data: formData,
			     beforeSend:   function(){$('.loadingDiv').show();},
			     success: function(data) {
			    	 
			    	 console.log(data);
			    	 
			         if(data == "UPDATED"){
			        	 //alert('category updated successfully !');
			        	 console.log('from success alert');
			        	 $('#success_alert').hide();
			        	 $('#successMessage').text('successfully category updated !');
			        	 $('#success_alert').show();
			        	 $('#error_alert').hide();
			        	 fetchAllCategory();
			        	 ListCategroyWithAjax(page_no);
			         }else{
			        	 $("#errorMessage").text("fail to update category , please try after some time !");
			        	 $('#success_alert').hide();
			        	 $('#error_alert').show();
			         }
			        
			         $('.loadingDiv').hide();
			     }
			});
			$("#edit_close_modal").click();
	}
}

//add new category..
	//add maodal..
		function add(){
			console.log('from here');
			$("#add_category_button").click();
			$("#cat_name").val("");
			$('#parent_cat').val("");
		}
function add_category(e){
	e.preventDefault();
	cat_name = $("#cat_name");
	cat_type = $('#parent_cat');
	if(cat_name.val() == "" && cat_type.val() == ""){
		 $('#cat_errMsg').text("(*) mandatory field");
    	 $('#success_alert').hide();
         $('#catErr').show();
	}else{
		$.ajax({
		     type: "POST",
		     url: './add_category',
		     data: $("#add_cat_form").serialize(),
		     beforeSend:   function(){$('.loadingDiv').show();},
		     success: function(data) {
		    	 console.log(data);
		         if(data === "success"){
		        	 $('#successMessage').text('successfully category added');
		        	 $("#cat_name").val("");
		        	 $('#parent_cat' ).val(" ");
		        	 $('#error_alert').hide();
		        	$('#success_alert').show();
		        	$('#add_cat_close_btn').click();
		        	ListCategroyWithAjax(1);
		         }else if(data === "category allready exist"){
		        	 $('#errorMessage').text(data);
		        	 $('#success_alert').hide();
			        	$('#error_alert').show();
		         }else{
		        	 window.location.href = './error.jsp';
		         }
		         $('.loadingDiv').hide();
		     }
		});
	}
}

//fetching all category..
fetchAllCategory();
function fetchAllCategory(){
	 $.ajax({
	     type: "POST",
	     url: './list_category',
	     success: function(data) {
	    	 choose = '<option value = "">--select category</option>';
	    	 root = '<option value = "0">root</option>'
	    	 $('#p_category').html(choose + data);
	    	 $('#edit_type').html(choose+ root + data);
	    	 $('#parent_cat').html(choose+ root + data);
	     }
	});
}

	//delete category modal..
	function delete_modal(id, category, page_no){
		$("#hidden_id").val(id);
		$("#hidden_parent").html(category);
		$("#hidden_page").val(page_no);
		$("#delete_modal_btn").click();
		 $('#success_alert').hide();
		 $('#error_alert').hide();
	}
	//delete category..
	function deleteCategory(){
		var id = $("#hidden_id").val();
		var cat =  $("#hidden_parent").text();
		var current_page = $("#hidden_page").val();
		console.log(cat);
		$.ajax({
		     type: "POST",
		     url: './delete_cat',
		     data: {id : id},
		     beforeSend:   function(){$('.loadingDiv').show();},
		     success: function(data) {
		    	 console.log(data);
		         if(data === "success"){
		        	 $('#successMessage').html('successfully category "<i><strong>'+ cat + '</strong></i>" is deleted !');
		        	 $('#error_alert').hide();
		        	$('#success_alert').show();
		        	ListCategroyWithAjax(current_page);
		         }else if(data === "dependend"){
		        	 $('#errorMessage').text(cat + " is dependend, fail to delete !");
		        	 $('#success_alert').hide();
			        	$('#error_alert').show();
		         }else if(data === "hasProduct"){
		        	 $('#errorMessage').text(cat + " having products, fail to delete !");
		        	 $('#success_alert').hide();
			         $('#error_alert').show();
		         }else{
		        	// window.location.href = './error.jsp';
		        	 console.log('some error');
		         }
		         $('.loadingDiv').hide();
		         $("#close_delet").click();
		     }
		});
	}
	
	//listing using pagination and ajax..
	function ListCategroyWithAjax(current_page){
		$.ajax({
		     type: "GET",
		     url: './list_cat_ajax',
		     data: {page_no : current_page},
		     headers :{
		    	 Accept : " application/json; charset=utf-8",
		    	 "Content-Type" : "application/json; charset=utf-8"
		     },
		     beforeSend:   function(){$('.loadingDiv').show();},
		     success: function(data) {
		    	 if(data == "empty table"){
			    	 
			    	 $("#footer").html("");
			    	 $("#cat_table").html("<h5> No records to display </h5>");
			        
		     	}else if(data == "fail"){
		     		 $('#errorMessage').text("fail to delete category plese tye again !");
		        	 $('#success_alert').hide();
			         $('#error_alert').show();
		     	}else{
		     		var result = $.parseJSON(data);;
			    	 $("#footer").html(result.paginations);
			    	 $("#cat_table").html(result.tbody);
		     	}
		    	 $('.loadingDiv').hide();
		         $("#close_delet").click();
		     }
		});
	}
	
	//activate pagination link through ajax..
	$("body").delegate(".page-link", "click", function(){
		$('#success_alert').hide();
		$('#error_alert').hide();
		var pn = $(this).attr("pn");
		console.log(pn);
		ListCategroyWithAjax(pn);
	})