	
	//call to add brand modal
	function add(){
		console.log('inside add brand');
		$('#add_brand_button').click();
	}
	
	//add new brands..
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
			     url: './add_brands',
			     data: $('#brand_form').serialize(),
			     beforeSend:   function(){$('.loadingDiv').show();},
			     success: function(data) {
			    	 console.log(data);
			         if(data == "success"){
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
			         $("#close_addBrand").click();
			     }
			});
		}
	}else{
		b_name.addClass("border-danger");
		$('#b_error').addClass("text-danger").text('brand name should not kept blank !');
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
				     url: './update',
				     data: {id : id, status : status},
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 
				    	 console.log(data);
				    	 
				         if(data == "UPDATED"){
				        	 shwoSuccessMsg('successfully status updated !');
				        	 ListBrandWithAjax(page_no);
				         }else{
				        	 showErrorMsg("fail to update status , please try after some time !");
				        	
				         }
				        
				         $('.loadingDiv').hide();
				     }
				});
			}
		}
	
	// edit modal
	function edit(id, name, is_check, page_no, created_at){
		$('#edit_name').val(name);
		$('#created_at').val(created_at);
		$('#cat_id').val(id);
		$('#page_no').val(page_no);
		
		if(is_check == 1){
			$('#is_active').prop('checked', true);
		}else
			$('#is_active').prop('checked', false);
		
		localStorage.setItem("edit_name", name);
		localStorage.setItem("is_check", is_check);
		$('#editCategory').click();
	}
	
	//update Brand..
	function editBrand(e){
		e.preventDefault();
		var brandName = $("#edit_name").val();
		var id = $("#cat_id").val();
		var page_no = $('#page_no').val();
		var status = ($('#is_active').prop('checked') == true) ? 1 : 0;
		if(brandName != localStorage.getItem("edit_name") || status != localStorage.getItem("is_check")){
			
			if(brandName != null){	
				var formData = $("#edit_brand_form").serialize();
				$.ajax({
				     type: "POST",
				     url: './update',
				     data: formData,
				     beforeSend:   function(){$('.loadingDiv').show();},
				     success: function(data) {
				    	 
				    	 console.log(data);
				    	 
				         if(data == "UPDATED"){
				        	 shwoSuccessMsg("Brand successfully updated !");
				        	 ListBrandWithAjax(page_no);
				         }else{
				        	 showErrorMsg("fail to update category , please try after some time !");
				         }
				        
				         $('.loadingDiv').hide();
				     }
				});
				$("#edit_close_modal").click();
			}
		}
	}
	//delete brand modal..
	function delete_modal(id, brand, page_no){
		$("#hidden_id").val(id);
		$("#hidden_parent").html(brand);
		$("#hidden_page").val(page_no);
		$("#delete_modal_btn").click();
		 $('#success_alert').hide();
		 $('#error_alert').hide();
	}
	//delete brand..
	function deletebrand(){
		var id = $("#hidden_id").val();
		var brand =  $("#hidden_parent").text();
		var current_page = $("#hidden_page").val();
		console.log(brand);
		$.ajax({
		     type: "POST",
		     url: './delete_brand',
		     data: {id : id, status : "single"},
		     beforeSend:   function(){$('.loadingDiv').show();},
		     success: function(data) {
		    	 console.log(data);
		         if(data === "success"){
		        	 shwoSuccessMsg('successfully category "<i><strong>'+ brand + '</strong></i>" is deleted !');
		        	ListBrandWithAjax(current_page);
		         }else if(data === "dependend"){
		        	 showErrorMsg(brand + " is dependend, fail to delete !");
		         }else if(data === "hasProduct"){
		        	 showErrorMsg(brand + " having products, fail to delete !");
		         }else{
		        	// window.location.href = './error.jsp';
		        	 console.log('some error');
		         }
		         $('.loadingDiv').hide();
		         $("#close_delet").click();
		     }
		});
	}
	
	 //searching..
	 $("#searchinput").keyup(function(){
		 var searchValue = $("#searchinput").val();
		 var searchType = "";
		 var searchTable = "brand";
			 	
		 	if(searchValue != ""){
		 			$.ajax({
					     type: "POST",
					     url: './search',
					     data: {searchValue : searchValue, searchType : searchType, searchTable : searchTable},
	
					     success: function(data) {
					    	 console.log("data = " + data);
					    	 if(data !=""){
					    		 $("#brand_table").html(data);
						    	 $("#footer").html(""); 
					    	 }else{
					    		 $("#footer").html("<h5><span style = 'color:red;'> sorry ! no result found .</span></h5>");
					    		 $("#brand_table").html(data);
						    	
					    	 }
					    	 
					     }
					});
		 	}else{
		 		ListBrandWithAjax(1);
		 	}
		 	
	 });
	 //search function..
	 function search(e){
		// e.preventDefault();
		 console.log("from search");	
		 var searchValue = $("#searchinput").val();
		 var searchType = "";
		 var searchTable = "brand";
		 	if(searchValue != ""){
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
				     }
				});
		 		
		 		$('.loadingDiv').hide();
		 	}else{
		 		ListBrandWithAjax(1);
		 	}
		 
	 }
	//listing using pagination and ajax..
	function ListBrandWithAjax(current_page){
		console.log(current_page);
		$.ajax({
		     type: "POST",
		     url: './list_brand',
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
			    	 $("#brand_table").html(result.tbody);
		     	}
		    	 $('.loadingDiv').hide();
		     }
		});
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
    function deleteBrandOnCheck(){
   	 if(confirm('are you sure ? dlete checked item/items !!')){
	            var array = [];
	              $("table tbody").find('input[name="record"]').each(function(){
	                  if($(this).is(":checked")){
	                      array.push($(this).parents("tr").attr("id"));
	                  }
	              });
	              $.ajax({
					     type: "POST",
					     url: './delete_brand',
					     data: {ids:array, status : "multiple"},
					     beforeSend:   function(){$('.loadingDiv').show();},
					     success: function(data){
					    	 console.log(data);
					    	 if(data == "success"){
					    		 alert("congrats ! Item deleted successfully !");
					    		 $('#headchek').prop('checked', false);
					    		 ListBrandWithAjax(1);
					    	 }else if(data === "hasProduct"){
					        	 showErrorMsg("Sory some brand have products, fail to delete !");
					         }else{
					    		console.log('error from else'); 
					    	 }
					    	 $('.loadingDiv').hide();
					     }
					});
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
	
	//success message..
	 function shwoSuccessMsg(message){
		 $('#success_alert').hide();
    	 $('#successMessage').html(message);
    	 $('#success_alert').show();
    	 $('#error_alert').hide();
	 }
	 //error message..
	 function showErrorMsg(message){
		 $('#error_alert').show();
    	 $('#success_alert').hide();
         $('#errorMessage').html(message);    
	 }