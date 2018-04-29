<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.superInvent.POJO.CategoryMaster"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
  <div class = "container">
            <div class="card mx-auto" style="max-width: 55rem;">
                <div class="card-header" text-white" style="background-color:#1e90ff;">
                     <div class="row">
                     	<div class="col-md-3">
	                      Welcome to Category List
	                     </div>
	                     <div class="col-md-5">
	                         <form action="" method="" autocomplete="on">
	                                 <div class="input-group">
	                                   <input type="text" name="searchinput" id="searchinput" size="30%" class="" placeholder="Search Category" list ="users"
	                                   style="background-color : #d1d1d1;"> <span class="input-group-btn"></span>
	                                   <datalist id="users">
	                                     
	                                     </datalist>
	                                   <div class="clearfix"></div>
	                                   &nbsp;<button class="btn btn-success btn-sm" type="submit" style="display:;" onclick="searchSection(event)" id="search_icon">
	                                     <i class="fa fa-search"></i></span>
	                                   </button>
	                                   <button style="display:none ;font-size:20px; margin-top: 1px;" id="filter_button" class="btn-danger" onclick="removeFilter(event);"><i class="fa fa-remove"></i></button>
	                                 </div>             
	                         </form>
	                     </div>
	                     <div class="col-md-4" style="">
	                     <button style="margin-left:60px;display:none;font-size:16px;" id="delete-row" class="btn-danger" onclick="delete_modal();"><i class='fa fa-trash-o'></i>&nbsp;Delete</button><!--delete button -->
	                     <a onclick = "add();" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Add</a>
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
                            <th>Category</th>
                            <th>Type</th>
                            <th colspan="2"></th>
                          </tr>
                        </thead>
                        <tbody>
                           <% 
                            List<CategoryMaster> categoryLists =  new ArrayList<CategoryMaster>();
  									categoryLists = (List<CategoryMaster>)request.getAttribute("categoryLists");
	                           for(CategoryMaster List:categoryLists){%>
		               				
                                <tr id="<%= List.getId() %>">
                                    <td><input type="checkbox"  onclick = "checkbox_on_click()" name="record"></td>
                                    <td><%= List.getC_name() %></td>
                                    <td><%= List.getParentCategory() %></td>
                                    <td><a onclick="edit('<%=List.getId() %>', '<%=List.getC_name()%>', '<%=List.getParentCategory()%>', '<%=List.isC_status() %>');"  class="btn btn-primary btn-sm active pull-right" style="margin-left:100px"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;Edit</a></td>
                                    <td>
                                        <%if(List.isC_status()){ %>
                                        <a  onclick="deactivate();" class="btn btn-danger btn-sm pull-right" style="margin-right:10px">Deactivate</a>
                                        <%}else{%>
                                        <a onclick="activate();"  class="btn btn-success btn-sm pull-right" style="margin-right:10px">Activate</a>
                                     	<%}%>
                                     </td>
                                </tr>
                              <%}%>
                        </tbody>
                    </table>
                </div><!--pannel body -->
                <div class="card-footer" id="pannel_footer">
                           
                </div>
    </div><!--end of row  -->	
  </div> <!-- end of container -->
  <!-- Edit Modal -->
  	
<button type="button" id = "editCategory" class="btn btn-primary hidden" data-toggle="modal" data-target="#editCategoryModal">
</button>
<div id="editCategoryModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
	        <div class="modal-body">
	        	<div class="card">
					 <h5 class="card-header">Edit Category</h5>
	                <div class="card-body">
	                    <form class="form-horizontal" method="POST" action="" id = "edit_category_form">
	
	                        <div class="form-group">
	                            <label for="email" class="col-md-4 control-label">Category Name :</label>
	
	                            <div class="col-md-6">
	                                <input list="Category" id="edit_name" class="form-control" name="edit_name" required autofocus>
									<datalist id="Category">
										<option value="Software ">
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
							<input type="hidden" id = "cat_id">
	                        <div class="form-group">
	                            <label class="col-md-4 control-label">Category Type :</label>
	
	                            <div class="col-md-6">
	                            	<select class="custom-select" id = "edit_type">
									  <option selected>--select type</option>
									  <option value="0">root</option>
							<%for(CategoryMaster List:categoryLists){%>
										<option value="<%=List.getId()%>"><%=List.getC_name() %></option>
							<%}%>
									</select>
	                                    <span class="help-block">
	                                        <strong id = "login_pass_error"></strong>
	                                    </span>
	                            </div>
	                        </div>
	
	                        <div class="form-group">
	                        	<label class="col-md-3 control-label">Category Status :</label>
	                        		<input id = "is_active" type="checkbox" name="remember"> &nbsp;Is Active ..?
	                        </div>
	
	                        <div class="form-group">
	                            <div class="col-md-8 col-md-offset-4">
	                                <button type="submit" class="btn btn-success">
	                                    <i class="fa fa-edit" aria-hidden="true"></i>&nbsp;Edit
	                                </button>
	
	                                <a class="btn btn-link" href="{{ route('password.request') }}">
	                                    Forgot Your Password?
	                                </a>
	                            </div>
	                        </div>
	                    </form>
	                </div><!--end of card body  -->
           		 </div><!--end of card  -->
	        </div>
	        <div class="modal-footer">
       			 	<button type="button" class="btn btn-danger float-left" data-dismiss="modal">Close</button>
      		</div>
		</div>
	</div>
</div>
  <!-- End edit modal  -->
  <script type="text/javascript" src="./views/modules/category_master/js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>