<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container-fluid">
		<ul class = "nav navbar-nav navbar-left">
		    <a class="navbar-brand" href="#">Inventory</a>
		   <% if (session.getAttribute("name") == null) { %>
				   	<li class="nav-item"><a class = "nav-link" href='./'><i class = "fa fa-home"></i>&nbsp;Home</a></li>
					<li class="nav-item"><a class="nav-link" href='./service.jsp'>Service</a></li>
					<li class="nav-item"><a class="nav-link" href='./aboutUS.jsp'>About</a></li>
					<li class="nav-item"><a class="nav-link" href='./post'>Blog</a></li>
				<%
				} else {
			%>
   					<li class="nav-item"><a class = "nav-link" href='/Super-Inventory/views/dashboard.jsp'><i class = "fa fa-home"></i>&nbsp;Home</a></li>
   					<li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Transaction
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			          <a class="dropdown-item" href="/Super-Inventory/views/modules/purchase_master">Purchase Details</a>
			          <a class="dropdown-item" href="/Super-Inventory/views/modules/order_master">Sales Details</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="/Super-Inventory/views/modules/report/">Reports</a>
			        </div>
			      </li>
			      <li class="nav-item dropdown" style = "padding-left:20px;">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Brand
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			          <a class="dropdown-item" href="/superInvent/list_brand">List Brands</a>
			          <a class="dropdown-item" href="#">Add Brands</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="#">Reports</a>
			        </div>
			      </li>
			      <li class="nav-item dropdown" style = "padding-left:20px;">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Category
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			          <a class="dropdown-item" href="/superInvent/list_category">List Category</a>
			          <a class="dropdown-item" href="#">Add Category</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="#">Reports</a>
			        </div>
			      </li>
			      <li class="nav-item dropdown" style = "padding-left:20px;">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			         	Product
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			          <a class="dropdown-item" href="/superInvent/list_product">List Products</a>
			          <a class="dropdown-item" href="#">Add Products</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="#">Reports</a>
			        </div>
			      </li>  
			<% } %>
			
		</ul>
	      <!-- Authentication Links -->
		<ul class = "nav navbar-nav navbar-right">
			<% if (session.getAttribute("name") == null) { %>
		          <li align= "left" class="nav-item"><a class = "nav nav-link" href="#"><i class = "fa fa-user"></i>&nbsp;Login</a></li>
		          <li class="nav-item"><a href="#" class = "nav-link"><i class = "fa fa-user"></i>&nbsp;Register</a></li>
		    <% } else {%>
		    	  <li class="nav-item dropdown" style = "padding-right:20px;">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Action
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			          <a class="dropdown-item" href="/superInvent/views/dashboard.jsp">Dash Board</a>
			          <a class="dropdown-item" href="#">Another action</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="../logoutMe">Logout</a>
			        </div>
			      </li>
		    	  <li class="nav-item active" style = "padding-right:5px;"><a href="#" class = "nav-link"><i class = "fa fa-user"></i>&nbsp;&nbsp;<%= session.getAttribute("name") %></a></li>
		    	  
		    <% } %>
		</ul>
	</div>
</nav><br/>


