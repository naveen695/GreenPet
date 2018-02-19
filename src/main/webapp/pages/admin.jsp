<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>dgree</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA37598NOPeHttg0t75EozEBNF_JpT4vZ0&v=3.exp&libraries=places"></script>
   <style>
  
    .navbar {
      margin-bottom: 0px;
      border-radius: 0;
      background-color:  	#F0FFF0;
    }
    
    
    .row.content {height: 450px}
    
    .sidenav {
      padding-top:0px;
       background-color: honeydew;
       height: 100%;
    }
    
    footer {
      background-color:  	#F0FFF0;
      color: white;
      padding: 15px;
    }
    
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 50px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body >

<div class="navbar" style="border-bottom-color: green;" >
  	<div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle btn btn-info active btn-md" data-toggle="collapse" data-target="#myNavbar"> MENU
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar" style="padding-top: 20px;">
		      <ul class="nav navbar-nav">
		    	<li><a href=""><h4>G-Admin</h4></a></li>
		        <li class="active">
		        	<a href="HomeServlet"><input type="hidden" id="homeIndex" value="1">Home</a>
		        </li>
		     </ul>
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
          <h4 class="modal-title"></h4>
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
    
 
  <div class="container-fluid text-center">    
  	<div class="row content">
    	<div class="col-sm-1 sidenav"></div>
		 	<div class="col-sm-7 text-left" style="height: 100%;padding-right: inherit;  padding-left: inherit; background-color: honeydew;border-right-style: ridge;overflow-x: unset;border-right-color: lightgreen;border-right-width: 1px;" >
		   		<c:if test="${loginUserDetails.login != true }">
		        	<div style="display :list-item;">
				  		<form class="form-horizontal" action="AdminControler" method="post">
							<div class="form-group">
			      				<label class="control-label col-sm-3" for="email">Email:</label>
			      				<div class="col-sm-6">
			        				<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
			      				</div>
			    			</div>
			    				<div class="form-group">
			    					<label class="control-label col-sm-3" for="pwd">Password:</label>
			      					<div class="col-sm-6">          
			      						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
			      					</div>
			    				</div>
			    			<div class="form-group">        
			    				<div class="col-sm-offset-1 col-sm-2">
			      				</div>
			      				<div class="col-sm-offset-1 col-sm-6">
			        				<button type="submit" class="btn btn-success">Login Adimn</button>
			      				</div>
			    			</div>
		  				</form>
		        	</div>
				</c:if> 
				
  				<c:if test="${loginUserDetails.login == true && loginUserDetails.admin == 'yes' }">
  				 	<c:forEach items="${loadImages}" var="petdetails">
  				      <div class="row" style="padding-bottom: 19px;">
					 	 	<div class="col-sm-4">
									 <a href="javascript:void(0);" data-toggle="modal" data-target="#myModal"><img src="LoadImage/${petdetails.image.ids}" onclick="resizeImg(this)" alt="test" height="150px"
								width="140px" style="padding-top: 10px;"></img>	</a>
					  		</div>
			   				<div class="col-sm-8">
			   					<div class="row" style="padding: 10px;">
			   						<div class="col-sm-6" >
			   						 	   		<div class="form-group row"> 
			   						 	  			 <div class="col-sm-offset-1 col-sm-3">
 														PetName
 									        		 </div>
									        		 <div class="col-sm-offset-1 col-sm-3">
 														${petdetails.petname}
 									      			</div>
 									      		</div>
 									       
 									      	<form action="PetIdApprove" method="post" class="form-horizontal">
 									      		<div class="form-group row">       
 									    			 <div class="col-sm-offset-1 col-sm-3">
 									    			 	<input type="hidden" id="petidApprove" name="petidApprove" readonly="readonly" value="${petdetails.image.ids}">
									        			<button type="submit" class="btn btn-success">Approve
									        			</button>
									        		 </div>
									        	</div>
									        </form>
									        <form action="PetIdDelete" method="post" class="form-horizontal">
									      		<div class="form-group row">       
									        		 <div class="col-sm-offset-1 col-sm-3">
									        		  	<input type="hidden" id="petidDelete" name="petidDelete" readonly="readonly" value="${petdetails.image.ids}">
									        		 	<button type="submit" class="btn btn-danger" >Delete</button>
									      			</div>
									    		</div>
									  		</form>
									</div> 
			   					</div>
		   					</div>
	   					</div>  
				 	</c:forEach>	 	 
				</c:if>  
			</div>
		<div class="col-sm-4 sidenav"></div>
	</div>
  </div>
 
    <footer class="container-fluid text-center" style="border-top-style: double;border-top-color: green;border-top-width: thin;margin-top: 3px;">
  <p style="color: green;font-style: oblique;">help@dgree.com</p>
</footer> 

</body>
</html>