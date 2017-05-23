<div class="modal fade" id="signUp" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Sign Up</h4>
        </div>
        <div class="modal-body">
 
 <form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" action="SignUpServlet">
  <div class="form-group">
  			<label for="inputEmail" class="control-label col-sm-1">Email</label>
 		<div class="col-sm-3"></div>
  		<div class="col-sm-6">
    		<input name="inputEmail" pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$" class="form-control" id="inputEmail" placeholder="Enter Email" data-error="Enter valid Email" required>
  			<div class="help-block with-errors"></div>
  		</div>
  </div>
  <div class="form-group">
  			<label for="inputFirstName" class="control-label col-sm-1">First Name</label>
			<div class="col-sm-3"></div>
  		<div class="col-sm-6">
  			<input pattern="[A-Za-z0-9]{1,20}" name="inputFirstName" class="form-control" id="inputFirstName" placeholder="Enter First Name" data-error="First name should not be null. It should be less than 20 characters. Use only A-Z, a-z, 0-9 charecters" required>
  			<div class="help-block with-errors"></div>
		</div>
		</div>
		<div class="form-group">
    		<label for="inputLastName" class="control-label col-sm-1">Last Name</label>
    		<div class="col-sm-3"></div>
  			<div class="col-sm-6">
    			<input pattern="[A-Za-z0-9]{1,20}" name="inputLastName" class="form-control" id="inputLastName" placeholder="Enter Last Name" data-error="last name should not be null. It should be less than 20 characters. Use only A-Z, a-z, 0-9 charecters" data-toggle="tooltip" data-placement="right" required>
  	 			<div class="help-block with-errors"></div>
 			</div>
  		</div>
  		<div class="form-group">
  			<div class="col-sm-1">
    			<label for="inputPassword1" class="control-label">MobileNumber</label>
			</div>
     		<div class="col-sm-3"></div>
  			<div class="col-sm-6">
    			<input  name="mobilenumber" class="form-control" id="mobilenumber" data-match="#mobilenumber" placeholder="Enter mobile number" data-error="It should be number." required>
    			<div class="help-block with-errors"></div>
  			</div>
  		</div>
  		<div class="form-group">
    		<label for="inputPassword" class="control-label col-sm-1">Password</label>
    		<div class="col-sm-3"></div>
  			<div class="col-sm-6">
    			<input type="password" pattern="[A-Za-z0-9@#$%!^&*]{6,30}" name="inputPassword" class="form-control" id="inputPassword" placeholder="Enter Password" data-error="Password should not be null. It should be greater than 6 and less than 30 characters . Use only A-Z, a-z, 0-9, @ # $ % ! ^ & * charecters" required>
    			<div class="help-block with-errors"></div>
  			</div>
  		</div>
  		<div class="form-group">
  			<div class="col-sm-1">
    			<label for="inputPassword1" class="control-label">Confirm Password</label>
			</div>
     		<div class="col-sm-3"></div>
  			<div class="col-sm-6">
    			<input type="password" name="inputPassword1" class="form-control" id="inputPassword1" data-match="#inputPassword" placeholder="Enter Password Again" data-error="It should not be null and should match with above password" required>
    			<div class="help-block with-errors"></div>
  			</div>
  		</div>
  		<div class="form-group">
  			<div class="col-sm-offset-1 col-sm-2">
      		</div>
      		<div class="col-sm-offset-1 col-sm-6">
      			<button type="submit" class="btn btn-success btn-primary">Register</button>
 			</div>
 		</div>
	</form>
</div>
        <div class="modal-footer">
        </div>
      </div>
    </div>  
    </div>
    
    <script type="text/javascript"> 
     $(document).ready( function () {
    		   $('#signInMessage').modal('show');
    	});
 </script>
<c:if test="${signupresponce.statusMessage != null}">
 <div class="modal fade" id="signInMessage" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
    
            <div class="modal-body">
    				<h3>${signupresponce.statusMessage}</h3>
    		</div>
    		
        <div class="modal-footer">
          <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
        </div>
	        </div>
       </div>
      </div>
  
</c:if>
    