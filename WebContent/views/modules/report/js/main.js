$(document).ready(function(){
	var domain = "http://localhost:8082/superInvent/";
	
	$("#mode_type").change(function(){
		var type = $("#mode_type").val();
		console.log(type);
		if(type == 1){
			$("#heading").html('Provide Seller Info');
			var tbody = '<tr><td>1</td><td style="text-align: center;">Seller Name :</td><td><input type = "text" class = "form-control form-control-sm" name = "name" placeholder="Select Name" required="required"></td></tr>';
				tbody +='<tr><td>1</td><td style="text-align: center;">Selling Date :</td><td><input type = "date" class = "form-control form-control-sm" name = "date" required="required"></td> </tr>';
				tbody +='<tr><td>1</td><td style="text-align: center;">Due :</td> <td> </td> </tr>';
		}else if(type == 2){
			$("#heading").html('Provide Purchase Info');
			var tbody = '<tr><td>1</td><td style="text-align: center;">Vendor Name :</td><td><input type = "text" name = "name" class = "form-control form-control-sm" placeholder="Select Name" required="required"></td></tr>';
				tbody +='<tr><td>1</td><td style="text-align: center;">Purchase Date :</td><td><input type = "date" name = "date" class = "form-control form-control-sm" placeholder="Select Name" required="required"></td> </tr>';
				tbody +='<tr><td>1</td><td style="text-align: center;">Payment Mode :</td> <td> </td> </tr>';
		}
		$("#report_generate").empty();
		$("#report_generate").append(tbody);
	});
	
	//on form submit
	$("#report_form_btn").click(function(e){
		e.preventDefault();
		var formData = $("#report_form_data").serialize();
		$.ajax({
		     type			:		 "POST",
		     url			: 		  domain + "/reporsts",
		     data			: 		  formData,
		     beforeSend		:   	  function(){$('.loadingDiv').show();},
		     success	: function(data) {
		    	 console.log(data);
		        /* if(data == "success"){
		        	 shwoSuccessMsg('Brand added successfully !');
		        	 ListBrandWithAjax(1);
		         }else if(data == "Brand already exist !"){
		        	 showErrorMsg(data);  
		         }
		         else{
		        	 console.log('from else to error.jsp');
		        	//window.location.href = "http://localhost:8082/superInvent/error.jsp";
		         }
		         $('.loadingDiv').hide();
		         b_name.val("");
		         $("#close_addBrand").click();*/
		     }
		});
		alert(formData);
	});
})