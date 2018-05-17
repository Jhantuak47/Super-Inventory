
function login_form_validation(){
	var email = $("#login_email").val();
	var pass = $("#login_password").val();
	if(email == "" || pass == ""){
		
		return false;
	}else
	return true;
}

function registration(e){
	e.preventDefault();
	var name = $("#name").val();
	var email = $("#email").val();
	var pass = $("#password").val();
	var confirm_pass = $("#password-confirm").val();
	if(pass === confirm_pass){
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
			        	 $("#errorMessage").text("Email allready exist !");
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
