<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.superInvent.POJO.BrandMaster"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
	<%  Date dt = new Date();  
			    SimpleDateFormat formatter = new SimpleDateFormat("dd,  MMMM yyyy");
			    List<BrandMaster> brands =  new ArrayList<BrandMaster>();
				brands = (List<BrandMaster>)request.getAttribute("brands");
				 int page_no = (Integer)request.getAttribute("page_no");
				int numberOfResultPerPage = Integer.parseInt(getServletContext().getInitParameter("resultDisplayPerPage"));
				 int i = ( (page_no - 1) * numberOfResultPerPage ) + 1;
	%>
            <div class="card mx-auto" style="max-width: 55rem;">
                <div class="card-header" text-white" style="background-color:#1e90ff;">
                     <div class="row">
                     	<div class="col-md-3">
	                      Welcome to Brand List
	                     </div>
	                     <div class="col-md-5">
	                         <form action="" onsubmit = "return false">
	                                 <div class="input-group">
	                                   <input type="text" class = "form-control-sm" name="searchinput" id="searchinput" size="30%" class="" placeholder="Search Category" list ="users"
	                                   style="background-color : #d1d1d1;"> <span class="input-group-btn"></span>
	                                   <div class="clearfix"></div>
	                                   &nbsp;<button class="btn btn-success btn-sm" type="submit" style="display:;" onclick="search()" id="search_icon">
	                                     <i class="fa fa-search"></i></span>
	                                   </button>
	                                   <button style="display:none ;font-size:20px; margin-top: 1px;" id="filter_button" class="btn-danger" onclick="removeFilter(event);"><i class="fa fa-remove"></i></button>
	                                 </div>             
	                         </form>
	                     </div>
	                     <div class="col-md-4" style="">
	                     <button style="margin-left:60px;display:none;font-size:16px;" id="delete-row" class="btn-danger" onclick="deleteBrandOnCheck();"><i class='fa fa-trash-o'></i>&nbsp;Delete</button><!--delete button -->
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
                            <th>Brand Name</th>
                            <th>Created at</th>
                            <th style = "text-align:center;">Status</th>
                            <th colspan="2" style = "text-align:center;padding-left:130px;" >Action</th>
                          </tr>
                        </thead>
                        <tbody id = "brand_table">
                          <% 
	                         for(BrandMaster brand : brands){%>
		               				
                                <tr id="<%= brand.getId() %>">
                                    <td><input type="checkbox"  onclick = "checkbox_on_click()" name="record"></td>
                                    <td><%= i %></td>
                                    <td><%=brand.getName() %></td>
                                    <td><%= formatter.format(brand.getCreated_at()) %></td>
                                    <td>
                                        <%if(brand.getStatus() == 1){ %>
                                        <a href = "#" onclick="Update_category_status(<%=brand.getId() %>, <%= page_no %>,<%= brand.getStatus() %> );" class="btn btn-danger btn-sm pull-right" style="margin-right:10px;">Deactivate</a>
                                        <%}else{%>
                                        <a href = "#" onclick="Update_category_status(<%=brand.getId() %>, <%= page_no %>, <%= brand.getStatus() %> );"  class="btn btn-success btn-sm pull-right" style="margin-right:10px;">Activate</a>
                                     	<%}%>
                                     </td>
                                    <td><a onclick="edit('<%=brand.getId() %>', '<%=brand.getName() %>', '<%=brand.getStatus() %>',<%= page_no %>, '<%= formatter.format(brand.getCreated_at()) %>');"  class="btn btn-primary btn-sm active pull-right" style="margin-left:100px"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;Edit</a></td>
                                    <td><a class = "btn btn-danger active btn-sm" onclick = "delete_modal('<%= brand.getId() %>', '<%= brand.getName() %>', <%= page_no %>);">Delete</a></td>
                                </tr>
                              <%i++; }%>
                        </tbody>
                    </table>
                </div><!--pannel body -->
                <div class="card-footer" id="pannel_footer">
                   <div align="center" id = "footer"> <%= request.getAttribute("pagination") %> </div> 
                </div>
    </div><!--end of row  -->
	
   <jsp:include page="<%=\"/views/modules/brands/templets/add_brand.jsp\"%>"/>
   <jsp:include page="<%=\"/views/modules/brands/templets/edit.jsp\"%>"/>
   <jsp:include page="<%=\"/views/modules/brands/templets/delete.jsp\"%>"/>
  <script type="text/javascript" src="./views/modules/brands/js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>