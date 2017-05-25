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
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA37598NOPeHttg0t75EozEBNF_JpT4vZ0"></script>
   <style>
  
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0px;
      border-radius: 0;
      background-color:  	#F0FFF0;
    }
    
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top:0px;
      background-image: url("/GreenPet/images/BG_image.jpg");
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color:  	#F0FFF0;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 50px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>

<div class="navbar">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle btn btn-info active btn-md" data-toggle="collapse" data-target="#myNavbar"> MENU
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">
      <img src="/GreenPet/images/images.png" class="img-rounded" 
          			 alt="image not found" width="50" height="50">
    	    			</a>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar" style="padding-top: 20px;">
      <ul class="nav navbar-nav">
    	<li><a><h4>GreenPet</h4></a></li>
    	<li><a>  </a></li>
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
<c:if test="${loginUserDetails.login != true }">
     <div class="btn-group btn-group-lg nav navbar-nav navbar-right" >
  							<div class="btn-group">
   								<button type="button" class="btn btn-success active btn-md" 
   								data-toggle="modal" data-target="#signIn">  LOGIN  </button>
  								<!-- Modal -->
   							</div>
  							<div class="btn-group">
    							<button type="button" class="btn btn-danger active btn-md" 
   								data-toggle="modal" data-target="#signUp">SING UP</button>
  							</div>
	</div>
</c:if>
	
<c:if test="${loginUserDetails.login == true }">
	<div class="btn-group btn-group-lg nav navbar-nav navbar-right" >
		 <div class="dropdown">
   			<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
		   		 <span class="glyphicon glyphicon-user"></span> User 
			</button>
   			 <ul class="dropdown-menu">
    		 <li><a href="#">kandhukuri naveen kumar</a></li>
   			 <li><a href="#">naveenkumar.kandhukuri@gmail.com</a></li>
    		 <li><a href="#" class="btn btn-info btn-sm"> <span class="glyphicon glyphicon-off"></span>logout</a></li>
  		  	 </ul>
  		</div>
  	</div>
</c:if>      
    </div>
  </div>
</div>



<!-- Sign in popup -->
 <div class="modal fade" id="signIn" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Sign In</h4>
        </div>
        <div class="modal-body">
          
		  <form class="form-horizontal" action="LoginServlet" method="post">
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
        			<button type="submit" class="btn btn-success">Submit</button>
        			<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      			</div>
    		</div>
  		</form>

        </div>
        <div class="modal-footer">
        </div>
      </div>
    </div>  
    </div>
 

<%@ include file="SignUp.jsp" %>
  <div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
    
    </div>
    <div class="col-sm-8 text-left"> 
    