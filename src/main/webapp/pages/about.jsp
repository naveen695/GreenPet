<%@ include file="/pages/header.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
	$("td", this).on("click", function () {
	    var tds = $(this).parents("tr").find("td");
	    $.each(tds, function (i, v) {
	        $($(".data-form input")[i]).val($(v).text());
	    });
	});
	});	


$(document).ready(function () {
	$("td", this).on("click", function () {
	    var tds = $(this).parents("tr").find("td");
	    $.each(tds, function (i, v) {
	        $($(".data-form1 input")[i]).val($(v).text());
	    });
	});
	});	



</script>
<c:if test="${loginUserDetails.login != true }">
please login for details
</c:if>
<c:if test="${loginUserDetails.login == true }">
<div>
  <div class="row">
    <div class="col-sm-9">
		<div>
        <h4>Your Details</h4>
        <div class="table-responsive">
              <table id="mytable" class="table table-bordred table-striped">
                <thead>
                	<th>First Name</th>
                   	<th>Last Name</th>
                    <th>Email</th>
                    <th>Contact No</th>
                    <th>Edit</th>
                </thead>
   				<tbody>
			    	<tr>
				    	<td id="2">${loginUserDetails.userFirstName}</td>
    					<td>${loginUserDetails.userLastName}</td>
   						<td>${loginUserDetails.email}</td>
    					<td>${loginUserDetails.mobilenumber}</td>
    					<td>	
    							<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#editDetaila" >
    							<span class="glyphicon glyphicon-pencil"></span></button>
    					</td>
    				</tr>
				</tbody>        
		</table>
        </div>
	</div>
		
    </div>
  </div>


<div class="row">
    <div class="col-sm-9">
    
        <h4>Your  Pet Details </h4>
        <div class="table-responsive">
      <table id="mytable1" class="table table-striped table-bordered">
               <thead>
               <tr>
                	<th>Name of Pet</th>
                    <th>Address1 (house/street)</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>Country</th>
                    <th>Pet Image</th>
                    <th>edit</th>
                	</tr>
                </thead>
   				<tbody id="mytab">
			    	<tr>
				    	<td>Alian </td>
    					<td>Marathahalli, 560037</td>
    					<td>Bangalore </td>
    					<td>KA</td>
   						<td>60037</td>
   						<td>india</td>
    					<td>----</td>
    					<td>	
    							<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" >
    							<span class="glyphicon glyphicon-pencil"></span></button>
    					</td>
    				</tr>

  					<tr>
				    	<td>Alian </td>
    					<td>Marathahalli, 560037</td>
    					<td>Bangalore </td>
    					<td>KA</td>
   						<td>60037</td>
   						<td>india</td>
    					<td>----</td>
    					<td>	
    							<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" >
    							<span class="glyphicon glyphicon-pencil"></span></button>
    					</td>
    				</tr>
				  	<tr>
				    	<td>Alian </td>
    					<td>Marathahalli, 560037</td>
    					<td>Bangalore </td>
    					<td>KA</td>
   						<td>60037</td>
   						<td>india</td>
    					<td>----</td>
    					<td>	
    							<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" >
    							<span class="glyphicon glyphicon-pencil"></span></button>
    					</td>
    				</tr>


				</tbody>        
		</table>

 <script type="text/javascript">
 $.fn.pageMe = function(opts){
	    var $this = this,
	        defaults = {
	            perPage: 7,
	            showPrevNext: false,
	            hidePageNumbers: false
	        },
	        settings = $.extend(defaults, opts);
	    var listElement = $this;
	    var perPage = settings.perPage; 
	    var children = listElement.children();
	    var pager = $('.pager');
	    if (typeof settings.childSelector!="undefined") {
	        children = listElement.find(settings.childSelector);
	    }
	    if (typeof settings.pagerSelector!="undefined") {
	        pager = $(settings.pagerSelector);
	    }
	    var numItems = children.length;
	    var numPages = Math.ceil(numItems/perPage);
	    pager.data("curr",0);
	    if (settings.showPrevNext){
	        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
	    }
	    var curr = 0;
	    while(numPages > curr && (settings.hidePageNumbers==false)){
	        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
	        curr++;
	    }
	    if (settings.showPrevNext){
	        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
	    }
	    pager.find('.page_link:first').addClass('active');
	    pager.find('.prev_link').hide();
	    if (numPages<=1) {
	        pager.find('.next_link').hide();
	    }
	  	pager.children().eq(1).addClass("active");
	    children.hide();
	    children.slice(0, perPage).show();
	    pager.find('li .page_link').click(function(){
	        var clickedPage = $(this).html().valueOf()-1;
	        goTo(clickedPage,perPage);
	        return false;
	    });
	    pager.find('li .prev_link').click(function(){
	        previous();
	        return false;
	    });
	    pager.find('li .next_link').click(function(){
	        next();
	        return false;
	    });
	    function previous(){
	        var goToPage = parseInt(pager.data("curr")) - 1;
	        goTo(goToPage);
	    }
	    function next(){
	        goToPage = parseInt(pager.data("curr")) + 1;
	        goTo(goToPage);
	    }
	    function goTo(page){
	        var startAt = page * perPage,
	            endOn = startAt + perPage;
	        children.css('display','none').slice(startAt, endOn).show();
	        if (page>=1) {
	            pager.find('.prev_link').show();
	        }
	        else {
	            pager.find('.prev_link').hide();
	        }
	        if (page<(numPages-1)) {
	            pager.find('.next_link').show();
	        }
	        else {
	            pager.find('.next_link').hide();
	        }
	        pager.data("curr",page);
	      	pager.children().removeClass("active");
	        pager.children().eq(page+1).addClass("active");
	    }
	};
	$(document).ready(function(){
	  $('#mytab').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:3});
	});
	
	
	
 </script>

	<div class="col-md-12 text-center">
    	  <ul class="pagination pagination-lg pager" id="myPager"></ul>
      </div>
           </div>
 </div>
 </div>
   </div>
   
   
   
     
