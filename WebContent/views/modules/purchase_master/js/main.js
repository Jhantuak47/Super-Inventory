 $( document ).ready(function() {
        var domain = "http://localhost:8082/superInvent";
        
    	//get invoice items...
     	getProductItem();
     	//add button to add row.
    	$('#addRow').click(function(){
    		getProductItem();
    		return false;
    	});
    	
    	function getProductItem(){
    		$.ajax({
    			
				url				:	domain + "/get_purchase_item_row",
				method			:	"GET",
			     success: function(data) {
			    	 $("#purchase_item").append(data);
			     }
		});
    	}
    	
    	//remove button to remove one row...
    	$('#remove').click(function(){
    		 $("#purchase_item").children("tr:last").remove();
    		 //calculate(0, $("#paid").val());
    		 return false;
    	});
    	
    	//on submiting form..
    	$('#purchase_form_btn').click(function(e){
    		e.preventDefault();
    		var formData = $("#purchase_form_data").serialize();
    		console.log(formData);
    		if($("#ven_name") != "" && $("#bill_no").val() != ""){
    			$.ajax({
        			url			:		domain+"/insert_product_bill",
        			type		:		"POST",
        			data		:		formData,
        			beforeSend	:   	function(){$('.loadingDiv').show();},
        			success		:	function(data){
        					
        					console.log(data);
        					if(data == "success"){
        						alert("conratulation ! successfully updated .");
        						location.reload();
        					}else{
        						alert("fail to add products, please try again !");
        						location.reload();
        					}
        					
        				}
        		});
    		}else{
    			alert("(*) fields are mandatory !");
    		}
    		
    		
    	});
    	
    	//cost Price...
    	$("#purchase_item").delegate(".cp","keyup", function(){
    		var cp = $(this);
    		var cost_price = 0;
    		var tr = $(this).parent().parent();
    		
    		  if(tr.find(".pro_name").val() == ""){
    			  alert('Please, first enter product name !');
    		  }else{
    			  
    			  if(isNaN(cp.val())){
  	    			alert("please enter a valid cost price !");
  	    			cp.val("");
	  	    	  }else{
	  	    			$(".cp").each(function(){
	  	    				cost_price += $(this).val()*1;
	  	    			});
	  	    		  $("#sub_total").val(cost_price);
	  	    	    }
    		  }

    	});
    	
    	//Item Name...
    	$("#purchase_item").delegate(".pro_name","keyup", function(){
    				tr = $(this).parent().parent();
    			  if($(this).val() != "" && !isNaN($(this).val())){
  	    			alert("please enter a valid Product name !");
  	    			$(this).val("");
	  	    	  }
//    			  if($(this).val() == ""){
//    				  
//    			  }
    	});
    	//Quantity	...
    	$("#purchase_item").delegate(".qty","keyup", function(){
    		
    		var qty = $(this);
    		var tr = $(this).parent().parent();
    		
    		  if(tr.find(".pro_name").val() == ""){
    			  alert('Please, first enter product name !');
    		  }else{
    			  
    			  if(isNaN(qty.val())){
  	    			alert("please enter a valid Quantity!");
  	    			qty.val("");
	  	    	  }
    		  }
    	});
    	//price	...
    	$("#purchase_item").delegate(".price","keyup", function(){
    		
    		var price = $(this);
    		var tr = $(this).parent().parent();
    		
    		  if(tr.find(".pro_name").val() == ""){
    			  alert('Please, first enter product name !');
    		  }else{
    			  
    			  if(isNaN(price.val())){
  	    			alert("please enter a valid Price!");
  	    			price.val("");
	  	    	  }
    		  }
    	});
    	//type	...
    	$("#purchase_item").delegate(".type","keyup", function(){
    		
    		var type = $(this);
    		var tr = $(this).parent().parent();
    		
    		  if(tr.find(".pro_name").val() == ""){
    			  alert('Please, first enter product name !');
    		  }else{
    			  
    			  if(type.val() != "" && !isNaN(type.val())){
  	    			alert("please enter a valid type!");
  	    			type.val("");
	  	    	  }
    		  }
    	});

 });
 
 