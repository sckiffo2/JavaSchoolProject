<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Поиск рейса"/>
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
                    <li class="breadcrumb-item active">Поиск рейса</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Поиск рейса</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="${pageContext.request.contextPath}/tripSearch"
                                      class="form-horizontal">
                                    <div class="form-group row">
                                        <label for="station1">Станция отправления</label>
                                        <div class="col-sm-10">
                                            <input list="stations1" id="station1" name="departureStation"
                                                   class="form-control" placeholder="Выберите станцию отправления"
                                                   required/>
                                            <datalist id="stations1">
                                                <c:forEach var="station" items="${stations}">
                                                    <option value="${station.getName()}"></option>
                                                </c:forEach>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="station2">Станция прибытия</label>
                                        <div class="col-sm-10">
                                            <input list="stations2" id="station2" name="arrivalStation"
                                                   class="form-control" placeholder="Выберите станцию прибытия"
                                                   required/>
                                            <datalist id="stations2">
                                                <c:forEach var="station" items="${stations}">
                                                    <option value="${station.getName()}"></option>
                                                </c:forEach>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="date" class="col-sm-2"> Дата отправления</label>
                                        <div class="col-sm-10">
                                            <input type="date" name="stringDate" class="form-control" id="date"
                                                   required>
                                        </div>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <div class="col-sm-10">
                                        <input type="submit" class="btn btn-primary" value="Поиск"/>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="table-responsive">
                                                    <table id="table" class="table table-striped table-sm">
                                                        <tr>
                                                            <th>№ Поезда</th>
                                                            <th>Название поезда</th>
                                                            <th>Отправление</th>
                                                            <th>Прибытие</th>
                                                            <th></th>
                                                        </tr>
                                                        <c:forEach var="dto" items="${TicketScheduleDTO}">
                                                        <tr>
                                                            <td>
                                                                <c:out value="${dto.routeNumber}"/>
                                                            </td>
                                                            <td>
                                                                <c:out value="${dto.routeName}"/>
                                                            </td>
                                                            <td>
                                                                <c:out value="${dto.getDepartureTimeString()}"/>
                                                            </td>
                                                            <td>
                                                                <c:out value="${dto.getArrivalTimeString()}"/>
                                                            </td>
                                                            <td>
                                                                <a href="freeplaces/<c:out value="${dto.tripId}" />">Забронировать
                                                                    билет</a>
                                                            </td>
                                                        </tr>
                                                        </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </table>
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