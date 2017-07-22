<%@ include file="/pages/header.jsp" %>
 <form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" enctype="multipart/form-data" action="UplodePetDetails">
  		
  		
<c:forEach var="user" items="${petDeails}">
<c:if test="${user.id == images}">
			<div class="well" style="min-height: 400px;">
			
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
        							<td>TreeName</td>
       								<td>${user.petname}</td>
      							</tr>
      							<tr class="info">
        							<td>Address</td>
       								<td>${user.address1}</td>
      							</tr>
      							<tr class="info">
        							<td>Address2</td>
       								<td>${user.address2}</td>
      							</tr>
      							<tr class="info">
        							<td>City</td>
       								<td>${user.city}</td>
      							</tr>
      							<tr class="info">
        							<td>County</td>
       								<td>${user.county}</td>
      							</tr>
      							<tr class="info">
        							<td>Country</td>
       								<td>${user.country}</td>
      							</tr>
      							<tr class="info">
        							<td>Zip</td>
       								<td>${user.zip}</td>
      							</tr>
      						</tbody>
      					</table>
                   		 </div>
                   		 <div class="form-group">
			<div class="col-sm-5">
					<label for="imageId2" class="control-label ">Pet Image</label>
			</div>
  			<div class="col-sm-6">
    			<input type="file" name="imageId2" class="form-control" id="imageId2" 
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
                	</div>
					</div>
		</c:if>

</c:forEach>
    			
		
	</form>
</div>
<div class="col-sm-4 sidenav ">
 
		<c:if test="${loginUserDetails.login == true }">
    		<%@ include file="UploadImage.jsp" %>
    	</c:if>
</div>
  </div>
</div>
<%@ include file="/pages/footer.jsp" %>

