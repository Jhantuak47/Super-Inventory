 $( document ).ready(function() {
        var domain = "http://localhost:8082/superInvent";
        
    	//get invoice items...
     	getInvoiceItem();
     	//add button to add row.
    	$('#add').click(function(){
    		getInvoiceItem();
    		return false;
    	});
    	
    	//call to get items for invoice..
    	function getInvoiceItem(){
    		$.ajax({
    			
    				url				:	domain + "/get_item",
    				method			:	"GET",
    			     success: function(data) {
    			    	 $("#invoice_item").append(data);
    			    	 var n = 0;
    			    	 $(".number").each(function(){
    			    		 $(this).html(++n);
    			    	 })
    			     }
    		});
    	}
    	
    	//on changing select option...
    	$("#invoice_item").delegate(".pid", "change", function(){
    		var pid = $(this).val();
    		var tr = $(this).parent().parent();
    		
    		$.ajax({
    				url				:	domain +"/get_product",
    				method			:	"POST",
    				dataType		:	"json",
    				data			:	{id : pid},
    				beforeSend		:   function(){$('.loadingDiv').show();},
    				 success: function(data) {
    			    	tr.find(".tqty").val(data.stock);
    			    	tr.find(".pro_name").val(data.p_name);
    			    	tr.find(".qty").val(0);
    			    	tr.find(".price").val(data.price);
    			    	if(data.stock > 0){
    			    		tr.find(".qty").val(1);
    			    		tr.find(".amt").html( tr.find(".qty").val() * tr.find(".price").val() );
    			    	}
    			    	
    			    	$('.loadingDiv').hide();
    			    	calculate(0, $("#paid").val());
    			     }
    		});
    	})
    	
    	//remove button to remove one row...
    	$('#remove').click(function(){
    		 $("#invoice_item").children("tr:last").remove();
    		 calculate(0, $("#paid").val());
    		 return false;
    	});
    	
    	//on typing the quantity
    	$("#invoice_item").delegate(".qty","keyup", function(){
    		var qty = $(this);
    		var tr = $(this).parent().parent();
    		if(isNaN(qty.val())){
    			alert("please enter a valid quantaty !");
    			qty.val("");
    		}else{
    			if( qty.val() - 0 > tr.find(".tqty").val() ){
    				alert("sorry we dont have that much stock !");
    				qty.val(1);
    			}
    		  tr.find(".amt").html( qty.val() * tr.find(".price").val() * 1 );
    		  calculate($("#discount").val(), $("#paid").val());
    		}
    	});
    	
    	function calculate(dis = 0, paid = 0){
    		var tot_amt = 0;
    		var gst = 0;
    		var net_tot = 0;
    		var discount = dis;
    		var paid_amount = paid;
    		var due  = 0;
    		$(".amt").each(function(){
    			tot_amt = (tot_amt * 1) + ( $(this).text() * 1 );
    		});
    		gst = 0.18 * tot_amt;
    		net_tot = gst + tot_amt;
    		net_tot = net_tot - discount;
    		due = net_tot - paid_amount;
    		$("#sub_total").val(tot_amt);
    		$("#gst").val(gst);
    		$("#net_tot").val(net_tot);
    		$("#due").val(Math.ceil(due));
    		$("#discount").val(discount);
    	}
    	
    	//calculating discount
    	$("#discount").keyup(function(){
    		calculate($(this).val(), $("#paid").val());
    	})
    	
    	//calculating paid and due..
    	$("#paid").keyup(function(){
    		var paid_amt  = $(this).val();
    		var discount = $("#discount").val();
    		calculate(discount, paid_amt);
    	});

    });
 
 var domain = "http://localhost:8082/superInvent";
	//feed data into database...
	function feedInvoiceInDB(e){
		e.preventDefault();
		console.log('form feed');
		var formData = $("#order_form_data").serialize();
		console.log("form data = "+ formData);
		
		$.ajax({
			url				:		domain + "/insert_invoice",
			type			:		"POST",
			data			:		formData,
			beforeSend		:   	function(){$('.loadingDiv').show();},
			success		: 	function(data) {
				 
					console.log(data);
					if(data ==  "success"){
						alert("Successfully Ordered Created !");
						location.reload(true);
					}else if(data == "QUANTITY_EXCEED"){
						showErrorMsg("sorry ! quantity exceed , we have limited stock !!");
					}else
						window.location.href = domain + '/error.php';
					
					
					$('.loadingDiv').hide();
		     }
		});	
	}

//error message..
function showErrorMsg(message){
	 $('#error_alert').show();
	 $('#success_alert').hide();
    $('#errorMessage').html(message);    
}

