<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Country Operations</title>
</head>
<body>

<h1>Create Country</h1>
<form action="countrycreate" method="post">
    <p>
        <label for="countryalpha3codecreate">Country Alpha 3 Code</label>
        <input id="countryalpha3codecreate" name="countryalpha3code" value="">
    </p>
    <p>
        <label for="countryname">Country Name</label>
        <input id="countryname" name="countryname" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessagecreate"><b>${messages.success}</b></span>
</p>

<h1>Delete Country</h1>
<form action="countrydelete" method="post">
    <p>
    <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
        <label for="countryalpha3codedelete">AgeRangeId</label>
        <input id="countryalpha3codedelete" name="countryalpha3code" value="${fn:escapeXml(param.countryalpha3code)}">
    </div>
    </p>
    <p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
    </p>
</form>
<br/><br/>

<h1>Update Country</h1>
<form action="countryalpha3code" method="post">
    <p>
        <label for="countryalpha3codeupdate">CountryAlpha3Code</label>
        <input id="countryalpha3codeupdate" name="countryalpha3code" value="${fn:escapeXml(param.countryalpha3code)}">
    </p>
    <p>
        <label for="newcountryname">New CountryName</label>
        <input id="newcountryname" name="newcountryname" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessageupdate"><b>${messages.success}</b></span>
</p>



</body>
</html>