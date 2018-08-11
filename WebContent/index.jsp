<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
<link rel = "stylesheet" href = "/Super-Inventory/css/index.css" >
		<!-- Alerts  -->
			<div class="alert alert-success alert-dismissible" id = "success" style = "display:none;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
			    <strong>Congratulation!</strong>&nbsp;You are successfully registered, please login !.
			 </div>
			 <div class="alert alert-danger alert-dismissible" id="error" style = "display:none;">
			 	<button type="button" class="close" data-dismiss="alert">&times;</button>
			    <strong>Error !</strong>&nbsp; Fail to register, Please try again !.
			  </div>
		<!-- End of Alerts -->
		<div class="jumbotron text-center">
			<h2>Welcome to Super Inventry !!</h2>
			<p>This is our home page....</p>
			<p class="activity-buttons">
				<a class="btn btn-primary btn-lg" role="button" data-toggle="modal" data-target="#loginModal">Login</a>
				<a class="btn btn-success btn-lg" role="button" onclick = "open_reg_modal();">Register</a>
			</p>
		</div>
	</div>
  <script type="text/javascript" src="js/index.js"></script>
  <!-- login modal -->
<div id="loginModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
	        <div class="modal-body">
	          <div class="panel panel-primary">
                <div class="panel-heading">Login</div>
				<hr>
                <div class="panel-body">
                    <form class="form-horizontal" method="POST" action="./login"  onsubmit="return login_form_validation()">

                        <div class="form-group">
                            <label for="email" class="col-md-4 control-label">E-Mail Address</label>

                            <div class="col-md-6">
                                <input id="login_email" type="email" class="form-control" name="login_email" required autofocus>
                                    <span class="help-block">
                                        <strong id = "login_email_error"></strong>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-md-4 control-label">Password</label>

                            <div class="col-md-6">
                                <input id="login_password" type="password" class="form-control" name="login_password" required>
                                    <span class="help-block">
                                        <strong id = "login_pass_error"></strong>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="remember"> Remember Me
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-primary">
                                    Login
                                </button>

                                <a class="btn btn-link" href="{{ route('password.request') }}">
                                    Forgot Your Password?
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
              <div class="modal-footer">
       			 	<button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
      			</div>
	        </div>
		</div>
	</div>
</div>
<!-- end of login modal -->
<!-- registration modal -->
<button id = "register_btn" data-toggle="modal" data-target="#registerModal" class="d-none"></button>
<div id="registerModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg modal-primary">
		<div class="modal-content">
			 <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Registeration</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
	        <div class="modal-body">		
                	<!-- Alert -->
                	<div class="alert alert-danger alert-dismissible" id="register_modelError" style = "display:none;">
					 	<button type="button" class="close" data-dismiss="alert">&times;</button>
					    <strong>Error !</strong>&nbsp;<span id = "errorMessage_register"></span>
					  </div>
					 <!-- End alert -->
                    <form class="form-horizontal" method="POST" id = "register_form" action="../registration">
 
						<div class="form-row">
						 	<div class = "form-group col-md-6">
						 		  <label for="name">Name</label>
                                <input id="name" type="text" class="form-control" name="name" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
						 	</div>
						 		<div class = "form-group col-md-6">
						 		  <label for="name">Mobile</label>
                                <input id="mobile" type="number" class="form-control" name="mobile" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
						 	</div>	
                        </div>
						
						<div class="form-group">
						 		
                          
                        </div>

                        <div class="form-row">
                        
	                          <div class = "form-group col-md-6">
	                          	<label for="password">Password</label>
	                          	<input id="password" type="password" name="password" class = "form-control" required>
	                                    <span class="help-block">
	                                        <strong></strong>
	                                    </span>
	                          </div>
	                          <div class = "form-group col-md-6">
	                          <label for="password-confirm">Confirm Password</label>
	                                <input id="password-confirm" class="form-control" type="password" name="password_confirmation" required>
	                          </div>

                        </div>
                         <div class="form-row">
                        
	                          <div class = "form-group col-md-6">
	                          	<label for="Address">Address</label>
	                          	 <input id="address" type="text" class="form-control" name="address" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
	                          </div>
	                          <div class = "form-group col-md-6">
	                           <label for="email">E-Mail Address</label>
                                  <input id="email" type="email" name="email" class="form-control" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
                              </div>
                        </div>

                        
                          <div class = "clearfix"></div>
                    </form>
              <div class="modal-footer">
                                <button type="submit" class="btn btn-success pull-right" onclick = "registration(event);">
                                    Register
                                </button>
                        
       			 	<button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
      			</div>
	        </div>
		</div>
	</div>
</div>
<jsp:include page="<%=\"views/inc/footer.jsp\"%>"/>
</div>
</div>