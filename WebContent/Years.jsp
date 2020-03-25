<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>COUNTRY YEAR CRUD OPERATIONS</title>
</head>
<body>







<!-- CREATE -->
<h1>Create Year</h1>
	<form action="yearcreate" method="post">
		<p>
			<label for="createdyear">Year</label>
			<input id="createdyear" name="createdyear" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
<!-- END CREATE -->


    <!-- GET ALL YEARS -->
   
	<h1>Years</h1>
        <table border="1">
            <tr>
                <th>Year</th>
               
            </tr>
            <c:forEach items="${years}" var="year" >
                <tr>
                    <td><c:out value="${year.getYear()}" /></td>
                </tr>
            </c:forEach>
       </table>

       <!-- END GET ALL COUNTRYYEARS  -->


    <!-- DELETE  -->
    <div>
	<h1>${messages.title}</h1>
	<form action="yeardelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="yearname">Year</label>
				<input id="year" name="year" value="${fn:escapeXml(param.year)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
    <br/><br/>
    </div>
    <!-- END DELETE -->

 
	
</body>
</html>