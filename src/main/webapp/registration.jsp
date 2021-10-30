<%--
  Created by IntelliJ IDEA.
  User: sonya
  Date: 30.10.2021
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>

<c:if test="${violations != null}">
    <c:forEach items="${violations}" var="violation">
        <p>${violation}.</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <label for="firstname">Name:</label>
    <input type="text" name="firstname" id="firstname" value="${firstname}">
    <label for="lastname">Last Name:</label>
        <input type="text" lastname="lastname" id="lastname" value="${lastname}">
        <label for="username">Username: </label>
        <input type="text" name="username" id="username" value="${email}">
        <input type="submit" name="signup" value="Sign Up">
</form>
</body>
</html>
