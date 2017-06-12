<!-- <div class="jumbotron">
  <div class="row">
    <div class="col-sm-12">
     Upload new Images For Pet
    </div>
    <div class="col-sm-12">
      test
    </div>
    <div class="col-sm-12">
      test
    </div>
  </div>
</div>
 -->
 <style>

.container .jumbotron, .container-fluid .jumbotron {
    padding-right: 5px;
    padding-left: 5px;
    padding-top: 10px;
    padding-bottom: 10px;
    
   
}
 .fixed-panel {
  min-height: 10px;
  max-height: 455px;
  overflow-y: scroll;
}</style>

  <div class="jumbotron text-center fixed-panel">
    <div class="panel-heading "><h4>Upload New Pet details </h4></div>
    <div class="panel-body " style="    padding-top: 0px;  padding-right: 0px;    padding-left: 0px;    padding-bottom: 0px;">
	
	 <form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" action="UpdatePetDetails">
  		
  		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="inputFirstName" class="control-label">Name Of Pet</label>
			</div>
  			<div class="col-sm-6">
  				<input  name="petname" class="form-control" id="petname" placeholder="Enter Pet Name"  required>
			</div>
		</div>
		
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="address1" class="control-label">Address1 (house/street)</label>
			</div>
  			<div class="col-sm-6">
  				<input name="address1" class="form-control" id="address1" placeholder="Enter Address Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="address2" class="control-label">Address2 (street/*)</label>
			</div>
  			<div class="col-sm-6">
  				<input name="address2" class="form-control" id="address2" placeholder="Enter Address Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		  
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="city" class="control-label">City</label>
			</div>
  			<div class="col-sm-6">
  				<input name="city" class="form-control" id="city" placeholder="Enter city Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="county" class="control-label">County</label>
			</div>
  			<div class="col-sm-6">
  				<input name="county" class="form-control" id="county" placeholder="Enter county Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="zip" class="control-label">Post Code</label>
			</div>
  			<div class="col-sm-6">
  				<input name="zip" class="form-control" id="zip" placeholder="Enter Address Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="inputFirstName" class="control-label">Country</label>
			</div>
  			<div class="col-sm-6">
  				<input name="country" class="form-control" id="country" placeholder="Enter city Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		
		<div class="form-group">
			<div class="col-sm-5">
					<label for="inputFirstName" class="control-label ">Pet Image</label>
			</div>
  			<div class="col-sm-6">
    			<input type="file" name="imageId" class="form-control" id="imageId" 
    			placeholder="Upload image . . . " required accept="image/*">
  	 			<div class="help-block with-errors"></div>
 			</div>
  		</div>
  	
  	
  	
  		<div class="form-group">
  			<div class="col-sm-offset-1 col-sm-2">
      		</div>
      		<div class="col-sm-offset-1 col-sm-6">
      			<button type="submit" class="btn btn-success btn-primary">Submit</button>
 			</div>
 		</div>
	</form>
    
    </div>
  </div>
