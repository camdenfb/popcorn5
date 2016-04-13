<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>

 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

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


<body>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<div id="wrapper" style="width:100%; float:left">

<div style="background:#660033; width:40%; margin-left:30%; margin-right:30%; margin-top:5%; border-radius:15px; padding:5% 10% 5% 10%">

<img src="${context}/images/popborder.png" style="height:100px; margin-left:18%; margin-bottom:10%">
<br><br>
	<c:set var="context" value="${pageContext.request.contextPath}"/>
	<form action = "${context}/authenticate" method="post">
	<table>
          <tr>
		 <td style="color:white; letter-spacing: 3px">E-mail </td>
		<td><input type="text" name="email"></td>
		</tr>
		<tr>
           <td style="color:white; letter-spacing: 3px">Password </td>
          <td><input type="password" name="password"></td>
         </tr> 
         <tr>
          <td>
          <br><input type="submit" name="submitButton" value="Login">
          </td>
          </tr>
       </table>   
      </form>
     <a href="${context}/registerpage"><h6>Not a member yet? Sign up for an account!</h6></a>     
</div>

</div> <!-- END WRAPPER -->

       
</body>
</html>