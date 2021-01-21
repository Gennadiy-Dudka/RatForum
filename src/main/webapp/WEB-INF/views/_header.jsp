<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class = "big-block">
    <div class = "header-block">
        <a href="${pageContext.request.contextPath}/home" class = "logo-text">MyProject ImageBoard</a>
        <div class = "under-logo-text">
            <sec:authorize access="isAuthenticated()">
                    Hello <sec:authentication property="principal.username"/>
                    <form:form action="/logout" method="post" cssStyle="display:inline;">
                        <input type="submit" value="logout"/>
                    </form:form>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a href="${pageContext.request.contextPath}/login">Log in</a>
                <a href="${pageContext.request.contextPath}/register">Sign up</a>
            </sec:authorize>
        </div>
    </div>
</div>