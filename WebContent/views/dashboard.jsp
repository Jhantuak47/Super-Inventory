<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
  <div class="row">
	    <div class ="col-md-3">
			<div class="card mx-auto" style="width:15rem">
			  <img class="card-img-top mx-auto" style = "width:60%;" src="../images/user.png" alt="Card image">
			  <div class="card-body">
			    <h4 class="card-title">Profile info</h4>
			    <p class="card-text"><i class = "fa fa-user"></i>&nbsp;<%=session.getAttribute("name") %></p>
			    <p class="card-text"><i class = "fa fa-user"></i>&nbsp;<%if(session.getAttribute("is_admin").equals(true)){%> Admin <%}else %> User </p>
			    <p class="card-text"><i class = "fa fa-clock-o"></i>&nbsp;Last Login :<br> <%= session.getAttribute("last_login") %> am</p>
			    <a href="#" class="btn btn-primary"><i class = "fa fa-edit"></i>&nbsp;Edit Profile</a>
			  </div>
		  </div>
	  </div>
		<jsp:include page="<%=\"layouts/admin_jumbotron.jsp\"%>"/>		
  </div>
  <jsp:include page="<%=\"layouts/admin_modules.jsp\"%>"/>
<jsp:include page="<%=\"modules/product_master/templets/add_product.jsp\"%>"/>
<jsp:include page="<%=\"modules/brands/templets/add_brand.jsp\"%>"/>
<jsp:include page="<%=\"modules/category_master/templets/add_cat.jsp\"%>"/>
<script type="text/javascript" src="js/main.js"></script>
<jsp:include page="<%=\"inc/footer.jsp\"%>"/>