<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Artist</title>
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
      <li class="active"><a href="${context}/admin/addartistpage">Add Artist</a></li>
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
<h1>Add Artist</h1>
<hr>	
	<c:set var="context" value="${pageContext.request.contextPath}"/>
<div style="width:50%; float:left">	
<form action="${context}/admin/addartist" method="post" id="newArtist" enctype="multipart/form-data">
             <table>
              
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="artistName"></td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><input type="date" name="artistBday"></td>
                </tr>
                <tr>
                    <td>Bio</td>
                    <td><textarea cols="40" rows="5" name="artistBio" form="newArtist" maxlength="255">
</textarea></td>
                </tr>
                <tr> 
					<td>Headshot</td>
					<td><input type="file" name="file" onchange="readURL(this);"></td>
				</tr>	
				<tr> 	
					<td>Image Name</td>
					<td><input type="text" name="artistImg"></td>
				</tr>	
                <tr>
                    <td><input type="submit" value="Submit"/></td>
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

<!-- 
<form:form action="${context}/admin/addartist" method="post" modelAttribute="artist">
             <table>
              
                <tr>
                    <td><form:label path="artistName">Name</form:label></td>
                    <td><form:input path="artistName"/></td>
                </tr>
                <tr>
                    <td><form:label path="artistBday">Bday</form:label></td>
                    <td><form:input type="date" path="artistBday"/></td>
                </tr>
                <tr>
                    <td><form:label path="artistBio">Bio</form:label></td>
                    <td><form:textarea path="artistBio"/></td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
 -->
</html>