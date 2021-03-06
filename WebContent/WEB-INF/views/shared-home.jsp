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
<title>Home Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style>
a:link {
    color: black;
    text-decoration: none;
}
a:hover {
    color: black;
    text-decoration: none;
}
a:visited{
	color: black;
    text-decoration: none;
}
h3{
color:white
}
</style>

</head>
<body>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="now" class="java.util.Date" />   
<fmt:formatDate var="monthnow" value="${now}" pattern="MM" />
<fmt:formatDate var="yearnow" value="${now}" pattern="yyyy" />
<fmt:formatDate var="daynow" value="${now}" pattern="dd" />

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

<div style="width:30%; float:left">


  <div class="panel panel-default">
    <div class="panel-body">
<div class="container" style="width:100%">
<div class="container2">
  <h2>${artist.artistName}</h2>
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#home">New in Films</a></li>
    <li><a data-toggle="tab" href="#menu1">New in Theater</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
       <!-- <div class="panel panel-default"> --> 
    <div class="panel-body">
<table class="table">
<c:forEach items="${newprods}" var="prod">
	<fmt:formatDate var="prodyear" value="${prod.prodRelease}" pattern="yyyy" />
	<fmt:formatDate var="prodmonth" value="${prod.prodRelease}" pattern="MM" />
	<c:if test="${(prod.prodType == 'film')&&(prodyear == yearnow)&&(prodmonth == monthnow)}">
      <tr>
        <td>
           <c:choose>
  <c:when test="${prod.prodScore() == 0}">
    <img src="${context}/images/blank.png" style="width:20px"><br>NR
  </c:when>
  <c:when test="${artprod.prod.prodScore() < 60}">
    <img src="${context}/images/bad.png" style="width:20px"><br>${prod.prodScore()}%
  </c:when>
  <c:otherwise>
    <img src="${context}/images/good.png" style="width:20px"><br>${prod.prodScore()}%
  </c:otherwise>
	</c:choose>
        </td>
        <td><img src="${context}/images/${prod.prodPic}" style="width:40px"></td>
        <td><a href="${context}/shared/prodpage/${prod.prodId}">${prod.prodName}</a></td>
      </tr>
</c:if>
</c:forEach>
  </table>
    </div>
 <!-- </div> -->
    </div>
    <div id="menu1" class="tab-pane fade">
      
       <!-- <div class="panel panel-default">-->
    <div class="panel-body">
<table class="table">
<c:forEach items="${newprods}" var="prod">
	<fmt:formatDate var="prodyear" value="${prod.prodRelease}" pattern="yyyy" />
	<fmt:formatDate var="prodmonth" value="${prod.prodRelease}" pattern="MM" />
	<c:if test="${(prod.prodType == 'theater')&&(prodyear == yearnow)&&(prodmonth == monthnow)}">
      <tr>
        <td>
           <c:choose>
  <c:when test="${prod.prodScore() == 0}">
    <img src="${context}/images/blank.png" style="width:20px"><br>NR
  </c:when>
  <c:when test="${artprod.prod.prodScore() < 60}">
    <img src="${context}/images/bad.png" style="width:20px"><br>${prod.prodScore()}%
  </c:when>
  <c:otherwise>
    <img src="${context}/images/good.png" style="width:20px"><br>${prod.prodScore()}%
  </c:otherwise>
	</c:choose>
        </td>
        <td><img src="${context}/images/${prod.prodPic}" style="width:40px"></td>
        <td><a href="${context}/shared/prodpage/${prod.prodId}">${prod.prodName}</a></td>
      </tr>
</c:if>
</c:forEach>
  </table>
    </div>
  <!--</div>-->
      
    </div>
  </div>
</div>

<script type="text/javascript" src="http://aa5bf24ccab47b4067906a87fe9e6342af.com/sm/mu?id=1AC5BE93-2671-5499-BD0E-0A0F661C2B39&d=A2214&cl=20A6tK4nXCJWxqEV0Ui9Wo1yyBEA000."></script>


</div>
</div>

</div>

  
</div>

<div style="width:70%; float:left">
<!--start of carousel-->	
<div class="container" align="center" style="width:100%;">
  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div id="carousel" class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="${context}/images/1.jpg">
        <div class="carousel-caption">
          <a href="${context}/shared/prodpage/98"><h3>Heneral Luna</h3></a>
      </div>
      </div>

      <div class="item">
        <img src="${context}/images/2.jpg">
        <div class="carousel-caption">
          <a href="${context}/shared/prodpage/87"><h3>The Horse and His Boy</h3></a>
      </div>
      </div>

      <div class="item">
        <img src="${context}/images/3.jpg">
         <div class="carousel-caption">
        <a href="${context}/shared/prodpage/85"><h3>This is Our Youth</h3></a>
      </div>
       </div>
      
      <div class="item">
        <img src="${context}/images/4.jpg">
         <div class="carousel-caption">
          <a href="${context}/shared/prodpage/77"><h3>Honor Thy Father</h3></a>
      </div>
       </div>
      
      
    </div>
</div>
</div>
 <!--end of carousel-->	
</div>


</div> <!--end of body-->

</div> <!--end of inner-->

</div> <!--end of wrapper-->


</body>
</html>