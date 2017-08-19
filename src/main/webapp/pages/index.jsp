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
		         success : function(responseText) {
		        	 $('#msg1').html(responseText);
		         },
		         
		         error : function(xhr, ajaxOptions, thrownError) {
		             $('#msg1').html(" <div class='jumbotron'> ERROR Loading images. </div>");
		         }
		    });
	}
 </script>
<%@ include file="Map.jsp" %>
</div>
<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
       
        <div id="img" class="modal-body">
         <div  style="margin-bottom: 0px;padding-bottom: 0px;">
         		<img id="test" src="" class="img-responsive" style="width: 600px;height: 650px;">
         </div>
      </div>
      
    </div>
  </div>
  </div>
<div  class="col-sm-4 sidenav">

 <div id="msg1">
 kkkkk
 </div>
</div>
  </div>
</div>
<%@ include file="footer.jsp" %>
