<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Редактирование рейса"/>
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
                    <li class="breadcrumb-item">Рейсы</li>
                    <li class="breadcrumb-item active">Редактирование рейса</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h1>Редактирование рейса</h1>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="tripUpdate" class="form-horizontal">
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <label>Рейс: ${trip.route.name}</label><br/>
                                            <label>Отправление: ${trip.startDate}</label>
                                            <input type="hidden" name="id" id="id" value="${trip.id}" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <label for="canceled" class="col-sm-2">Рейс отменен</label>
                                            <input type="checkbox" name="canceled" class="checkbox-inline" id="canceled" <c:if test="${trip.canceled == true}">checked="checked"</c:if>>
                                            <input type="hidden" name="canceled" value="false"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="delay" class="col-sm-2">Задержка рейса</label>
                                        <div class="col-sm-10">
                                            <input type="number" name="delay" id="delay" value="${trip.delay}" min="0">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="submit" class="btn btn-primary" value="Сохранить"/>
                                        </div>
                                    </div>
                                </form>
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