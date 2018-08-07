
function login_form_validation(){
	var email = $("#login_email").val();
	var pass = $("#login_password").val();
	if(email == "" || pass == ""){
		
		return false;
	}else
	return true;
}
function open_reg_modal(){
	$('#register_form').trigger('reset');
	$('#register_btn').click();
}
function registration(e){
	e.preventDefault();
	var name = $("#name").val();
	var email = $("#email").val();
	var pass = $("#password").val();
	var mobile = $('#mobile').val();
	var confirm_pass = $("#password-confirm").val();
	if(pass === confirm_pass){
		if(mobile.length == 10){	
			//var formData = new FormData($("#register_form"));
			  var formData = $("#register_form").serialize();
			$.ajax({
			     type: "POST",
			     url: './registration',
			     data: formData,
			     beforeSend:   function(){$('.loadingDiv').show();},
			     success: function(data) {
			         if(data == "success"){
			        	 $('#success').show();
				         $('#registerModal').click();
			         }else if(data == "EMAIL_EXIST"){
			        	 $("#errorMessage_register").text("Email allready exist !");
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
			$("#errorMessage_register").html(" Mobile number should be equal to 10 digits !!");
			$('#register_modelError').show();
		}
			
	}else{
		$("#errorMessage_register").html("Password does not match with confirm password !!");
		$('#register_modelError').show();
	}
	
}
