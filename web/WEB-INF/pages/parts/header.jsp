<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
    <div class="title">Any day - any way!</div>
    <div class="login">
        <sec:authorize var="loggedIn" access="isAuthenticated()" />
        <c:choose>
            <c:when test="${loggedIn}">
                <sec:authentication property="principal.username" />
                <a href="/AnyWayTicket/logout"/>Logout</a>
            </c:when>
            <c:otherwise>
                <a href="/AnyWayTicket/login"/>Login</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>