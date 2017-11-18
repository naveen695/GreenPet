<%@ include file="/pages/header.jsp"%>

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
  
<style>
 #Container {
        position:relative;
        width:250px;
        height:61px;
        overflow:hidden;
      }

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

.post .post-footer .comments-list .comment>.comments-list { 
	margin-left: 50px;
}
</style>



 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      Modal content
      <div class="modal-content">
       
        <div id="img" class="modal-body">
         <div  style="margin-bottom: 0px;padding-bottom: 0px;">
         		<img id="test" src="" class="img-responsive" >
         </div>
      </div>
      
    </div>
  </div>
  </div> 
<script type="text/javascript">

function resizeImg(img){
 $('#test').attr('src',img.src);
} 

    </script>

<c:if test="${loginUserDetails.login == true }">

	<div style="height: 500px; overflow: scroll;">

		<table class="table">
			<tbody id="mytab1">
				<c:forEach var="user" items="${petDeails}">
					<tr>
						<td><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal"><img src="Image/${user.petname}" onclick="resizeImg(this)" alt="test" height="150px"
							width="140px" style="padding-top: 10px;"></img></a>
						<td>
						<td>
							<table class="table">
								<tbody>
									<tr>
										<th>TreeName :</th>
										<td><a href="ImageUpdateServlet?inputName=${user.id}"><b>${user.petname}</b></a></td>
									</tr>
									<tr>
										<th>Total Likes :</th>
										<td><b>${user.totalLikes}</b></td>
									</tr>
									
									<tr>
										<th>Pet Desc :</th>
										<td><textarea placeholder="What are you doing right now?"
												readonly="readonly">${user.petDesc}</textarea></td>
									</tr>
									<tr>
										<th>Upload More images:</th>
										<td><a href="ImageUpdateServlet?inputName=${user.id}"><img
												style="width: 50px;" alt="" src="/GreenPet/images/edit.png"></a></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
				
						<td class="col-sm-3">
						<form action="HomeServlet">
								<input type="hidden" id="pageNumberBack" name="pageNumberBack" readonly="readonly" value="${dataModel.pageNumber}">
								<button type="submit" class="btn btn-success btn-primary">BACK</button>
						</form>	
						</td>
						<td class="col-sm-3">Page Number:  ${dataModel.pageNumber}</td>
						<td class="col-sm-3">Total Pages : ${dataModel.totalpageNumber} </td>
						<td class="col-sm-3">
							<form action="HomeServlet">
								<input type="hidden" id="pageNumberNext" name="pageNumberNext" readonly="readonly" value="${dataModel.pageNumber}">
								<button type="submit" class="btn btn-success btn-primary">
									NEXT
								</button>
							</form>
						</td>
				</tr>
			</tfoot>
		</table>
		
	</div>
	
</c:if>





<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">

</div>
<div class="col-sm-4 sidenav">
	<c:if test="${loginUserDetails.login == true }">
		<%@ include file="UploadImage.jsp"%>
	</c:if>
</div>
</div>
</div>
<%@ include file="/pages/footer.jsp"%>

