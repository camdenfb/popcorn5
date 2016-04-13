<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Artist List</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body style="background:#e4e4e4">
<c:set var="context" value="${pageContext.request.contextPath}"/>

<div id="wrapper" style="width:100%; float:left">

<div style="width:100%; height:50px; float:left;">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${context}/admin"><img src="${context}/images/pop.png" height="25px"></img></a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${context}/admin/addartistpage">Add Artist</a></li>
      <li class="active"><a href="${context}/admin/artistlist">Artist List</a></li> 
      <li><a href="${context}/admin/addprodpage">Add Production</a></li> 
      <li><a href="${context}/admin/prodlist">Production List</a></li> 
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <li><a href="${context}/shared/home"><span class="glyphicon glyphicon-globe" target="_blank"></span> View Site</a></li>
      <li><a href="${context}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
</div>

<div style="width:80%; overflow-y: scroll; height:100vh; float:left; background:white; margin-left:10%; margin-right:10%; padding:5%">
<h1>Artist List</h1>
	<table class="table">
			<tr>
				<th>Image</th>
				<th>Name</th>
				<th>Birthday</th>
				<th width="300">Bio</th>
				<th width="20" colspan="2">Action</th>
			</tr>

			<c:forEach items="${artistList}" var="artist">
				<tr>
					<td><img src="${context}/images/${artist.artistImg}" width="50px" height="50px"></td>
					<td><a href="${pageContext.request.contextPath}/shared/artistpage/${artist.artistId}">${artist.artistName}</a></td>
					<td><fmt:formatDate type="date" 
            value="${artist.artistBday}" /></td>
					<td>${artist.artistBio}</td>
					<td><a href="<c:url value='editartist/${artist.artistId}' />">
					  	<img src="${context}/images/edit.png" width="20px" height="20px"></a></td>
					<td><a href="<c:url value='removeartist/${artist.artistId}' />">
						<img src="${context}/images/delete.png" width="20px" height="20px"></a></td>			
				</tr>
			</c:forEach>
		</table>
<hr>
</div>

</div> <!-- END WRAPPER -->

       
</body>


<body>

</html>