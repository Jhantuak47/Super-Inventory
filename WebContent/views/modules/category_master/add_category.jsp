<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
  <div class = "container">
	        	<div class="card mx-auto" style="max-width: 55rem;">
					 <h5 class="card-header">Add Category</h5>
	                <div class="card-body">
	                	<jsp:include page="<%=\"/views/inc/alerts.jsp\"%>"/>
	                    <form class="form-horizontal" method="POST" action= "" id = "add_cat_form">
	
	                        <div class="form-group">
	                            <label  class="col-md-4 control-label">Category Name :</label>
	
	                            <div class="col-md-6">
	                                <input list="Category" id="cat_name" class="form-control" name="cat_name" required />
									<datalist id="Category">
										<option value="Softwares">
										<option value="Clothing">
										<option value="Electronics">
										<option value="Medicine">
										<option value="Game">
										<option value="Electronics">
										<option value="Medicine">
										<option value="Game">
									</datalist>
								</div>
	                        </div>
	
	                        <div class="form-group">
	                            <label for="parent_cat" class="col-md-4 control-label">Category Type :</label>
								<% List<Object[]> allCategories = new ArrayList<Object[]>();
									allCategories = (List<Object[]>)request.getAttribute("allCategories");
								%>
	                            <div class="col-md-6">
	                            	<select class="custom-select" id = "parent_cat" name ="parent_cat" required="required" >
									  <option selected value = "-1">--select category</option>
									  <option value="0">root</option>
							<% if(allCategories != null && !allCategories.isEmpty()){ 
									for(Object[] objects : allCategories){%>
									  <option value=<%=objects[1]%>><%= objects[0]%></option>
							<% }}%>
									</select>
	                                    <span class="help-block">
	                                        <strong id = "login_pass_error"></strong>
	                                    </span>
	                            </div>
	                        </div>
	
	                        <div class="form-group">
	                        	<label for="status" class="col-md-3 control-label">Category Status :</label>
	                        		<input type="checkbox" name="status" value = "1"> &nbsp;Is Active ..?
	                        </div>
	
	                        <div class="form-group">
	                            <div class="col-md-8 col-md-offset-4">
	                                <button type="submit" class="btn btn-success" onclick="add_category(event);">
	                                   <i class="fa fa-plus" aria-hidden="true"></i>&nbsp; Add
	                                </button>
	                            </div>
	                        </div>
	                    </form>
	                </div><!--end of card body  -->
           		 </div><!--end of card  -->
  
   </div> <!-- end of container -->
   <script type="text/javascript" src="./views/modules/category_master/js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>