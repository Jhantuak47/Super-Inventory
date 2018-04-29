
function add_product_btn(){
	removeProductModelValue();
	$('#p_err').hide();
	$("#add_product_button").click();
}

function add_product(e){
	e.preventDefault();
	console.log('hred');
	if($("#p_name").val() == "" || $('#p_category').val() == "" || $('#p_brand').val() == "" ||$('#p_batch').val() == "" || $('#p_price').val() == "" || $('#p_quantity').val() == "" ||$('#p_weight').val() == ""){
		 $('#p_errMsg').text("(*) mandatory field");
        $('#p_err').show();
	}else{
		var formData = $("#add_product_form").serialize();
		console.log(formData);
		$.ajax({
		     type: "POST",
		     url: '/superInvent/add_product',
		     data: formData,
		     beforeSend:   function(){$('.loadingDiv').show();},
		     success: function(data) {
		    	 console.log(data);
		         if(data === "success"){
		        	 $('#successMessage').text('successfully product is added');
		        	 removeProductModelValue();
		        	 $('#error_alert').hide();
		        	$('#success_alert').show();
		        	$('#add_product_close_btn').click();
		         }else if(data.indexOf('already exist !') != -1){
		        	 $('#p_errMsg').text(data);
			        	$('#p_err').show();
		         }else{
		        	 console.log('fail from product js');
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
	     url: '../list_category',
	     success: function(data) {
	    	 choose = '<option value = "">--select category</option>';
	    	 $('#p_category').html(choose + data);
	     }
	});
 }
 
//fetching all brand..
 fetchAllBrand();
 function fetchAllBrand(){
	 $.ajax({
	     type: "GET",
	     url: '../list_brand',
	     success: function(data) {
	    	 choose = '<option value = "">--select brand</option>';
	    	 $('#p_brand').html(choose + data);
	     }
	});
 }
 function removeProductModelValue(){
	 $("#p_name").val("");
		$('#p_category').val("");
		$('#p_brand').val("");
		$('#p_batch').val("");
		$('#p_price').val("");
		$('#p_quantity').val("");
		$('#p_weight').val("");
		$('#p_desc').val("");
		$('#p_type').val("");
		$('#p_exp').val("");

 }