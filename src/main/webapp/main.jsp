<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="<c:url value="jsp/javascript.js" />"></script>
<style>
   <%@include file='jsp/style.css' %>
</style>
<title>MainPage</title>
</head>
<body>
<div>
<c:if test="${checked == false}">
   <script> alert ("Please select just one row to be edited") </script>
</c:if>
<p align="center"> <b> All users list </b> </p>
<form name="mainaction" method="post" action="/AkvelonServlet/action">
 <table>
        <thead>
            <tr>
                <th>Check</th>
                <th>User Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Login</th>
                <th>Birthday</th>
                <th>Balance</th>
                </tr>
        </thead>
        <tbody>
            <c:forEach items="${ulist}" var="user">
                <tr>
                    <td><input type="checkbox" name="maincheck" value="${user.id}"></td>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.fname}" /></td>
                    <td><c:out value="${user.lname}" /></td>
                    <td><c:out value="${user.login}" /></td>
                    <td><c:out value="${user.birthday}" /></td>
                    <td><c:out value="${user.balance}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p align="center"> <input class="button" type="submit" name="mainbutton" value="Edit" > <input class="button" type="submit" name="mainbutton" value="Delete"> <input class="button" type="submit" name="mainbutton" value="Refresh"> </p>
    </form>
</div>
</body>
</html>