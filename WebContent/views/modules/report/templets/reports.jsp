<% if(session.getAttribute("name") == null) response.sendRedirect("./"); %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
<div class="row">
	<% SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yy, hh:mm"); %>
	<div class="col-md-10 mx-auto">
		<div class="card" style = "box-shadow:0 0 15px 0 lightgrey;">
			<div class="card-header">
				<h5>Reports</h5>
			</div>
			<div class="card-body primary">
				<div class="card" style = "box-shadow:0 0 15px 0 lightgrey;">
					<div class="card-header" data-toggle="collapse" data-target="#demo">Featured</div>
					<div class="card-body collapse" id="demo">
						<h5 class="card-title">Special title treatment</h5>
						<p class="card-text">With supporting text below as a natural
							lead-in to additional content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
				<br>
				<div class="card">
					<div class="card-header" data-toggle="collapse" data-target="#demo">Featured</div>
					<div class="card-body collapse" id="demo">
						<h5 class="card-title">Special title treatment</h5>
						<p class="card-text">With supporting text below as a natural
							lead-in to additional content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>
			<!-- primary cards body end -->
		</div>
		<!-- primary cards primary end -->
	</div>
</div>
  <script type="text/javascript" src="../js/main.js"></script>
<jsp:include page="<%=\"/views/inc/footer.jsp\"%>"/>