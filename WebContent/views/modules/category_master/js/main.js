function edit(id, name, type, is_check){
	$('#edit_name').val(name);
	$('#edit_type').val(type);
	$('#cat_id').val(id);
	console.log(is_check);
	if(is_check){
		$('#is_active').prop('checked', true);
	}else
		$('#is_active').prop('checked', false);
	$('#editCategory').click();
}
function editCategory(){
	e.preventDefault();
	var categoryName = $("#edit_name").val();
	var catType = $("#edit_type").val();
	var id = $("#cat_id").val();
	var catStatus = $("#is_active").val();
	if(pass === confirm_pass){
			  var formData = $("#register_form").serialize();
			console.log(formData);
			$.ajax({
			     type: "POST",
			     url: './registration',
			     data: formData,
			     beforeSend:   function(){$('.loadingDiv').show();},
			     success: function(data) {
			         if(data == "success"){
			        	 $('#success').show();
				         $('#registerModal').click();
			         }else if(data == "email all ready exist !"){
			        	 $("#errorMessage").text(data);
			        	 $('#register_modelError').show();
			         }
			         else{
			        	 $('#error').show();
			        	 $('#registerModal').click();
			         }
			         $('.loadingDiv').hide();
			     }
			});
	}else{
		$("#errorMessage").text(" Password does not match with confirm password!.");
		$('#register_modelError').show();
	}
}
function add_category(e){
	e.preventDefault();
	cat_name = $("#cat_name");
	cat_type = $('#parent_cat' );
	if(cat_name.val() == "" && cat_type.val() == "-1"){
		 $('#errorMessage').text("(*) mandatory field");
    	 $('#success_alert').hide();
         $('#error_alert').show();
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
		        	 $('#parent_cat' ).val("-1");
		        	 $('#error_alert').hide();
		        	$('#success_alert').show();
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