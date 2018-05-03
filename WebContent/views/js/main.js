	//add new category..
		//open add_category modal...
		function add_category_btn(){
			$("#add_category_button").click();
			removePAllExistValues();
		}
		
		function add_category(e){
			e.preventDefault();
			cat_name = $("#cat_name");
			cat_type = $('#parent_cat' );
			if(cat_name.val() == "" || cat_type.val() == ""){
				 $('#cat_errMsg').text("(*) mandatory field");
		    	 $('#success_alert').hide();
		         $('#catErr').show();
			}else{
				$.ajax({
				     type: "POST",
				     url: '../add_category',
				     data: $("#add_cat_form").serialize(),
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 console.log(data);
				         if(data === "success"){
				        	 shwoSuccessMsg('category added successfully..!!');
				        	$("#add_cat_close_btn").click();
				         }else if(data === "category allready exist"){
				        	 $('#cat_errMsg').text(data);
				        	 $('#success_alert').hide();
					        	$('#catErr').show();
				         }else{
				        	 window.location.href = './error.jsp';
				         }
				         $('.loadingDiv').hide();
				     }
				});
			}
			
		}

	//add new brands..
		function add_brand(e){
			console.log('from add brands');
		e.preventDefault();
		var b_name = $('#b_name');
		if(!b_name.val() == ""){
			if($.isNumeric(b_name.val())){
				console.log('from here');
				b_name.addClass("border-danger");
				$('#b_error').addClass("text-danger").text('brand name must be string !');
			}else{
				console.log('from else');
				$.ajax({
				     type: "POST",
				     url: '../add_brands',
				     data: $('#brand_form').serialize(),
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 console.log(data);
				         if(data == "success"){
				        	 shwoSuccessMsg('Brand added successfully !');				        	 
					         fetchAllBrand();
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
			b_name.addClass("border-danger");
			$('#b_error').addClass("text-danger").text('brand name should not kept blank !');
		}
	}
		
		//open add_product modal
		function add_product_btn(){
			removePAllExistValues();
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
				        	 shwoSuccessMsg('successfully product is added');
				        	 removePAllExistValues();
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
			    	 root = '<option value = "0">root</option>'
			    	 $('#p_category').html(choose + data);
			    	 $('#parent_cat').html(choose+ root + data);
			     }
			});
		 }
		 
		//fetching all brand..
		 fetchAllBrand();
		 function fetchAllBrand(){
			 $.ajax({
			     type: "GET",
			     url: '../get_brand',
			     success: function(data) {
			    	 choose = '<option value = "">--select brand</option>';
			    	 $('#p_brand').html(choose + data);
			     }
			});
		 }
		 
		 //success message..
		 function shwoSuccessMsg(message){
			 $('#success_alert').show();
        	 $('#error_alert').hide();
	         $('#successMessage').text(message);
		 }
		 
		 //removed values after inserting from all edit modal..
		 function removePAllExistValues(){
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
				 $("#cat_name").val("");
	        	 $('#parent_cat' ).val("");

		 }