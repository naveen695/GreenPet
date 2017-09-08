<%@ include file="/pages/header.jsp" %>

 <H3>Welcome to ....</H3>
 <br/>
  	 
	<div class="well" style="background-color:white">
   		<div class="row">
   		
    		<div class="col-sm-6" >
    			<form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" action="Contact">
  					<div class="form-group">
  						<div class="col-sm-4">	
  							<label for="inputEmailID" class="control-label col-sm-1" style="padding-left: 0px;">Your Email </label>
  						</div>
  						<div class="col-sm-8">
    						<input name="inputEmailID" pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$" class="form-control" id="inputEmailID" placeholder="Enter Your Email" data-error="Enter valid Email" required>
  							<div class="help-block with-errors"></div>
  						</div>
 					</div>
 					<div class="form-group">
  						<div class="col-sm-4">
  							<label for="inputFirstName1" class="control-label col-sm-1" style="padding-left: 0px;">Name </label>
						</div>
  						<div class="col-sm-8">
  							<input pattern="[A-Za-z0-9]{1,20}" name="inputFirstName1" class="form-control" id="inputFirstName1" placeholder="Enter Name" data-error="First name should not be null. It should be less than 20 characters. Use only A-Z, a-z, 0-9 charecters" required>
  							<div class="help-block with-errors"></div>
						</div>
					</div>
			  		<div class="form-group">
  						<div class="col-sm-4">
    						<label for="mobilenumber1" class="control-label" style="padding-left: 0px;">MobileNumber </label>
						</div>
  						<div class="col-sm-8">
    						<input  name="mobilenumber1" class="form-control" id="mobilenumber1" data-match="#mobilenumber1" placeholder="Enter mobile number" data-error="It should be number." required>
    						<div class="help-block with-errors"></div>
  						</div>
  					</div>
  		
  		
  					<div class="form-group">
    					<div class="col-sm-4"><label for="heading" class="control-label col-sm-1" style="padding-left: 0px;">Title </label></div>
  							<div class="col-sm-8">
    						<input type="text" name="heading" class="form-control" id="heading" placeholder="Enter Title " required="required" ></textarea>
 						</div>
  					</div>
  					
  					<div class="form-group">
    					<div class="col-sm-4"><label for="comments" class="control-label col-sm-1" style="padding-left: 0px;">Comments </label></div>
  							<div class="col-sm-8">
    						<textarea name="comments" class="form-control" id="comments" placeholder="Enter Comments " required="required" ></textarea>
 						</div>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-offset-1 col-sm-2"></div>
      					<div class="col-sm-offset-1 col-sm-6">
      						<button type="submit" class="btn btn-success btn-primary">Submit</button>
 						</div>
 					</div>
				</form>
	   		</div>

    		<div class="col-sm-6">
	            <div>
    	            <div class="panel panel-default">
        	            <div class="text-center header" style="color: green;">
        	            	Our Office Address<br/><br/>
        	            </div>
            	       
						<div style="color: red;">
						Name :	Inperta Technologies<br/><br/>
						
						Place : London, United Kingdom<br/><br/>
						
						contact :  +44 845 094 0053 <br/><br/>
 						</div>
	            	</div>
        		</div>
    		</div>
    		
    	</div>
	</div>








</div>
<div class="col-sm-4 sidenav">


    </div>
  </div>
</div>    
<%@ include file="/pages/footer.jsp" %>

