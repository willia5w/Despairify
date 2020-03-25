<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suicides</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>CountryName</th>
                <th>Year</th>
                <th>Sex</th>
                <th>Age Range</th>
                <th>Population</th>
                <th># of Suicides</th>
            </tr>
            <c:forEach items="${suicides}" var="suicide" >
                <tr>
                    <td><c:out value="${suicide.getCountryName()}" /></td>
                    <td><c:out value="${suicide.getYear()}" /></td>
                    <td><c:out value="${suicide.getSex().getSex()}" /></td>
                    <td><c:out value="${suicide.getAgeRange().getAgeRange()}" /></td>
                    <td><c:out value="${suicide.getPopulation()}" /></td>
                    <td><c:out value="${suicide.getSuicides()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
