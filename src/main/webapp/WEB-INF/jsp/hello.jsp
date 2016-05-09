<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>:: sample - brainage.net ::</title>
</head>

<body>

<h1>Hello <b><c:out value="${pageContext.request.remoteUser}" /></b></h1>

<form action="<c:url value="/logout" />" method="post">
    <input type="submit" value="Sign Out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

</body>

</html>
