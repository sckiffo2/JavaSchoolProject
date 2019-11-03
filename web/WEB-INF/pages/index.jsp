<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

    <c:set var="title" value="AnyWayTicket" />
    <jsp:include page="parts/head.jsp">
        <jsp:param name="title" value="${title}"/>
    </jsp:include>
    <body>
        <jsp:include page="parts/sidemenu.jsp"/>

        <div class="main">
            <jsp:include page="parts/header.jsp"/>
            <div class="info">
                <h1>Добро пожаловать!</h1>
                <p> У нас самые лучшие билеты куда угодно!</p>
            </div>
        </div>
    </body>
</html>