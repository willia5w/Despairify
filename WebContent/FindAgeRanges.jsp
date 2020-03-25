<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find an Age Range</title>
</head>
<body>
	<form action="findageranges" method="post">
		<h1>Search for an AgeRange by AgeRange</h1>
		<p>
			<label for="agerange">AgeRange</label>
			<input id="agerange" name="agerange" value="${fn:escapeXml(param.agerange)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
 	<div id="agerangecreate"><a href="agerangecreate">Create Age Range</a></div>
	<br/>
	<h1>Matching Age Range</h1>
        <table border="1">
            <tr>
                <th>AgeRangeId</th>
                <th>AgeRange</th>
                <th>Suicides</th>
                <th>Delete AgeRange</th>
                <th>Update AgeRange</th>
            </tr>
            <c:forEach items="${ageRanges}" var="ageRange" >
                <tr>
                    <td><c:out value="${ageRange.getAgeRangeId()}" /></td>
                    <td><c:out value="${ageRange.getAgeRange()}" /></td>
                    <td><a href="agerangesuicides?agerangeid=<c:out value="${ageRange.getAgeRangeId()}"/>">Suicides</a></td>
                    <td><a href="agerangedelete?agerangeid=<c:out value="${ageRange.getAgeRangeId()}"/>">Delete</a></td>
                    <td><a href="agerangeupdate?agerangeid=<c:out value="${ageRange.getAgeRangeId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
