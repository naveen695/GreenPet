
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




</script>
<style>
.table-responsive {
    min-height: .01%;
    overflow-x: overlay;
}
</style>
<c:if test="${loginUserDetails.login != true }">
please login for details
</c:if>
<c:if test="${loginUserDetails.login == true }">
<div >
  <div class="row">
    <div class="col-sm-12">
		<div >
        <h5 style="color: blue;margin-top: 0px;margin-bottom: 0px;">Your Details</h5>
        <div class="table-responsive" style=" padding-bottom: 2px;">
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
    <div class="col-sm-12">
    
        <h5 style="0px;margin-bottom: 0px;color: blue;">Your  Pet Details </h5>
        <div class="table-responsive">
      <table id="mytable1" class="table table-striped table-bordered" style="margin-bottom: 0px">
               <thead>
               <tr>
                	<th>Name of Pet</th>
                    <th>Address1 (house)</th>
                   	<th>Address1 (street)</th>
                    <th>City</th>
                     <th>County</th>
                    <th>Zip Code</th>
                    <th>Country</th>
                	</tr>
                </thead>
   				<tbody id="mytab">
   				
        		<c:forEach var="user" items="${petDeails}">
			    	<tr>
				    	<td>${user.petname} </td>
    					<td>${user.address1}</td>
    					<td>${user.address2}</td>
    					<td>${user.city}</td>
    					<td>${user.county}</td>
   						<td>${user.zip}</td>
   						<td>${user.country}</td>
   						 
    				</tr>
    			</c:forEach>
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
   
   <style>
  .pager {
    padding-left: 0;
    margin: 2px;
    text-align: center;
    list-style: none;
}
</style>
   
     
<div class="modal fade data-form" id="editDetaila" tabindex="-1" role="dialog" aria-labelledby="editDetails" aria-hidden="true">
    <div class="modal-dialog">
    	<div class="modal-content">
    	    <div class="modal-header">
       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        		<h4 class="modal-title custom_align" id="Heading">Edit or Add Your Detail</h4>
      		</div>
<div class="modal-body">
 <form class="form-horizontal" id="updateDetails" data-toggle="validator" method="post" role="form" action="UpdateDetails">
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
    
    

   
    </c:if>
    </div>
	<div class="col-sm-4 sidenav">
		<c:if test="${loginUserDetails.login == true }">
    		<%@ include file="UploadImage.jsp" %>
    	</c:if>
    </div>
  </div>
</div>
<%@ include file="/pages/footer.jsp" %>
