<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="AnyWayTicket"/>
<jsp:include page="parts/head.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>
<body>
<jsp:include page="parts/sidemenu.jsp"/>

<div class="main">
    <jsp:include page="parts/header.jsp"/>
    <div class="info">
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h1>Добро пожаловать!</h1>
                                <p> У нас самые лучшие билеты куда угодно!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</body>
</html>