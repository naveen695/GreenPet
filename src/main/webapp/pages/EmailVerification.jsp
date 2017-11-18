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
  
     .navbar {
      margin-bottom: 0px;
      border-radius: 0;
      background-color:  	#F0FFF0;
    }
    
    
     .row.content {height: 450px}
    
      .sidenav {
      padding-top:0px;
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
			GeenPet
    	    			</a>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar" style="padding-top: 20px;">
      <ul class="nav navbar-nav">
    	<li><a><h4>GreenPet</h4></a></li>
    	<li><a>  </a></li>
        <li class="active"><a href="#"></a></li>
        <li><a href="#"></a></li>
        <li><a href="#"></a></li>
      </ul>
      
    </div>
  </div>
</div>

 

  <div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
    
    </div>
    <div class="col-sm-8 text-left"> 
    
<div class="jumbotron text-center">
 <div class="row">
    <div class="col-sm-12">
    <p class="bg-success">${signupresponce.statusMessage}</p>
    </div>
<!--     <div class="col-sm-3">
		<img src="/GreenPet/images/images.jpg" class="img-rounded" alt="Cinque Terre" > 
  	</div>    
 -->  </div>
</div>
 	
<div class="jumbotron text-center">
 <div class="row">
    <div class="col-sm-12">
    <p class="bg-success">
    <a href="/GreenPet/">Please Login</a>
    </p>
    </div>
   </div>
</div>    	
  	    	
<%@ include file="footer.jsp" %>


</body>
</html>