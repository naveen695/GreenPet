<style>
	.main{
		width: 380px;
		padding: 3px;
		border: 1px solid #ececec;
		text-align: left;
	}
	.status{
		background-color:#F0FFF0;
		width:440px;
	}
	
	.comment{
		padding:5px; vertical-align:top; margin-top:2px;
font-size: 12px;
	}
	#myDIV {
			 	width: 400px; 
			 	height: 200px; 
			    text-align: center;
			    overflow-y: scroll;
			    background-color: #F0FFF0;
			    margin-top: 4px;
			    display: none;
			    border-bottom-style: solid;
			    border-top-style: solid;
    			border-width: 1px;
			    border-color: green;
	}
</style>
	
<script>
		function myFunction() {
			document.getElementById("myDIV1").style.display = "block";
		
		    var x = document.getElementById("myDIV");
		    var petID = document.getElementById("petID").value;
		    var data;
		    if (x.style.display === "none") {
		        x.style.display = "block";
					var loc= window.location.protocol + "//" + window.location.host+"/GreenPet/rest/comments/load/"+petID+"";
		        	$.ajax({
		        		type:"post",
		        		async:false,
		        		url:loc,
		        		contentType:"application/json; charset=utf-8",
		        		dataType:"json",
		        		processData:true,
		        		success:function(result){
		        		AjaxSucceeded(result);
		        		}, beforeSend: function(){
				        	 $(".modal1").show();
				       	},
		        	   complete:function(data){
		        		   $(".modal1").hide();
				       },
		        		eror:AjaxFailed
		        		
		        	});
		        		function AjaxSucceeded(result)
		        		{
		        			var com = result.comments;
		        			var htmlText = '<table border="0" cellpadding="0" cellspacing="0" class="main">';
		
		        		    for ( var key in com ) {
		        		        htmlText += '<tr class="status"><td class="comment"><b style="color: #2962ff;font-size: 12px;font-weight: normal">';
		        		        htmlText +=  ''+com[key].user +'- </b>'
		        		        
		        		        htmlText += '' + com[key].comment + '.</td> </tr>';
		        		    }
		        		    htmlText += '</table>';
		         		    document.getElementById("myDIV").innerHTML = htmlText;
		          		   x.scrollTop = 9999999;
		
		         	
		        		  }
		        		function AjaxFailed(result){
		        		alert(result.status+''+result.statusText);
		        		}
		        		    
		    
		    } else {
		        x.style.display = "none";
		    }
		}
		
		
		function myFunction1() {
		   
				var x = document.getElementById("myDIV");
			    var petID = document.getElementById("petID").value;
			    var data;
		        x.style.display = "block";
		        var loc= window.location.protocol + "//" + window.location.host+"/GreenPet/rest/comments/load/"+petID+"";
		        	
		        $.ajax({
		        		type:"post",
		        		async:false,
		        		url:loc,
		        		contentType:"application/json; charset=utf-8",
		        		dataType:"json",
		        		processData:true,
		        					success:function(result){
					        			AjaxSucceeded(result);
					        		}, beforeSend: function(){
							        	 $(".modal1").show();
							       	},
					        	   complete:function(data){
					        		   $(".modal1").hide();
							       },
					        		eror:AjaxFailed
		        		
		        		});
				        		function AjaxSucceeded(result){
				        			var com = result.comments;
				        			var htmlText = '<table border="0" cellpadding="0" cellspacing="0" class="main">';
				        			for ( var key in com ) {
					        		        htmlText += '<tr class="status"><td class="comment"><b style="color: #2962ff;font-size: 12px;font-weight: normal">';
					        		        htmlText +=  ''+com[key].user +'- </b>'
					        		        
					        		        htmlText += '' + com[key].comment + '.</td> </tr>';
					        		}
					        		   htmlText += '</table>';
					         		   document.getElementById("myDIV").innerHTML = htmlText;
					         		   document.getElementById('myDIV').scrollTop = 9999999;
				        		 }
				        		function AjaxFailed(result){
				        			alert(result.status+''+result.statusText);
				        		}
	}
		
	function savecomments(){
		var com=document.getElementById("comments").value;
	    var imageID = document.getElementById("imageID").value;
	    var petID = document.getElementById("petID").value;
		if(com==null || com ==""){
			alert("add comments");
			return false;
		} 
		var name = '<%= session.getAttribute("loginUserDetails") %>';
			if(name != "null" && name != '' && name!=null){
				
				var dataRequestObject= '{"comment":"'+com+'"}';
				var loc= window.location.protocol + "//" + window.location.host+"/GreenPet/rest/comments/save/"+petID+"/"+imageID;
				document.getElementById("comments").value="";
				$.ajax({
		 			type:"post",
		 			async:false,
		 			url:loc,
		 			contentType:"application/json; charset=utf-8",
		 			data:dataRequestObject,
		 			processData:true,
						 			success:function(result){
						 				myFunction1();
						 			},
						 			beforeSend: function(){
							        	 $(".modal1").show();
								    	},
							 	    complete:function(data){
							 		   $(".modal1").hide();
								    },
					 				eror:AjaxFailed
		 				});
							 		function AjaxFailed(result){
						        		alert(result.status+''+result.statusText);
						        	}
			}else{
				 alert(" Please Login .");
			}
	}
</script>
	
      <div id="myDIV1" style="display: none;">
				<div id="myDIV" style="display: none;margin-top: 10px;">
				</div>
				
				<form>
		        	<div id="combutton" class="col-md-12 " style="margin-top:10px;padding-left: 0px">
		          	 	<div class="col-md-10 " style="padding-left: 0px">
			          	  <fieldset class="form-group">
			          	      <input type="text"  class="form-control" style="padding-left: 20px;"  id="comments"  placeholder="Add a comment">
			          	  </fieldset>
		          	  	</div>
		          		<div class="col-md-2 ">
		             		<button type="button" onclick="savecomments()" class="btn btn-md btn-success">Post</button>
		             	</div>
		           </div>
		       </form> 
		</div>
