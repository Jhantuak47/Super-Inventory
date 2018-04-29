
function login_form_validation(){
	var email = $("#login_email").val();
	var pass = $("#login_password").val();
	console.log('from login validation');
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