<div class="modal fade data-form" id="editDetaila" tabindex="-1" role="dialog" aria-labelledby="editDetails" aria-hidden="true">
    <div class="modal-dialog">
    	<div class="modal-content">
    	    <div class="modal-header">
       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        		<h4 class="modal-title custom_align" id="Heading">Edit or Add Your Detail</h4>
      		</div>
<div class="modal-body">
 <form class="form-horizontal" id="updateDetails" data-toggle="validator" method="post" role="form" action="">
  		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="updateName" class="control-label"> First Name : </label>
			</div>
  			<div class="col-sm-6">
  				<input pattern="[A-Za-z0-9]{1,20}" name="updateName" class="form-control" id="updateName" placeholder="Update Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="updateLastName" class="control-label">Last Name : </label>
			</div>
  			<div class="col-sm-6">
  				<input pattern="[A-Za-z0-9]{1,20}" name="updateLastName" class="form-control" id="updateLastName" placeholder="Update Name"  required>
  				<div class="help-block with-errors"></div>
			</div>
		</div>
		
		
			
		
	
  		
  		
  		
		<div class="form-group">
      			<label class="col-sm-5" for="emailUpdate">Email:</label>
      			<div class="col-sm-6">
        			<input type="email" class="form-control" id="emailUpdate" placeholder="Enter email" name="emailUpdate" required="required" readonly="readonly">
      			</div>
    	</div>
    	
    	
    	
    	<div class="form-group">
  			<div class="col-sm-5">
    			<label for="updateMobileNumber" class="control-label">Mobile Number</label>
			</div>
  			<div class="col-sm-6">
    			<input  name="updateMobileNumber" class="form-control" placeholder="update mobile number" id="updateMobileNumber" required>
    			<div class="help-block with-errors"></div>
  			</div>
  		</div>
    	
    	
    	
    	
  		<div class="form-group">
  			<div class="col-sm-offset-1 col-sm-5">
      		</div>
      		<div class="col-sm-offset-1 col-sm-6">
      			<button type="submit" class="btn btn-success btn-primary">Upload</button>
 			</div>
 		</div>
	</form>
      		</div>
       </div>
  	</div>
</div>
    
    
 
   
   
   
   
   
   
   
   
   
   
   
<div class="modal fade data-form1" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
    	<div class="modal-content">
    	    <div class="modal-header">
       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        		<h4 class="modal-title custom_align" id="Heading">Edit or Add Your pet Detail</h4>
      		</div>
<div class="modal-body">
 <form class="form-horizontal" id="formRegister" data-toggle="validator" method="post" role="form" action="SignUpServlet">
  		<div class="form-group">
			<div class="col-sm-5">
			  			<label for="inputFirstName" class="control-label">Name Of Pet</label>
			</div>
  			<div class="col-sm-6">
  				<input pattern="[A-Za-z0-9]{1,20}" name="petname" class="form-control" id="petname" placeholder="Enter Pet Name"  required>
  				<div class="help-block with-errors"></div>
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
			  			<label for="zip" class="control-label">Zip Code</label>
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
      			<button type="submit" class="btn btn-success btn-primary">Upload</button>
 			</div>
 		</div>
	</form>
      		</div>
       </div>
  	</div>
</div>
    
    
    
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
      </div>
          <div class="modal-body">
       
       <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
       
      </div>
        <div class="modal-footer ">
        <button type="button" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
      </div>
        </div>
    </div></div>
    </c:if>
<%@ include file="/pages/footer.jsp" %>
