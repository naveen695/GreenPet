
 <style>

.container .jumbotron, .container-fluid .jumbotron {
    padding-right: 5px;
    padding-left: 5px;
    padding-top: 10px;
    padding-bottom: 10px;
    
   
}
.form-group{
	margin-bottom: 4px;
	text-align: start;
}
.panel-heading {
    text-align: center;
}
 .fixed-panel {
  min-height: 10px;
  max-height: 455px;
 /*  overflow-y: scroll; */
}
.help-block {
    display: block;
    margin-top: 0px;
    margin-bottom: 0px;
}
</style>

  <div class="jumbotron text-center fixed-panel" style="padding-top: 0px;padding-bottom: 0px;">
    <div class="panel-heading " style="padding: 0px;padding: 0px;"><h4>Upload New Pet details </h4></div>
    <div class="panel-body " style="    padding-top: 0px;  padding-right: 0px;    padding-left: 0px;    padding-bottom: 0px;">
	
	 <form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" enctype="multipart/form-data" action="UplodePetDetails">
  		
  		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="petname1" class="control-label">Name Of Pet</label>
			</div>
  			<div class="col-sm-6">
  				<input  name="petname1" class="form-control" id="petname1" placeholder="Enter Pet Name"  required>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="petDesc" class="control-label">Desc Of Pet</label>
			</div>
  			<div class="col-sm-6">
  				<textarea placeholder="What are you doing right now ?" id="petDesc"  name="petDesc" class="form-control" required></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="address11" class="control-label">Address1(Street/*)</label>
			</div>
  			<div class="col-sm-6">
  				<input name="address11" class="form-control" id="address11" placeholder="Enter Address Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="address21" class="control-label">Address2 (street/*)</label>
			</div>
  			<div class="col-sm-6">
  				<input name="address21" class="form-control" id="address21" placeholder="Enter Address Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		  
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="city1" class="control-label">City</label>
			</div>
  			<div class="col-sm-6">
  				<input name="city1" class="form-control" id="city1" placeholder="Enter city Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="county1" class="control-label">County</label>
			</div>
  			<div class="col-sm-6">
  				<input name="county1" class="form-control" id="county1" placeholder="Enter county Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="zip1" class="control-label">Post Code</label>
			</div>
  			<div class="col-sm-6">
  				<input name="zip1" class="form-control" id="zip1" placeholder="Enter Address Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="country1" class="control-label">Country</label>
			</div>
  			<div class="col-sm-6">
  				<input name="country1" class="form-control" id="country1" placeholder="Enter city Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
					<label for="imageId1" class="control-label ">Pet Image</label>
			</div>
  			<div class="col-sm-6">
    			<input type="file" name="imageId1" class="form-control" id="imageId1" 
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
