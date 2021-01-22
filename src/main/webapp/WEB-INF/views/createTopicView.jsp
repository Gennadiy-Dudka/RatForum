<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>MyProject ImageBoard</title>
    <link href="${pageContext.request.contextPath}/styles/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,500&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="big-block">
    <form:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/topic/create">
        <table border=0>
            <tr>
                <td>Theme</td>
                <td><textarea name="theme" cols="40" rows="3" >${topic.theme }</textarea> </td>
            </tr>
            <tr>
                <td>Body</td>
                <td><textarea name="value" cols="40" rows="5" >${topic.value }</textarea> </td>
            </tr>
            <tr>
                <td>Image</td>
                <td><input type="file" name="imageFile" accept=".jpg, .jpeg, .png"></td>
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