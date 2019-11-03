<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

    <c:set var="title" value="Бронирование билета" />
    <jsp:include page="parts/head.jsp">
        <jsp:param name="title" value="${title}"/>
    </jsp:include>
    <body>
        <jsp:include page="parts/sidemenu.jsp"/>

        <div class="main">
            <jsp:include page="parts/header.jsp"/>
            <div class="info">
                <div class="breadcrumb-holder">
                    <div class="container-fluid">
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/AnyWayTicket/">Главная</a></li>
                            <li class="breadcrumb-item">Поиск рейса</li>
                            <li class="breadcrumb-item active"> Бронирование билета </li>
                        </ul>
                    </div>
                </div>
                    <section class="forms">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <c:forEach var="wagonPlcaes" items="${tripPlaces}" varStatus="loop" >
                                                <p>Вагон №<c:out value="${loop.count}" /> </p>
                                                <c:forEach var="place" items="${wagonPlcaes}" >
                                                    <a href="bookTicket/<c:out value="${loop.count}/${place}" />">${place}</a>&nbsp;
                                                </c:forEach>
                                            </c:forEach>
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