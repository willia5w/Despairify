<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update an Age Range</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="agerangeupdate" method="post">
		<p>
			<label for="agerangeid">AgeRangeId</label>
			<input id="agerangeid" name="agerangeid" value="${fn:escapeXml(param.agerangeid)}">
		</p>
		<p>
			<label for="newagerange">New AgeRange</label>
			<input id="newagerange" name="newagerange" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>