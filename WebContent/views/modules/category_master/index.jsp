<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.superInvent.POJO.CategoryMaster"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
            <div class="card mx-auto" style="max-width: 55rem;">
                <div class="card-header" text-white" style="background-color:#1e90ff;">
                     <div class="row">
                     	<div class="col-md-3">
	                      Welcome to Category List
	                     </div>
	                     <div class="col-md-5">
	                         <form action="" method="" autocomplete="on">
	                                 <div class="input-group">
	                                   <input type="text" class = "form-control-sm" name="searchinput" id="searchinput" size="30%" class="" placeholder="Search Category" list ="users"
	                                   style="background-color : #d1d1d1;"> <span class="input-group-btn"></span>
	                                   <div class="clearfix"></div>
	                                   &nbsp;<button class="btn btn-success btn-sm" type="submit" style="display:;" onclick="search(event)" id="search_icon">
	                                     <i class="fa fa-search"></i></span>
	                                   </button>
	                                   <button style="display:none ;font-size:20px; margin-top: 1px;" id="filter_button" class="btn-danger" onclick="removeFilter(event);"><i class="fa fa-remove"></i></button>
	                                 </div>             
	                         </form>
	                     </div>
	                     <div class="col-md-4" style="">
	                     <button style="margin-left:60px;display:none;font-size:16px;" id="delete-row" class="btn-danger" onclick="deleteCategoryOnCheck();"><i class='fa fa-trash-o'></i>&nbsp;Delete</button><!--delete button -->
	                     <a href = "#" onclick = "add();" class="btn btn-success btn-sm pull-right"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Add</a>
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
                            <th>Category</th>
                            <th>Type</th>
                            <th style = "text-align:center;">Status</th>
                            <th colspan="2" style = "text-align:center;padding-left:130px;" >Action</th>
                          </tr>
                        </thead>
                        <tbody id = "cat_table">
                           <% 
                            List<CategoryMaster> categoryLists =  new ArrayList<CategoryMaster>();
  									categoryLists = (List<CategoryMaster>)request.getAttribute("categoryLists");
  									 int page_no = (Integer)request.getAttribute("page_no");
  									int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
  									 int i = ( (page_no - 1) * numberOfResultPerPage ) + 1;
	                           for(CategoryMaster List:categoryLists){%>
		               				
                                <tr id="<%= List.getId() %>">
                                    <td><input type="checkbox"  onclick = "checkbox_on_click()" name="record"></td>
                                    <td><%= i %></td>
                                    <td><%= List.getC_name() %></td>
                                    <td><% if(List.getParentCategory() == null){%>root<%}else{ out.print(List.getParentCategory()); } %></td>
                                    <td>
                                        <%if(List.getC_status() == 1){ %>
                                        <a href = "#" onclick="Update_category_status(<%=List.getId() %>, <%= page_no %>,<%= List.getC_status() %> );" class="btn btn-danger btn-sm pull-right" style="margin-right:10px;">Deactivate</a>
                                        <%}else{%>
                                        <a href = "#" onclick="Update_category_status(<%=List.getId() %>, <%= page_no %>, <%= List.getC_status() %> );"  class="btn btn-success btn-sm pull-right" style="margin-right:10px;">Activate</a>
                                     	<%}%>
                                     </td>
                                    <td><a onclick="edit('<%=List.getId() %>', '<%=List.getC_name()%>', '<%=List.getParent_id()%>',<%= page_no %>, '<%=List.getC_status() %>');"  class="btn btn-primary btn-sm active pull-right" style="margin-left:100px"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;Edit</a></td>
                                    <td><a class = "btn btn-danger active btn-sm" onclick = "delete_modal('<%= List.getId() %>', '<%= List.getC_name() %>', <%= page_no %>);">Delete</a></td>
                                </tr>
                              <%i++; }%>
                        </tbody>
                    </table>
                </div><!--pannel body -->
                <div class="card-footer" id="pannel_footer">
                   <div align="center" id = "footer"> <%= request.getAttribute("pagination") %> </div> 
                </div>
    </div><!--end of row  -->	
  <jsp:include page="<%=\"templets/edit.jsp\"%>"/>
  <jsp:include page="<%=\"templets/add_cat.jsp\"%>"/>
  <jsp:include page="<%=\"templets/delete.jsp\"%>"/>
  <script type="text/javascript" src="./views/modules/category_master/js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>