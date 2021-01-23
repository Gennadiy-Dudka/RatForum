<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>MyProject ImageBoard</title>
    <link href="${pageContext.request.contextPath}/styles/mainStyle.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,500&display=swap" rel="stylesheet">
</head>
<body>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" var="currentUsername"/>
    </sec:authorize>
    <jsp:include page="_header.jsp"/>
    <div class="big-block">
        <p>User: ${topic.userAccount.username }</p>
        <p>Creation Date: ${topic.formatDate }</p>
        <h3>${topic.theme}</h3>
        <c:if test="${image != null}">
            <img src="data:image/jpg;base64,${image}" width="300" alt="topic image"/>
        </c:if>
        <div>${topic.value}</div>
        <c:if test="${currentUsername == topic.userAccount.username}">
            <form:form method="POST" action="${pageContext.request.contextPath}/topic/delete/${topic.topicId}">
                <input type="submit" value="DELETE"/>
            </form:form>
        </c:if>
    </div>
    <div class="big-block">
        <p align="center">Comments</p>
        <form:form method="POST" action="${pageContext.request.contextPath}/topic/createComment/${topic.topicId}">
            <textarea name="value" cols="40" rows="5">${comment.value }</textarea>
            <input type="submit" value = "Send Comment"/>
        </form:form>
        <c:forEach items="${comments}" var="com">
            <div class="comment-block">
                <p>User: ${com.userAccount.username} </p>
                <p>Creation Date: ${com.formatDate} </p>
                <div>${com.value}</div>
                <c:if test="${currentUsername == com.userAccount.username}">
                    <form:form method="POST" action="${pageContext.request.contextPath}/topic/deleteComment/${com.commentId}">
                        <input type="submit" value="DELETE"/>
                    </form:form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</body>
</html>