<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css">
<title>Admin Menu</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style type="text/css">
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>	
</head>

<body style="background:#e4e4e4">
<c:if test="${user.userType == 'admin'}">
<c:set var="context" value="${pageContext.request.contextPath}"/>

<div id="wrapper" style="width:100%; height:100vh; float:left">

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
      <li><a href="${context}/admin/prodlist">Production List</a></li> 
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <li><a href="${context}/shared/home"><span class="glyphicon glyphicon-globe" target="_blank"></span> View Site</a></li>
      <li><a href="${context}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>
</div>

<div style="width:80%; height:100%; float:left; background:white; margin-left:10%; margin-right:10%; padding:5%">
<h1>Welcome ${user.firstName}!</h1>
<hr>
Users <span class="badge">${totaluser}</span><br>
Reviews <span class="badge">${totalrev}</span><br>
Artists <span class="badge">${totalart}</span><br>
Productions <span class="badge">${totalprod}</span><br>
</div>

</div> <!-- END WRAPPER -->

</c:if>       
</body>

</html>