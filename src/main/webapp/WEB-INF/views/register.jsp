<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="${pageContext.request.contextPath}/styles/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,500&display=swap" rel="stylesheet">
    <title>MyProject ImageBoard</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="big-block">
    <h3>Sign up Page</h3>
    <p class="errorMessage">${error}</p>
    <form:form method="POST" action="${pageContext.request.contextPath}/register">
        <table border=0>
            <tr>
                <td>User Name</td>
                <td><input type="text" name="username" value="${user.username}"/> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value="${user.password}"/> </td>
            </tr>
            <tr>
                <td>Confirm Password</td>
                <td><input type="password" name="confirmPassword"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value = "Submit"/>
                    <a href="${pageContext.request.contextPath}/">Cancel</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>