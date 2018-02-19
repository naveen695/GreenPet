<%@ include file="header.jsp" %>
 <script type="text/javascript">
 
 function zoom1(){
	var path = document.getElementById("image1").src;
    $('#test').attr('src',path);
 }
 
 function zoom(path){
	var path = document.getElementById("path").src;
    $('#test').attr('src',path);
 }

 function loadajaximages(id,type) {
		var dataRequestObject= {}; 
	    var pageIndex=document.getElementById("pageIndex").value;
//		var nextNum=document.getElementById("nextNum").value;
		dataRequestObject= {id:id,type:type,pageIndex:pageIndex};
		 	 $.ajax({
		         type : "post",
		         url : "LoadMultipleImages",
		         data : dataRequestObject,
		         dataType : 'json',
		         success : function(responseText) {
		        	 $('#msg1').html(responseText.data);
		        	 $('#msg').html(responseText.content);
		        	 $('#imageID').val(responseText.imageID);
		        	 $('#petID').val(responseText.petID);
		         },
		         beforeSend: function(){
		        	 $(".modal1").show();
		       	},
        	   complete:function(data){
        		   $(".modal1").hide();
		       },
		         error : function(xhr, ajaxOptions, thrownError) {
		             $('#msg1').html(" <div class='jumbotron'> ERROR Loading images. </div>");
		             $('#msg').html("");
		             $('#imageID').val("");
		        	 $('#petID').val("");
		         }
		    });
		 	 return false;
	}
 function send(action,id,loadingImageID,imagelikes,petlikes) {
	 var name = '<%= session.getAttribute("loginUserDetails") %>';
	 if(name != "null" && name != '' && name!=null){
		    var opposite;
			var dataRequestObject1= {}; 
		    opposite = action === 'like' ? 'dislike' : 'like';
		  	dataRequestObject1= {id:id,loadingImageID:loadingImageID,type:action,imagelikes:imagelikes,petlikes:petlikes};
		     $.ajax({
		         type:"POST",
		         url:"LikeControler",
		         data:dataRequestObject1,
		         success: function(data){
		        	 $('#msg').html(data.content);
			      },
			      beforeSend: function(){
			        	 $(".modal1").show();
				  },
			 	  complete:function(data){
			 		   $(".modal1").hide();
				  }
		     });
	 }else{
		 alert(" Please Login .");
	 }
 	}
 $("#msg").hover(
		   function(e){
		       $("#tooltip").show();
		   },
		   function(e){
		       $("#tooltip").hide();
		  });
 
 

 </script>
 
 <style>  
   
     .like, .dislike {
         display: inline-block;
         margin-bottom: 0;
         font-weight: normal;    
         text-align: center;    
         vertical-align: middle;    
         cursor: pointer;    
         background: lightgreen;    
         border: 1px solid transparent;    
         white-space: nowrap;    
         padding: 6px 12px;    
         font-size: 14px;    
         line-height: 1.428571429;    
         border-radius: 4px;
        }
       .qty1, .qty2 {
           border: none;    
           width:20px;    
           background: transparent;
        }
	.​tooltip {
		display:none;
		border:1px solid #F00;
		width:150px;
	}​
  </style> 
	
<style type="text/css">
    .body
    {
        font-family: Arial;
        font-size: 10pt;
    }
    .modal1
    {
        position: fixed;
        z-index: 999;
        height: 100%;
        width: 100%;
        top: 0;
        left: 0;
        filter: alpha(opacity=60);
    }
    .center
    {
        z-index: 1000;
        margin: 300px auto;
        padding: 10px;
        width: 100px;
        border-radius: 10px;
        opacity: 0.9;
        -moz-opacity: 0.8;
    }
    .center img
    {
        height: 128px;
        width: 128px;
        opacity: 0.9;
        -moz-opacity: 0.8;
    }
</style>

<%@ include file="Map.jsp" %>
</div>

<!--   Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      Modal content
	     <div class="modal-content">
        	<div id="img" class="modal-body">
        		 <div  style="margin-bottom: 0px;padding-bottom: 0px;">
         			<img id="test" src="" class="img-responsive" style="width: 600px;height: 350px;">
    	    	 </div>
	      	</div>
    	</div>
  	</div>
  </div>  
		
		
		<div  class="col-sm-4 sidenav" style="padding-left: 0px;">
 			<div id="msg1"></div>
			<div id="msg" style="border-width: 3px;"></div>
			<%@ include file="comments.jsp" %>
			
			
<!-- 			loading spain for ajax				
 -->	
 			<div class="modal1" style="display: none">
		    	<div class="center">
		        	<img alt="" src="images/loader.gif" />
		    	</div>
			</div>
		</div>
</div>
  </div>
</div>
<%@ include file="footer.jsp" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.fa {
    font-size: 25px;
    cursor: pointer;
    user-select: none;
}

.fa:hover {
  color: darkblue;
}
</style>
