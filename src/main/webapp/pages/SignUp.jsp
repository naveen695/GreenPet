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
    <label for="inputEmail" class="control-label">Email</label>
    <input name="inputEmail" pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$" class="form-control" id="inputEmail" placeholder="Enter Email" data-error="Enter valid Email" required>
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
    <label for="inputFirstName" class="control-label">First Name</label>
    <input pattern="[A-Za-z0-9]{1,20}" name="inputFirstName" class="form-control" id="inputFirstName" placeholder="Enter First Name" data-error="First name should not be null. It should be less than 20 characters. Use only A-Z, a-z, 0-9 charecters" required>
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
    <label for="inputLastName" class="control-label">Last Name</label>
    <input pattern="[A-Za-z0-9]{1,20}" name="inputLastName" class="form-control" id="inputLastName" placeholder="Enter Last Name" data-error="last name should not be null. It should be less than 20 characters. Use only A-Z, a-z, 0-9 charecters" data-toggle="tooltip" data-placement="right" required>
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="control-label">Password</label>
    <input type="password" pattern="[A-Za-z0-9@#$%!^&*]{6,30}" name="inputPassword" class="form-control" id="inputPassword" placeholder="Enter Password" data-error="Password should not be null. It should be greater than 6 and less than 30 characters . Use only A-Z, a-z, 0-9, @ # $ % ! ^ & * charecters" required>
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
    <label for="inputPassword1" class="control-label">Confirm Password</label>
    <input type="password" name="inputPassword1" class="form-control" id="inputPassword1" data-match="#inputPassword" placeholder="Enter Password Again" data-error="It should not be null and should match with above password" required>
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
      <button style="width:100%" type="submit" class="btn btn-default btn-primary">Register</button>
  </div>
</form>
</div>
        <div class="modal-footer">
        </div>
      </div>
    </div>  
    </div>