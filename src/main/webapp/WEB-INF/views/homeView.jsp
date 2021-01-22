<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
    <a href="${pageContext.request.contextPath}/topic/create" class="button-link">Create New Thread</a>
    <div class="thread-container">
        <c:forEach items="${topics}" var="topic">
            <div class="thread-block">
                <p>Creation date: ${topic.formatDate}</p>
                <p><b>${topic.theme}</b></p>
                <p>
                        ${fn:substring(topic.value, 0, 25)}
                    <c:if test="${fn:length(topic.value)> 25}">
                        ...
                    </c:if>
                </p>
                <a href="${pageContext.request.contextPath}/topic/${topic.topicId}">read thread...</a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>