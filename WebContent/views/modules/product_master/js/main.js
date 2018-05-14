//open add_product modal
		function add_product_btn(){
			console.log("from add btn");
			removeAllExistValues();
			$('#p_quantity').val(0);
			$('#p_err').hide();
			$("#add_product_button").click();
		}

		function add_product(e){
			e.preventDefault();
			if($("#p_name").val() == "" || $('#p_category').val() == "" || $('#p_brand').val() == "" ||$('#p_batch').val() == "" || $('#p_price').val() == "" || $('#cost_price').val() == "" ||$('#bill_no').val() == "" ||$('#ven_name').val() == ""){
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
				        	 removeAllExistValues();
				        	 ListProductWithAjax(1);
				         }else if(data.indexOf('already exist !') != -1){
				        	 $('#p_errMsg').text(data);
					        	$('#p_err').show();
				         }else{
				        	 console.log('fail from product js');
//				        	 /window.location.href = './error.jsp';
				        	 $('#add_product_close_btn').click();
				         }
				         $('.loadingDiv').hide();
				         $('#add_product_close_btn').click();
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
		 function removeAllExistValues(){
			 $("#p_name").val("");
				$('#p_category').val("");
				$('#p_brand').val("");
				$('#p_batch').val("");
				$('#p_price').val("");
				$('#cost_price').val("");
				$('#p_quantity').val("");
				$('#p_desc').val("");
				$('#p_type').val("");
				$('#p_exp').val("");
				$("#cat_name").val("");
	        	$('#parent_cat' ).val("");
	        	$('#ven_name').val("");
	        	$('#bill_no').val("");

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
		 
		 //search type on change
		 $("#searchType").change(function(){
			if($(this).val() == "expired"){
				$("#searchinput").val($(this).val());
				search();
			}
			if($(this).val() == ""){
				$("#searchinput").val("");
				ListProductWithAjax(1);
			}
		 });
		 
		 //searching..
		 $("#searchinput").keyup(function(){
			 var searchValue = $("#searchinput").val();
			 var searchType = $("#searchType").val();
			 var searchTable = "products";
			 var indicator= 1;
			 if(searchType == "price"){
				 if(isNaN(searchValue)){
					 alert('please enter a valid price !');
					 $("#searchinput").val("");
					 	indicator = 0;
				 }
					
			 }
			 if(searchType == "expired"){
				 if(searchValue != "expired"){
					 alert('you need to write expired !');
					 $("#searchinput").val("expired");
				 }
			 }
				 	
			 	if(searchType != "" && searchValue != "" && indicator){
			 		if(searchType != "expired"){
			 			$.ajax({
						     type: "POST",
						     url: './search',
						     data: {searchValue : searchValue, searchType : searchType, searchTable : searchTable},
						     beforeSend:   function(){$('.loadingDiv').show();},
						     success: function(data) {
						    	 console.log(data);
						    	
						    	 if(data !=""){
						    		 $("#prod_tbl").html(data);
							    	 $("#footer").html(""); 
						    	 }else{
						    		 $("#footer").html("<h5><span style = 'color:red;'> sorry ! no result found .</span></h5>");
						    		 $("#prod_tbl").html(data);
							    	
						    	 }
						    	 $('.loadingDiv').hide();
						     }
						});
			 		}
			 		
			 	}else if(searchType == ""){
			 		alert("please select search type");
			 		$("#searchinput").val("");
			 	}else{
			 		ListProductWithAjax(1);
			 	}
			 	
		 });
		 
		 //search function..
		 function search(e){
			// e.preventDefault();
			 console.log("from search");	
			 var searchValue = $("#searchinput").val();
			 var searchType = $("#searchType").val();
			 var searchTable = "products";
			 	if(searchType != "" && searchValue != ""){
			 		$.ajax({
					     type: "POST",
					     url: './search',
					     data: {searchValue : searchValue, searchType : searchType, searchTable : searchTable},
					     beforeSend:   function(){$('.loadingDiv').show();},
					     success: function(data) {
					    	 console.log(data);
					    	// alert(data);
					    	 if(data !=""){
					    		 $("#prod_tbl").html(data);
						    	 $("#footer").html(""); 
					    	 }else{
					    		 $("#footer").html("<h5><span style = 'color:red;'> sorry ! no result found .</span></h5>");
					    		 $("#prod_tbl").html(data);
					    	 }
					    	 
					    	 $('.loadingDiv').hide();
					     }
					});
			 	}else if(searchType == ""){
			 		alert("please select search type");
			 		$("#searchinput").val("");
			 	}else{
			 		ListProductWithAjax(1);
			 	}
			 
		 }
		 
		 //head-check-box..
	     function head_checkbox_on_click(){
	         var indicator = true;
	         
	        if($('#headchek').prop('checked') == true){
	             $("table tbody").find('input[name="record"]').each(function(){
	                //checked all un-checked check-box..
	                 if(!$(this).is(":checked")){
	                         $(this).prop('checked',true);
	                     }
	             });
	             $('#delete-row').show();
	         }
	         else
	           {
	             $("table tbody").find('input[name="record"]').each(function(){
	                //checked all un-checked check-box..
	                 if($(this).is(":checked")){
	                         $(this).prop('checked',false);
	                     }
	             });
	             $('#delete-row').hide(); 
	           }
	    }
	     //check boxes...
	     function checkbox_on_click(){
	         var indicator = true;
	         var indicateHeadCheakBox = true;
	         $('#delete-row').show();
	         $("table tbody").find('input[name="record"]').each(function(){
	             if($(this).is(":checked")){
	                     indicator = false;
	                 }
	             if(!$(this).is(":checked")){
	                  indicateHeadCheakBox = false;
	             }
	         });
	         if(indicator)
	           $('#delete-row').hide(); 
	         if(indicateHeadCheakBox)
	            $('#headchek').prop('checked',true);
	     }
	     
	     function deleteProductOnCheck(current_Page){
	    	 if(confirm('are you sure ? dlete checked item/items !!')){

		            var array = [];
		              $("table tbody").find('input[name="record"]').each(function(){
		                  if($(this).is(":checked")){
		                      array.push($(this).parents("tr").attr("id"));
		                  }
		              });
		              $.ajax({
						     type: "POST",
						     url: './delete_product',
						     data: {id:array},
						     beforeSend:   function(){$('.loadingDiv').show();},
						     success: function(data){
						    	 console.log(data);
						    	 if(data == "success"){
						    		 alert("congrats ! Item deleted successfully !");
						    		 $('#headchek').prop('checked', false);
						    		 ListProductWithAjax(current_Page);
						    	 }else{
						    		 
						    	 }
						    	 
						    	 $('.loadingDiv').hide();
						     }
						});
	    	 }
	     }
	     //view product
	     function viewProduct(product_id){


	    	 $.ajax({
			     type: "POST",
			     data: {id : product_id},
			     url: './get_product',
			     success: function(data) {
			    	 console.log(data);
			    	var result =  $.parseJSON(data);
			    	$('#view_date').val(result.added_date);
			    	$('#view_name').val(result.p_name);
			    	$('#view_exp_dt').val(result.expiry_date);
			    	$('#view_batch_no').val(result.batch_no);
			    	$('#viewPrice').val(result.price);
			    	$('#view_stock').val(result.stock);
			    	$('#view_weight').val(result.weight);
			    	$('#view_desc').val(result.desc);
			    	$('#view_type').val(result.p_type);
			    	 $("#view_product_button").click();
			     }
			});
	    	 
	     }