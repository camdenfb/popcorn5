<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Production</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<script>
$(document).ready( function(){
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#hey')
                .attr('src', e.target.result)
                .height(300)
        };

        reader.readAsDataURL(input.files[0]);
    }
}
</script>

<style>
td {
    padding: 5px;
}
a:link {
    color: white;
    text-decoration: none;
}
a:hover {
    color: yellow;
    text-decoration: none;
}
</style>

</head>

<body style="background:#e4e4e4">
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
<h1>Edit Production</h1>
<hr>	
	<c:set var="context" value="${pageContext.request.contextPath}"/>
	
<div style="width:50%; float:left">	
<form action="${context}/admin/saveprod" method="post" id="saveProd" enctype="multipart/form-data">	
<input type="hidden" name="prodId" value="${production.prodId}">
<table>
<tr>
	<td>Name</td>
	<td><input type="text" name="prodName" value="${production.prodName}"></td>
</tr>	
<tr>
	<td>Release</td>
	<td><input type="date" name="prodRelease" value="${production.prodRelease}"></td>
</tr>	
<tr>		
	<td>Type</td>
	
	
	 <c:choose>
  <c:when test="${production.prodType == 'film'}">
   <td><input type="radio" name="prodType" value="theater">Theater
    <input type="radio" name="prodType" value="film" checked>Film</td>
  </c:when>
  <c:otherwise>
  <td><input type="radio" name="prodType" value="theater" checked>Theater
    <input type="radio" name="prodType" value="film">Film</td>
  </c:otherwise>
	</c:choose>

</tr>	
<tr>   
    <td>Synopsis</td>
    <td><textarea cols="40" rows="5" name="prodSynopsis" form="saveProd" maxlength="255">${production.prodSynopsis}</textarea></td>
</tr>	
<tr> 
	<td>Youtube Code</td>
	<td><input type="text" name="prodTrailer" value="${production.prodTrailer}"></td>
</tr>	
<tr> 
	<td>Poster</td>
	<td><input type="file" name="file" onchange="readURL(this);"></td>
</tr>	
<tr> 	
	<td>Image Name</td>
	<td><input type="text" name="prodPic" value="${production.prodPic}"></td>
</tr>	
<tr> 
	<td><input type="submit" value="Save"></td>
</tr>		
</table>	
</form> 
</div>

<div style="width:50%; float:left;">
<img src="" id="hey">
</div>


</div>

</div> <!-- END WRAPPER -->

       
</body>
</html>