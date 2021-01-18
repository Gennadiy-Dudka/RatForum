<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "big-block">
    <div class = "header-block">
        <a href="${pageContext.request.contextPath}/home" class = "logo-text">MyProject ImageBoard</a>
        <div class = "under-logo-text">
            <c:choose>
                <c:when test="${loginedUser != null}">
                    Hello ${loginedUser.userName}
                    <a href="${pageContext.request.contextPath}/logOut">Log out</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/logIn">Log in</a>
                    <a href="${pageContext.request.contextPath}/signUp">Sign up</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>