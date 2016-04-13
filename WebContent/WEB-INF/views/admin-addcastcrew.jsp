<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<title>Add Production</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script
	src="http://www.google.com/uds/solutions/dynamicfeed/gfdynamicfeedcontrol.js"
	type="text/javascript"></script>

<style type="text/css">
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script>
$(document).ready( function(){
});

$(function(){
    $('#savecastcrew').click(function(event){
      var castcrew = [];
      $(':checkbox:checked').each(function(i){
       	castcrew[i] = $(this).val();
       	
        //INSERT AJAX HERE
        var passme = {};
        passme["prod"] = parseInt($("#prodId").val());
        passme["artist"] = parseInt(castcrew[i]);
        passme["role"] = $("#" + castcrew[i]).val();
        
        //alert(JSON.stringify(passme));
        
		// POST method
		$.ajax({
			type: "POST",
			contentType: "application/x-www-form-urlencoded",
			url : "http://localhost:8080/Popcorn/json/addcastcrew",
			cache: false,
			data: passme,
			dataType: 'json',
			success: function(data) {
				//alert(data);
			},
			error: function(xhr, status, error) {
				//alert(xhr.responseText);
			}
		}); 

      });
      alert("Cast and Crew Saved");
    });
  });


</script>	

<style>
td {
    padding: 5px;
}
a:link {
    color: black;
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
      <li class="active"><a href="${context}/admin/addprodpage">Add Production</a></li> 
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
<h1>Select Cast and Crew</h1>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<h4>Step 3</h4>
<hr>	

<form:form action="" method="post" modelAttribute="production">
<form:hidden path="prodId"/>
<form:hidden path="prodName"/>
</form:form>	

      <div id="castcrew" style="height:300px; overflow-y: scroll">
			<table class="table">
		
        <c:forEach items="${artistList}" var="artist">
        
        <tr>
			<td><input type="checkbox" name="artists" value="${artist.artistId}"/></td>
			<td><img src="${context}/images/${artist.artistImg}" style="width:30px; height:30px"></td>
			<td>${artist.artistName}</td>
			<td><input type="text" id="${artist.artistId}"></td>
			</tr>			
			</c:forEach>
			
	</table>
        </div>
        <hr>
        <input type="button" id="savecastcrew" name="save_value" value="Save" />
        <form action="${context}/admin/addcastcrewpage" method="post">	
	<a href="${context}/admin/prodlist"><button type="button">Finish</button></a>
	</form>
</div>

</div> <!-- END WRAPPER -->
</body>



</html>

