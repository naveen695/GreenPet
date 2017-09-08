<%@ include file="/pages/header.jsp" %>
<script type="text/javascript">


$(document).ready(function () {
	$("td", this).on("click", function () {
	    var tds = $(this).parents("tbody").find("td");
	    $.each(tds, function (i, v) {
	        $($(".data-form1 input")[i]).val($(v).text());
	    });
	});
	});	
function setIdValue(){
	var path = document.getElementById("petId2").value;

	document.getElementById("petId1").value=path;
	alert(document.getElementById("petId1").value);
}


</script>
	<c:forEach var="user" items="${petDeails}">
		<c:if test="${user.id == images}">
			<div class="well" style="min-height: 500px;">
				<div class="col-sm-12">
        	 	  
        	   		<div class="col-sm-4">  
        				<div class="panel-heading "><h4> Pet Details </h4></div>
        			         <div class="pull-left image">
								<a href="#" data-image="small/Image" data-zoom-image="large/Image"> 
									<img src="Image/${user.petname}" height="100px" width="100px" class="img-circle avatar" ></img>
								</a>
                		    </div>
                    </div>
                    <div class="col-sm-8">  
	   					 <table class="table">
    						<thead >
     							 <tr class="warning">
       							 	<th># Column Name</th>
       								<th># Values</th>
     							 </tr>
    						</thead>
   							<tbody>
   								 
    							<tr class="info">
        							<th>TreeName</th>
       								<td>${user.petname}</td>
      							</tr>
      							<tr class="info">
        							<th>Description Of Pet</th>
       								<td>${user.petDesc}</td>
      								</tr>
      							<tr class="info">
        							<th>Address</th>
       								<td>${user.address1}</td>
      							</tr>
      							<tr class="info">
        							<th>Address2</th>
       								<td>${user.address2}</td>
      							</tr>
      							<tr class="info">
        							<th>City</th>
       								<td>${user.city}</td>
      							</tr>
      							<tr class="info">
        							<th>County</th>
       								<td>${user.county}</td>
      							</tr>
      							<tr class="info">
        							<th>Country</th>
       								<td>${user.country}</td>
      							</tr>
      							<tr class="info">
        							<th>Zip</th>
       								<td>${user.zip}</td>
      							</tr>
      							<tr class="info" style="display: none;">
        							<th> id </th>
       								<td>${user.id}</td>
      							</tr>
      							<tr class="warning ">
      								<th>Edit & Upadte</th>	
      								<td>	
      							 
    									<button class="btn btn-primary btn-xs" data-title="Edit"  data-toggle="modal" data-target="#edit" >
    									<span class="glyphicon glyphicon-pencil"></span></button>
    								</td>
    							</tr>
      						</tbody>
      					</table>
                   </div>
                   
                   <form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" enctype="multipart/form-data" action="UploadMultipleImages">
                   <div class="form-group">
						<div class="col-sm-4">
							<label for="imageId2" class="control-label ">Upload more images Pet --> : ${user.petname} </label>
						</div>
  						<div class="col-sm-4">
  							<input name="petId" class="form-control" id="petId" placeholder="Enter city Name" value="${user.id}" type="hidden">
  		
    						<input type="file" name="imageId2" class="form-control" id="imageId2" 
    						placeholder="Upload image . . . " required accept="image/*">
  	 						<div class="help-block with-errors"></div>
 						</div>
 						<div class="col-sm-offset-1 col-sm-4">
      						<button type="submit" class="btn btn-success btn-primary">Submit</button>
 						</div>
	  				</div>
	  				</form>
	  				
  				</div>
			</div>
		</c:if>
	</c:forEach>

	
	
	<div class="modal fade data-form1" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
    	<div class="modal-content">
    	    <div class="modal-header">
       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        		<h4 class="modal-title custom_align" id="Heading">Edit or Update Your pet Detail</h4>
      		</div>
	<div class="modal-body">
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
			  			<label for="inputFirstName" class="control-label">Desc Of Pet</label>
			</div>
  			<div class="col-sm-6">
  				<input  name="petdesc" class="form-control" id="petdesc" placeholder="Enter Pet Name"  required>
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
			  			<label for="inputFirstName" class="control-label">Country</label>
			</div>
  			<div class="col-sm-6">
  				<input name="country" class="form-control" id="country" placeholder="Enter city Name"  required>
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
		
	 
  	 <div class="form-group" style="display: none;">
			<div class="col-sm-5">
			  			<label for="zip" class="control-label"> </label>
			</div>
  			<div class="col-sm-6">
      				<input  name="petId1" class="form-control" id="petId1"  placeholder="Enter Pet Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
			
  		<div class="form-group">
  			<div class="col-sm-offset-1 col-sm-2">
      		</div>
      		<div class="col-sm-offset-1 col-sm-6">
      			<button type="submit" class="btn btn-success btn-primary">
      			Submit
  				</button>
 			</div>
 		</div>
	</form>
      		</div>
       </div>
  	</div>
</div>





</div>
<div class="col-sm-4 sidenav ">
 
		<c:if test="${loginUserDetails.login == true }">
    		<%@ include file="UploadImage.jsp" %>
    	</c:if>
</div>
  </div>
</div>
<%@ include file="/pages/footer.jsp" %>

