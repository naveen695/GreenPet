<%@ include file="/pages/header.jsp" %>
 
<c:if test="${msg.statusMessage != null}">
 <div class="modal fade" id="LoginMessage" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
            <div class="modal-body">
    				<h3>${msg.statusMessage}</h3>
    		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
        </div>
	        </div>
       </div>
      </div>
</c:if>
 
<script type="text/javascript"> 

</script>
 
 <style>
 

.panel-shadow {
    box-shadow: rgba(0, 0, 0, 0.3) 7px 7px 7px;
}
.panel-white {
  border: 1px solid #dddddd;
}
.panel-white  .panel-heading {
  color: #333;
  background-color: #fff;
  border-color: #ddd;
}
.panel-white  .panel-footer {
  background-color: #fff;
  border-color: #ddd;
}

.post .post-heading {
  height: 95px;
  padding: 20px 15px;
}
.post .post-heading .avatar {
  width: 60px;
  height: 60px;
  display: block;
  margin-right: 15px;
}
.post .post-heading .meta .title {
  margin-bottom: 0;
}
.post .post-heading .meta .title a {
  color: black;
}
.post .post-heading .meta .title a:hover {
  color: #aaaaaa;
}
.post .post-heading .meta .time {
  margin-top: 8px;
  color: #999;
}
.post .post-image .image {
  width: 100%;
  height: auto;
}
.post .post-description {
  padding: 15px;
}
.post .post-description p {
  font-size: 14px;
}
.post .post-description .stats {
  margin-top: 20px;
}
.post .post-description .stats .stat-item {
  display: inline-block;
  margin-right: 15px;
}
.post .post-description .stats .stat-item .icon {
  margin-right: 8px;
}
.post .post-footer {
  border-top: 1px solid #ddd;
  padding: 15px;
}
.post .post-footer .input-group-addon a {
  color: #454545;
}
.post .post-footer .comments-list {
  padding: 0;
  margin-top: 20px;
  list-style-type: none;
}
.post .post-footer .comments-list .comment {
  display: block;
  width: 100%;
  margin: 20px 0;
}
.post .post-footer .comments-list .comment .avatar {
  width: 35px;
  height: 35px;
}
.post .post-footer .comments-list .comment .comment-heading {
  display: block;
  width: 100%;
}
.post .post-footer .comments-list .comment .comment-heading .user {
  font-size: 14px;
  font-weight: bold;
  display: inline;
  margin-top: 0;
  margin-right: 10px;
}
.post .post-footer .comments-list .comment .comment-heading .time {
  font-size: 12px;
  color: #aaa;
  margin-top: 0;
  display: inline;
}
.post .post-footer .comments-list .comment .comment-body {
  margin-left: 50px;
}
.post .post-footer .comments-list .comment > .comments-list {
  margin-left: 50px;
} 
 </style> 



<c:if test="${loginUserDetails.login == true }">
	
<div style="height: 500px; overflow: scroll;">
	<c:forEach var="user" items="${petDeails}">
        	<div class="col-sm-12">
        		<div class="panel panel-white post panel-shadow">
        	   	<div class="col-sm-4"> 
               		<div class="post-heading">
                    <div class="pull-left image">
						<a href="#" data-image="small/Image" data-zoom-image="large/Image"> 
							<img src="Image/${user.petname}" height="100px" width="100px" class="img-circle avatar" ></img>
						</a>
                    </div>
                	</div>
	           	</div> 
	            <div class="col-sm-8">
              		  
              		  
              		 <table class="table">
  						 <tbody>
    							<tr>
        							<th>TreeName :</th>
       								<td><a href="ImageUpdateServlet?inputName=${user.id}" ><b>${user.petname}</b></a></td>
      							</tr>
      							<tr>
        							<th>Pet Desc :</th>
       								<td><textarea  placeholder="What are you doing right now?"  readonly="readonly">${user.petDesc}</textarea></td>
      							</tr>
						</tbody>
					</table>
             	
             		<%-- <div class="post-description"> 
                        <div>
                       		<div class="title h5">
                       			PetName :
                            	<a href="ImageUpdateServlet?inputName=${user.id}" ><b>${user.petname}</b></a>
                        	</div>
                 		</div>
                 	
                	PetDesc :
                	<textarea  placeholder="What are you doing right now?" >${user.petDesc}</textarea>
                    <div class="stats">
                        <a href="#" class="btn btn-default stat-item">
                            <i class="fa fa-thumbs-up icon"></i>2
                        </a>
                        <a href="#" class="btn btn-default stat-item">
                            <i class="fa fa-thumbs-down icon"></i>12
                        </a>
                    </div>
                </div> --%>
              </div>
              
	            </div>
    	    </div>
	</c:forEach>
    </div>           
   </c:if>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">

 </div>
<div class="col-sm-4 sidenav">
		<c:if test="${loginUserDetails.login == true }">
    		<%@ include file="UploadImage.jsp" %>
    	</c:if>
</div>
  </div>
</div>
<%@ include file="/pages/footer.jsp" %>

