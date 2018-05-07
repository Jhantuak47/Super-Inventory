 $( document ).ready(function() {
        var domain = "http://localhost:8082/superInvent";
        
    	//get invoice items...
     	getInvoiceItem();
     	//add button to add row.
    	$('#add').click(function(){
    		getInvoiceItem();
    	});
    	function getInvoiceItem(){
    		var id = $("#number").text();
    		console.log(id + "id");
    		$.ajax({
    			
    				url		:	domain + "/get_item",
    				method	:	"GET",
    				data	:	{row_no : 1, row_num : id},
    				beforeSend:   function(){$('.loadingDiv').show();},
    			     success: function(data) {
    			    	 $("#invoice_item").append(data);
    			    	 $('.loadingDiv').hide();
    			     }
    				
    		});
    	}
    	
    	//remove button to remove one row...
    	$('#remove').click(function(){
    		 $("#invoice_item").children("tr:last").remove();
    	});
    });

