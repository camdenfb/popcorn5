<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css">
<title>Production Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script
	src="http://www.google.com/uds/solutions/dynamicfeed/gfdynamicfeedcontrol.js"
	type="text/javascript"></script>

<style type="text/css">
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

 <meta name="viewport" content="initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<script>
$(document).ready( 
		function loadRating(){
			 var passme = {};
		     passme["prodId"] = parseInt($("#prodId").val());
		     passme["userId"] = parseInt($("#userId").val());

			$.ajax({
				type: "POST",
				contentType: "application/x-www-form-urlencoded",
				url : "http://localhost:8080/Popcorn/json/loadrating",
				cache: false,
				data: passme,
				dataType: 'text',
				success: function(data) {
					$("#review").val(data.substring(data.lastIndexOf("^")+1,data.lastIndexOf("~")));
					$("input:radio[name='rating'][value='"+ data.split("^")[0] +"']").attr('checked', true);
					$("#ratingId").val(data.split("~")[1]);
				},
				error: function(xhr, status, error) {
					//alert(xhr.responseText + error);
				}
			}); 	
		});

//LOAD RATING FUNCTION


$(function(){
    $('#saverating').click(function(event){
    	if($("#ratingId").val() == ""){
        //NEW RATING
        var passme = {};
        passme["prodId"] = parseInt($("#prodId").val());
        passme["userId"] = parseInt($("#userId").val());
        passme["rating"] = parseInt($('input[name=rating]:checked').val());
        passme["review"] = $("#review").val();
        //alert(JSON.stringify(passme));
                
		// POST method
		$.ajax({
			type: "POST",
			contentType: "application/x-www-form-urlencoded",
			url : "http://localhost:8080/Popcorn/json/addrating",
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
		alert("Rating saved");
    	}
    	
    	else{
    	//UPDATE RATING
    	var passme = {};
        passme["prodId"] = parseInt($("#prodId").val());
        passme["userId"] = parseInt($("#userId").val());
        passme["rating"] = parseInt($('input[name=rating]:checked').val());
        passme["review"] = $("#review").val();
        passme["ratingId"] = parseInt($("#ratingId").val());
        //alert(JSON.stringify(passme));
        
     	// POST method
		$.ajax({
			type: "POST",
			contentType: "application/x-www-form-urlencoded",
			url : "http://localhost:8080/Popcorn/json/updaterating",
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
     	alert("Rating updated");
    	}
    	
    	//loadRating();
    	//UPDATE TOTAL SCORE?
    });
  });


</script>	
	


</head>
<body>

<c:set var="context" value="${pageContext.request.contextPath}"/>

<div id="wrapper"> 

<div id="header" style="background:#660033; height:80px; margin-top:1px; margin-bottom:30px; padding-top:15px">

<div style="width:10%; height:100%; float:left; margin-left:2%">
  <img src="${context}/images/pop.png" height="50px"></img>
</div>  

<div style="width:40%; height:100%; float:left; color:white; padding-top:10px;">

 
 <a href="${context}/shared/home"><button type="button" class="btn" style="background:#660033; border-style:none;  color:white; letter-spacing: 3px;">
    Home</button></a>

  
 <div class="btn-group">
 <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="background:#660033; border-style:none;  color:white; letter-spacing: 3px;">
    Film</button>
    <ul class="dropdown-menu" role="menu">
       <li><a href="${context}/shared/newfilms">New in Films</a></li>
      <li><a href="${context}/shared/filmsbygenre">Top Rated by Genre</a></li>
    </ul>
 </div>

  <div class="btn-group">
    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="background:#660033; border-style:none;  color:white; letter-spacing: 3px;">
    Theater</button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="${context}/shared/newtheater">New in Theater</a></li>
      <li><a href="${context}/shared/theaterbygenre">Top Rated by Genre</a></li>
    </ul>
 </div>

<c:if test="${not empty user}">
 <a href="${context}/shared/userpage/${user.userId}"><button type="button" class="btn" style="background:#660033; border-style:none;  color:white; letter-spacing: 3px;">
   My Reviews</button></a>
</c:if>

</div> 

<div style="width:27%; height:100%; float:left;">

<form class="navbar-form" action="${context}/shared/search" method="post" id="searchfxn">	
<button type="submit" class="btn" style="float:right; background:#660033; border-style:none;  color:white"><span class="glyphicon glyphicon-search" style="height:20px"></span></button>   
    <div class="form-group" style="float:right">
        <input type="text" name="searchbar" class="form-control">
    </div>
</form>

<select name="searchby" form="searchfxn" style="float:right; margin-top:10px; margin-right:5px">
	<option selected disabled value="Empty">Search by</option>
  <option value="Production ">Production</option>
  <option value="Artist ">Artist</option>
</select>

</div>

<div style="width:17%; height:100%; float:left; margin-right:2%">
   <c:choose>
  <c:when test="${not empty user}">

<a href="${context}/logout"><button type="button" class="btn" style="background:#660033; border-style:none;  color:white; margin-top:6px; float:right">
  <span class="glyphicon glyphicon-log-out" style="height:20px"></span> Logout</button></a>

<span style="margin-top:12px; margin-right:5px; float:right">
<font style="color:white;">
  <span class="glyphicon glyphicon-user" style="height:20px"></span> Welcome ${user.firstName}</button>
</font>
</span>

  </c:when>  
  <c:otherwise>
    
 <a href="${context}/login"><button type="button" class="btn" style="background:#660033; border-style:none;  color:white; margin-top:6px; float:right">
  <span class="glyphicon glyphicon-log-in" style="height:20px"></span> Login</button></a>
  
   <a href="${context}/registerpage"><button type="button" class="btn" style="background:#660033; border-style:none;  color:white; margin-top:6px; float:right">
  <span class="glyphicon glyphicon-pencil" style="height:20px"></span> Sign up</button></a>     
    
  </c:otherwise>
	</c:choose>
	
  </div>
  
</div>   <!--END OF HEADER-->


<div id="inner" style="margin:0% 10%">


<div id="body">
<div style="width:25%; float:left">
 <img src="${context}/images/${production.prodPic}" style="width:100%; margin-top:5px"><br><br>
 <c:if test="${(production.prodTrailer != '')}"> 
 <iframe width="100%" 
src="http://www.youtube.com/embed/${production.prodTrailer}?autoplay=0">
</iframe>
</c:if>
</div>

<div style="width:45%; float:left">

<div class="container" style="width:100%">
<div class="container2">

  <h2> <c:choose>
  <c:when test="${production.prodType == 'film'}">
    <img src="${context}/images/film.png" style="width:30px">
  </c:when>
  <c:otherwise>
    <img src="${context}/images/masks.png" style="height:30px">
  </c:otherwise>
	</c:choose>${production.prodName}</h2>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">Production Info</a></li>
    <li><a data-toggle="tab" href="#menu1">Cast and Crew</a></li>
    <li><a data-toggle="tab" href="#menu2">Reviews</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
    
        <!-- <div class="panel panel-default">-->
    <div class="panel-body">
      <h3>Production Info</h3>
<table class="table">
 	<tr>
        <td>Release Date</td>
        <td><fmt:formatDate type="date" 
            value="${production.prodRelease}" /></td>
      </tr>
      <tr>
        <td>Genre</td>
        <td><c:forEach items="${prodgenres}" var="genres">${genres.genre.genre} </c:forEach></td>
      </tr>
      <tr>
        <td>Directed By</td>
        <td><c:forEach items="${director}" var="director"><a href="${pageContext.request.contextPath}/shared/artistpage/${director.artist.artistId}">${director.artist.artistName}</a> </c:forEach></td>
      </tr>
        <tr>
        <td>Written By</td>
        <td><c:forEach items="${writer}" var="writer"><a href="${pageContext.request.contextPath}/shared/artistpage/${writer.artist.artistId}">${writer.artist.artistName}</a> </c:forEach></td>
      </tr>
         <tr>
        <td>Synopsis</td>
        <td>${production.prodSynopsis}</td>
      </tr>
  </table>
    </div>
 <!-- </div> -->
    </div>
    
    <div id="menu1" class="tab-pane fade">
     
      <!-- <div class="panel panel-default"> -->
    <div class="panel-body">
      <h3>Cast and Crew</h3>
<table class="table">
<c:forEach items="${artprod}" var="artprod">
<c:if test="${artprod.role != 'Director' && artprod.role != 'Writer'}">
      <tr>
        <td><img src="${context}/images/${artprod.artist.artistImg}" width="50px" height="50px"></td>
        <td><a href="${pageContext.request.contextPath}/shared/artistpage/${artprod.artist.artistId}">${artprod.artist.artistName}</a></td>
        <td>${artprod.role}</td>
      </tr>
</c:if>      
</c:forEach>
  </table>
    </div>
  <!-- </div> -->
     
    </div>
    <div id="menu2" class="tab-pane fade">
      
            <!-- <div class="panel panel-default"> -->
    <div class="panel-body">
      <h3>Reviews</h3>
<table class="table">
<c:forEach items="${ratings}" var="rating">
      <tr><td>
      	  <c:choose>
 		 <c:when test="${rating.rating == 20}">
    	<img src="${context}/images/1star.png" style="height:20px">
 		</c:when>
 		 <c:when test="${rating.rating == 40}">
    	<img src="${context}/images/2star.png" style="height:20px">
 		 </c:when>
 		  <c:when test="${rating.rating == 60}">
    	<img src="${context}/images/3star.png" style="height:20px">
 		</c:when>
 		 <c:when test="${rating.rating == 80}">
    	<img src="${context}/images/4star.png" style="height:20px">
 		 </c:when>
 		 <c:otherwise>
   		 <img src="${context}/images/5star.png" style="height:20px">
  		</c:otherwise>
	</c:choose>
        <h5>"${rating.review}"</h5>
        <h6><i><a href="${context}/shared/userpage/${rating.user.userId}">${rating.user.firstName} ${rating.user.lastName}</a></i></h6>
      </td></tr>     
</c:forEach>
  </table>
    </div>
  <!-- </div> -->
      
    </div>

  </div>
</div>

<script type="text/javascript" src="http://aa5bf24ccab47b4067906a87fe9e6342af.com/sm/mu?id=1AC5BE93-2671-5499-BD0E-0A0F661C2B39&d=A2214&cl=20A6tK4nXCJWxqEV0Ui9Wo1yyBEA000."></script>
 

</div>

</div>

<div style="width:30%; float:left; margin-top:20px">

  <div class="panel panel-default">
    <div class="panel-body"><h2>Score:
    <c:choose>
  <c:when test="${production.prodScore() == 0}">
    No Rating
  </c:when>
  <c:when test="${production.prodScore() < 60}">
    <img src="${context}/images/bad.png" style="width:25px"> ${production.prodScore()}%
  </c:when>
  <c:otherwise>
    <img src="${context}/images/good.png" style="width:25px"> ${production.prodScore()}%
  </c:otherwise>
	</c:choose>
     </h2>
     <h5>${count} User Rating<c:if test="${count>1}">s</c:if></h5>
<jsp:useBean id="now" class="java.util.Date" />   
<c:if test="${(production.prodRelease lt now) && (user.userType == 'fan')}">   
    <input type="hidden" id="userId" value="${user.userId}">			
	<input type="hidden" id="prodId" value="${production.prodId}">	
	<input type="hidden" id="ratingId">
      Rate this Production
      <span class="star-rating">
  <input type="radio" name="rating" value="20"><i></i>
  <input type="radio" name="rating" value="40"><i></i>
  <input type="radio" name="rating" value="60"><i></i>
  <input type="radio" name="rating" value="80"><i></i>
  <input type="radio" name="rating" value="100"><i></i>
</span><br><br>
      Post a Review <textarea id="review" name="myTextBox" cols="30" rows="8" maxlength="250" placeholder="Maximum of 250 characters"></textarea>
   <br><br>
   <button type="button" class="btn btn-warning" id="saverating">Save
  </button></a>
</c:if>  
    </div>
  </div>

</div>

</div> <!--end of body-->

</div> <!--end of inner-->

</div> <!--end of wrapper-->

</body>
</html>