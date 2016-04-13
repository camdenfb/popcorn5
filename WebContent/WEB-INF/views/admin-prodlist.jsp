<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Production List</title>
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
      <li><a href="${context}/admin/artistlist">Artist List</a></li> 
      <li><a href="${context}/admin/addprodpage">Add Production</a></li> 
      <li class="active"><a href="${context}/admin/prodlist">Production List</a></li> 
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${context}/shared/home"><span class="glyphicon glyphicon-globe" target="_blank"></span> View Site</a></li>
      <li><a href="${context}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
</div>

<div style="width:80%; overflow-y: scroll; height:100vh; float:left; background:white; margin-left:10%; margin-right:10%; padding:5%">
<h1>Production List</h1>

	<table class="table">
			<tr>
				<th>Type</th>
				<th>Poster</th>
				<th width="130">Name</th>
				<th width="100">Release</th>
				<th>Genres</th>
				<th width="200">Synopsis</th>
				<th width="120">Action</th>
			</tr>
			
			<c:forEach items="${prodList}" var="prod">
				<tr>
				
				<td><c:choose>
  <c:when test="${prod.prodType == 'film'}">
    <img src="${context}/images/film.png" style="width:30px">
  </c:when>
  <c:otherwise>
    <img src="${context}/images/masks.png" style="height:30px">
  </c:otherwise>
	</c:choose>
				</td>
				<td><img src="${context}/images/${prod.prodPic}" width="50px"></td>
					<td><a href="${pageContext.request.contextPath}/shared/prodpage/${prod.prodId}">${prod.prodName}</a></td>
					<td><fmt:formatDate type="date" 
            value="${prod.prodRelease}" /></td>
					<td><c:forEach items="${prod.genreprodlist}" var="genre">
					${genre.genre.genre}<br></c:forEach> </td>
					<td>${prod.prodSynopsis}</td>
					
					<td><a href="<c:url value='editprod/${prod.prodId}'/>" >
					  	Edit Details</a><br>
					<a href="<c:url value='removeprod/${prod.prodId}'/>" >
					Delete</a><br>
						<a href="<c:url value='editgen/${prod.prodId}'/>" >
						Edit Genres</a><br>
						<a href="<c:url value='editcast/${prod.prodId}'/>" >
						Edit Cast/Crew
						</a></td>
					
				</tr>
			</c:forEach>
		</table>

<hr>
</div>

</div> <!-- END WRAPPER -->

       
</body>
</html>