<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Weapon Type</title>
</head>
<body>

	<h1>Create Weapon Type</h1>
	<form action="createweapontype" method="post">
		<p>
			<label for="weapontype">Weapon Type</label>
			<input id="weapontype" name="weapontype" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<br/><br/>
	
	<h1>Delete Weapon Type By Id</h1>
	<form action="deleteweapontypes" method="post">
		<p>
			<div<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="weapontypeid">Weapon Type Id</label>
				<input id="weapontypeid" name="weapontypeid" value="${fn:escapeXml(param.weapontypeid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.title}</b></span>
	</p>
	
	
	<h1>Update Weapon Type</h1>
	<form action="updateweapontypes" method="post">
		<p>
			<label for="weapontypeid">Weapon Type Id</label>
			<input id="weapontypeid" name="weapontypeid" value="${fn:escapeXml(param.weapontypeid)}">
		</p>
		<p>
			<label for="newweapontype">New Weapon Type</label>
			<input id="newweapontype" name="newweapontype" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

 	<form action="findweapontypes" method="post">
		<h1>Search for a Weapon Type By Id</h1>
		<p>
			<label for="weapontypeid">Weapon Type Id</label>
			<input id="weapontypeid" name="weapontypeid" value="${fn:escapeXml(param.weapontypeid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.updatesuccess}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	
	
	<h1>Matching Weapon Type</h1>
        <table border="1">
            <tr>
                <th>Weapon Type Id</th>
                <th>Weapon Type</th>
            </tr>
            <c:forEach items="${weapontypes}" var="weapontype" >
                <tr>
                    <td><c:out value="${weapontype.getWeaponTypeId()}" /></td>
                    <td><c:out value="${weapontype.getWeaponType()}" /></td>
                    <%-- <td><a href="terroristevents?weapontypeid=<c:out value="${weaponType.getWeaponTypeId()}"/>">UsedIn</a></td> --%>
                </tr>
            </c:forEach>
       </table>

</body>
</html>