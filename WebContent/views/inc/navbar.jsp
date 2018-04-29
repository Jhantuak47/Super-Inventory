<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<div class="container-fluid">
		<ul class = "nav navbar-nav navbar-left">
		    <a class="navbar-brand" href="#">Inventory</a>
		   <% if (session.getAttribute("name") == null) { %>
			   		<li class="nav-item"><a class = "nav-link" href='/'><i class = "fa fa-home"></i>&nbsp;Home</a></li>	 
			   <% } else {%>
   					<li class="nav-item"><a class = "nav-link" href='../views/dashboard.jsp'><i class = "fa fa-home"></i>&nbsp;Home</a></li>	  
			<% } %>
			 <li class="nav-item"><a class = "nav-link" href='/services'>Service</a></li>
			 <li class="nav-item"><a class = "nav-link" href='/about'>About</a></li>
			 <li class="nav-item"><a class = "nav-link" href='/post'>Blog</a></li> 
		</ul>
	      <!-- Authentication Links -->
		<ul class = "nav navbar-nav navbar-right">
			<% if (session.getAttribute("name") == null) { %>
		          <li align= "left" class="nav-item"><a class = "nav nav-link" href="#"><i class = "fa fa-user"></i>&nbsp;Login</a></li>
		          <li class="nav-item"><a href="#" class = "nav-link"><i class = "fa fa-user"></i>&nbsp;Register</a></li>
		    <% } else {%>
		    	  <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Action
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			          <a class="dropdown-item" href="#">Dash Board</a>
			          <a class="dropdown-item" href="#">Another action</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="../logoutMe">Logout</a>
			        </div>
			      </li>
		    	  <li class="nav-item active"><a href="#" class = "nav-link"><i class = "fa fa-user"></i>&nbsp;<%= session.getAttribute("name") %></a></li>
		    	  
		    <% } %>
		</ul>
	</div>
</nav><br/>


