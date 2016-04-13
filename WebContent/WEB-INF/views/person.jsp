<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Person Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<div id="container">
	<h1>Add a Person</h1>
	<c:set var="context" value="${pageContext.request.contextPath}"/>
	<form:form action="${context}/person/add" modelAttribute="person">
		<table>
			<c:if test="${!empty person.name}">
				<tr>
					<td><form:label path="id">
							<spring:message text="ID" />
						</form:label></td>
					<td><form:input path="id" readonly="true" size="8"
							disabled="true" /> <form:hidden path="id" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="country">
						<spring:message text="Country" />
					</form:label></td>
				<td><form:input path="country" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty person.name}">
						<input type="submit" value="<spring:message text="Save Person"/>" />
					
					</c:if> <c:if test="${empty person.name}">
						<input type="submit" value="<spring:message text="Add New Person"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<h1>Persons List</h1>
	<c:if test="${!empty listPersons}">
		<table class="tg">
			<tr>
				<th width="80">Person ID</th>
				<th width="120">Person Name</th>
				<th width="120">Person Country</th>
				<th width="60" colspan="2">Action</th>
			</tr>
			
			<c:forEach items="${listPersons}" var="person">
				<tr>
					<td>${person.id}</td>
					<td>${person.name}</td>
					<td>${person.country}</td>
					<td><a href="<c:url value='person/edit/${person.id}' />">
					  	<img src="${context}/images/edit.png" style="border-style: none;" /></a></td>
					<td><a href="<c:url value='person/remove/${person.id}' />">
						<img src="${context}/images/delete.png" style="border-style: none;" /></a></td>
					
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
</body>
</html>