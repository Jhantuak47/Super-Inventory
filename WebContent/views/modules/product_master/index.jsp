<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.superInvent.POJO.ProductMaster"%>
<%@ page import="com.google.gson.Gson"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
<% Gson gson = new Gson();
List<ProductMaster> products =  new ArrayList<ProductMaster>();
			products = (List<ProductMaster>)request.getAttribute("products");
			 int page_no = (Integer)request.getAttribute("page_no");
			int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
			 int i = ( (page_no - 1) * numberOfResultPerPage ) + 1; %>
  <div class = "container">
            <div class="card mx-auto" style="max-width: 60rem;">
                <div class="card-header" text-white" style="background-color:#1e90ff;">
                     <div class="row">
                     	<div class="col-md-2">
	                     Product List
	                     </div>
	                     <div class="col-md-6">
	                         <form  onsubmit = "return false">
	                         	<div class="input-group">
									<select id = "searchType" class = "form-control-sm" id = "searchTyoe">
									<option value = "">select type</option>
									<option value = "brand">Brand</option>
									<option value = "category">Category</option>
									<option value = "name">Products</option>
									<option value = "expired">Expired</option>
									<option value = "price">Price(>=)</option>
									</select>&nbsp;
										
											<input class = "form-control-sm" type="text" name="searchinput" id="searchinput" size="30%"  placeholder="search" list ="users"
					                                   style="background-color : #d1d1d1;"> <span class="input-group-btn"></span>
					                        &nbsp;
					                    <button class="btn btn-success btn-sm" type="submit" style="display:;" onclick="search()" id="search_icon">
	                                     <i class="fa fa-search"></i></span>
	                                   </button>
	                                   <button style="display:none ;font-size:20px; margin-top: 1px;" id="filter_button" class="btn-danger" onclick="removeFilter(event);"><i class="fa fa-remove"></i></button>					
								</div>          
	                         </form>
	                     </div>
	                     <div class="col-md-4" style="">
	                     <button style="margin-left:60px;display:none;font-size:16px;" id="delete-row" class="btn-danger" onclick="deleteProductOnCheck(<%= page_no %>);"><i class='fa fa-trash-o'></i>&nbsp;Delete</button><!--delete button -->
	                     <a href = "#" onclick = "add_product_btn()" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Add</a>
	                     </div>
                    </div><!--row end -->
                    <div class="clearfix"></div>
                </div>
                <div class="card-body">
                    <span id="searchError"></span>
                    <table class="table table-striped">
                        <thead>
                          <tr>
                            <th><input type="checkbox"  onclick = "head_checkbox_on_click()" id="headchek"></th>
                            <th>#</th>
                            <th>Name</th>
                            <th>Brand</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Expiry</th>
                            <th style = "padding-left:40px;" >Action</th>
                            <th style = "">Views</th>
                          </tr>
                        </thead>
                        <tbody id = "prod_tbl">
                           <% 
                   			
	                           for(ProductMaster product:products){%>
		 
                                    <tr id="<%= product.getId() %>">
                                    <td><input type="checkbox"  onclick = "checkbox_on_click()" name="record"></td>
                                    <td><%= i %></td>
                                    <td><%= product.getP_name() %></td>
                                    <td><%= product.getBrand() %></td>
                                    <td><%= product.getCategory() %></td>
                                    <td>Rs.<%= product.getPrice() %></td>
                                    <td><%= product.getExpiry_date() %></td>
	                                <td>
	                                	<a href="javascript: edit(<%= product.getId() %>,<%= page_no %>)"  class="btn btn-primary btn-sm active"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;Edit</a>
	                                  	<%if(product.getStatus() == 1){ %>
                                        <a href = "#" onclick="Update_category_status(<%=product.getId() %>, <%= page_no %>,<%= product.getStatus() %> );" class="btn btn-danger btn-sm" style="margin-right:10px;">Deactive</a>
                                        <%}else{%>
                                        <a href = "#" onclick="Update_category_status(<%=product.getId() %>, <%= page_no %>, <%= product.getStatus() %> );"  class="btn btn-success btn-sm " style="margin-right:10px;">Activate</a>
                                     	<%}%>                                 	 
	                                </td>
	                                  <td><a class = "btn btn-info active btn-sm" onclick = "viewProduct(<%= product.getId() %>)"><i class="fa fa-eye"></i>&nbsp;View</a></td>
                                </tr> 
                                
                              <%i++; }%>
                        </tbody>
                    </table>
                </div><!--pannel body -->
                <div class="card-footer" id="pannel_footer">
                   <div align="center" id = "footer"> <%= request.getAttribute("pagination") %> </div> 
                </div>
    </div><!--end of row  -->	
  </div> <!-- end of container -->
	<jsp:include page="<%=\"templets/edit.jsp\"%>"/>
		<jsp:include page="<%=\"templets/view.jsp\"%>"/>
  <jsp:include page="<%=\"templets/add_product.jsp\"%>"/>
  <jsp:include page="<%=\"templets/delete.jsp\"%>"/>
  <script type="text/javascript" src="./views/modules/product_master/js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>