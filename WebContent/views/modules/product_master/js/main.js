//open add_product modal
		function add_product_btn(){
			removePAllExistValues();
			$('#p_err').hide();
			$("#add_product_button").click();
		}

		function add_product(e){
			e.preventDefault();
			if($("#p_name").val() == "" || $('#p_category').val() == "" || $('#p_brand').val() == "" ||$('#p_batch').val() == "" || $('#p_price').val() == "" || $('#p_quantity').val() == "" ||$('#p_weight').val() == ""){
				$('#p_err').hide(); 
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
//				        	 /window.location.href = './error.jsp';
				        	 $('#add_product_close_btn').click();
				         }
				         $('.loadingDiv').hide();
				     }
				});
			}
		}
		//update brand status..
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
					     type: "GET",
					     url: './update_product',
					     data: {id : id, status : status},
					     beforeSend:   function(){$('.loadingDiv').show();},
					     success: function(data) {
					    	 
					    	 console.log(data);
					    	 
					         if(data == "UPDATED"){
					        	 shwoSuccessMsg('successfully status updated !');
					        	 ListProductWithAjax(page_no);
					         }else{
					        	 showErrorMsg("fail to update status , please try after some time !");
					        	
					         }
					        
					         $('.loadingDiv').hide();
					     }
					});
				}
			}
		
			//open edit product modal..
			function edit(product_id, page_no){
				 $.ajax({
				     type: "POST",
				     data: {id : product_id},
				     url: './get_product',
				     success: function(data) {
				    	var result =  $.parseJSON(data);
				    	$('#edit_page_no').val(page_no);
				    	$('#edit_id').val(product_id);
				    	$('#p_edit_date').val(result.added_date);
				    	$('#p_edit_name').val(result.p_name);
				    	$('#p_edit_exp').val(result.expiry_date);
				    	$('#p_edit_category').val(result.category_master_id);
				    	$('#p_edit_brand').val(result.brand_id);
				    	$('#p_edit_batch').val(result.batch_no);
				    	$('#p_edit_price').val(result.price);
				    	$('#p_edit_stock').val(result.stock);
				    	$('#p_edit_weight').val(result.weight);
				    	$('#p_edit_desc').val(result.desc);
				    	$('#p_edit_type').val(result.p_type);
				    	$('#edit_product_button').click();
				    	localStorage.setItem("p_edit_name", result.p_name);
				    	localStorage.setItem("p_edit_exp", result.expiry_date);
				    	localStorage.setItem("p_edit_category", result.category_master_id);
				    	localStorage.setItem("p_edit_brand", result.brand_id);
				    	localStorage.setItem("p_edit_batch", result.batch_no);
				    	localStorage.setItem("p_edit_price", result.stock);
				    	localStorage.setItem("p_edit_stock", result.p_name);
				    	localStorage.setItem("p_edit_weight", result.expiry_date);
				    	localStorage.setItem("p_edit_desc", result.desc);
				    	localStorage.setItem("p_edit_type", result.p_type);
				     }
				});
			}

		 //edit product..
			function edit_prod(e){
				e.preventDefault();
				console.log($("#edit_product_form").serialize());
				if(localStorage.getItem("p_edit_name") != $('#p_edit_name').val() || localStorage.getItem("p_edit_exp") != $('#p_edit_exp').val() || localStorage.getItem("p_edit_category") != $('#p_edit_category').val()|| localStorage.getItem("p_edit_brand") != $('#p_edit_brand').val()|| localStorage.getItem("p_edit_batch") != $('#p_edit_batch').val()|| localStorage.getItem("p_edit_price") != $('#p_edit_price').val()|| localStorage.getItem("p_edit_stock") != $('#p_edit_stock').val()|| localStorage.getItem("p_edit_weight") != $('#p_edit_weight').val()){
					$.ajax({
					     type: "POST",
					     url: './edit_prod',
					     data: $("#edit_product_form").serialize(),
					     headers :{
					    	 Accept : " application/json; charset=utf-8"
					     },
						 cache: false,
					     beforeSend:   function(){$('.loadingDiv').show();},
					     success: function(data) {
					    	 console.log(data);
					         if(data === "success"){
					        	 shwoSuccessMsg('successfully product is edited');
					        	$('#edit_product_button').click();
					        	ListProductWithAjax($('#edit_page_no').val());
					         }else{
					        	 console.log('fail from product js');
					        	 showErrorMsg('fail to edit product please try after some time');
						        	$('#edit_product_button').click();
					         }
					         $('.loadingDiv').hide();
					     }
					});
				}
			}
			//delete category modal..
			function delete_modal(id, product, page_no){
				$("#hidden_id").val(id);
				$("#hidden_parent").html(product);
				$("#hidden_page").val(page_no);
				$("#delete_modal_btn").click();
				 $('#success_alert').hide();
				 $('#error_alert').hide();
			}
			//delete products..
			function deletebrand(){
				var id = $("#hidden_id").val();
				var product =  $("#hidden_parent").text();
				var current_page = $("#hidden_page").val();
				console.log(product);
				$.ajax({
				     type: "POST",
				     url: './delete_product',
				     data: {id : id},
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 console.log(data);
				         if(data === "success"){
				        	 shwoSuccessMsg('successfully product "<i><strong>'+ product + '</strong></i>" is deleted !');
				        	 ListProductWithAjax(current_page);
				         }else{
				        	// window.location.href = './error.jsp';
				        	 console.log('some error');
				         }
				         $('.loadingDiv').hide();
				         $("#close_delet").click();
				     }
				});
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
			    	 $('#parent_cat').html(choose+ root + data);
			    	 $('#p_edit_category').html(root + data);
			     }
			});
		 }
		 
		//fetching all brand..
		 fetchAllBrand();
		 function fetchAllBrand(){
			 $.ajax({
			     type: "GET",
			     url: './get_brand',
			     success: function(data) {
			    	 choose = '<option value = "">--select brand</option>';
			    	 $('#p_brand').html(choose + data);
			    	 $('#p_edit_brand').html(data);
			     }
			});
		 }
			//listing using pagination and ajax..
			function ListProductWithAjax(current_page){
				console.log(current_page + "= crnt pg");
				$.ajax({
				     type: "POST",
				     url: './list_product',
				     data: {page_no : current_page},
				     headers :{
				    	 Accept : " application/json; charset=utf-8"
				     },
					 cache: false,
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 //console.log(data);
				    	 if(data == "empty table"){
					    	 
					    	 $("#footer").html("");
					    	 $("#cat_table").html("<h5> No records to display </h5>");
					        
				     	}else if(data == "fail"){
				     		 $('#errorMessage').text("fail from ListBrandWithAjax !");
				        	 $('#success_alert').hide();
					         $('#error_alert').show();
				     	}else{
				     		var result = $.parseJSON(data);
					    	 $("#footer").html(result.paginations);
					    	 $("#prod_tbl").html(result.tbody);
				     	}
				    	 $('.loadingDiv').hide();
				     }
				});
			}
		 //success message..
		 function shwoSuccessMsg(message){
			 $('#success_alert').show();
        	 $('#error_alert').hide();
	         $('#successMessage').html(message);
		 }
		 
		 //error message..
		 function showErrorMsg(message){
			 $('#error_alert').show();
	    	 $('#success_alert').hide();
	         $('#errorMessage').html(message);    
		 }
