	function add_brand(e){
		e.preventDefault();
		var b_name = $('#b_name');
		if(!b_name.val() == ""){
			if($.isNumeric(b_name.val())){
				console.log('from here');
				b_name.addClass("border-danger");
				$('#b_error').addClass("text-danger").text('brand name must be string !');
			}else{
				$.ajax({
				     type: "POST",
				     url: '../brand_master',
				     data: $('#brand_form').serialize(),
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 console.log(data);
				         if(data == "success"){
				        	 $('#success_alert').show();
				        	 $('#error_alert').hide();
					         $('#successMessage').text('Brand added successfully !');
				         }else if(data == "Brand already exist !"){
				        	 $('#error_alert').show();
				        	 $('#success_alert').hide();
					         $('#errorMessage').text('Brand already exist !');      
				         }
				         else{
				        	 console.log('from else to error.jsp');
				        	//window.location.href = "http://localhost:8082/superInvent/error.jsp";
				         }
				         $('.loadingDiv').hide();
				         b_name.val("");
				         $("#close_addBrand").click();
				     }
				});
			}
		}else{
			console.log('from here');
			b_name.addClass("border-danger");
			$('#b_error').addClass("text-danger").text('brand name should not kept blank !');
		}
	}