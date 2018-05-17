<jsp:include page="<%=\"/views/layouts/layout.jsp\"%>"/>
	<div class="container">
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
			<p>
				<a class="btn btn-primary btn-lg" role="button" data-toggle="modal" data-target="#loginModal">Login</a>
				<a class="btn btn-success btn-lg" role="button" data-toggle="modal" data-target="#registerModal">Register</a>
			</p>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/index.js"></script>
  
  <!-- login modal -->
<div id="loginModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
	        <div class="modal-body">
	          <div class="panel panel-primary">
                <div class="panel-heading">Login</div>

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
       			 	<button type="button" class="btn btn-danger btn-lg pull-left" data-dismiss="modal">Close</button>
      			</div>
	        </div>
		</div>
	</div>
</div>
<!-- end of login modal -->
<!-- registration modal -->
<button  type="button" class="btn btn-primary " data-toggle="modal" data-target="#registerModal">
</button>
<div id="registerModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg modal-primary">
		<div class="modal-content">
	        <div class="modal-body">
	          <div class="panel panel-primary">
                <div class="panel-heading">Registeration</div>

                <div class="panel-body">
                	<!-- Alert -->
                	<div class="alert alert-danger alert-dismissible" id="register_modelError" style = "display:none;">
					 	<button type="button" class="close" data-dismiss="alert">&times;</button>
					    <strong>Error !</strong>&nbsp;<span id = "errorMessage"></span>
					  </div>
					 <!-- End alert -->
                    <form class="form-horizontal" method="POST" id = "register_form" action="../registration">
						<div class="form-group">
                            <label for="name" class="col-md-4 control-label">Name</label>

                            <div class="col-md-6">
                                <input id="name" type="text" class="form-control" name="name" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-md-4 control-label">E-Mail Address</label>

                            <div class="col-md-6">
                                <input id="email" type="email" class="form-control" name="email" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="mobile" class="col-md-4 control-label">Mobile</label>

                            <div class="col-md-6">
                                <input id="mobile" type="number" class="form-control" name="mobile" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-md-4 control-label">Password</label>

                            <div class="col-md-6">
                                <input id="password" type="password" class="form-control" name="password" required>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="password-confirm" class="col-md-4 control-label">Confirm Password</label>

                            <div class="col-md-6">
                                <input id="password-confirm" type="password" class="form-control" name="password_confirmation" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="address" class="col-md-4 control-label">Address</label>

                            <div class="col-md-6">
                                <input id="address" type="text" class="form-control" name="address" required autofocus>
                                    <span class="help-block">
                                        <strong></strong>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-success" onclick = "registration(event);">
                                    Register
                                </button>
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

</body>
<jsp:include page="<%=\"inc/footer.jsp\"%>"/>
  <script type="text/javascript" src="js/index.js"></script>