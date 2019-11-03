<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<c:set var="title" value="Добавление станций в маршрут" />
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
                        <li class="breadcrumb-item"><a href="index.jsp">Главная страница</a></li>
                        <li class="breadcrumb-item"> Маршруты </li>
                        <li class="breadcrumb-item active">Добавление станций в маршрут</li>
                    </ul>
                </div>
            </div>
            <section class="forms">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-header d-flex align-items-center">
                                    <h4>Добавление станций в маршрут</h4>
                                </div>
                                <div class="card-body">

                                    <form  class="form-horizontal" method="POST" action="addStationToRoute">
                                        <div class="form-group row">
                                            <label class="col-sm-2">Добавьте станцию</label>
                                            <div class="col-sm-10">
                                                <input type="hidden" name="id" value="${id}"/>
                                                <input class="form-control form-control-success" list="stations" id="station" name="station" />
                                                <datalist id="stations">
                                                    <c:forEach var="station" items="${stations}" >
                                                        <option value="${station.name}"></option>
                                                    </c:forEach>
                                                </datalist>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="arrival" class="col-sm-2"> Время прибытия</label>
                                            <div class="col-sm-10">
                                                <input type="time" name="arrival" id="arrival" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="arrivalDay" class="col-sm-2">Дней в пути</label>
                                            <div class="col-sm-10">
                                                <input type="text" name="arrivalDay" id="arrivalDay" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="departure" class="col-sm-2">Время отправления</label>
                                            <div class="col-sm-10">
                                                <input type="time" name="departure" id="departure" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="departureDay" class="col-sm-2">Дней в пути</label>
                                            <div class="col-sm-10">
                                                <input type="text" name="departureDay" id="departureDay" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-10 offset-sm-2">
                                                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                                <input type="submit" value="Добавить" class="btn btn-primary">
                                            </div>
                                        </div>
                                    </form>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="card">
                                                <label for="table"><h4>Список остановок поезда</h4></label>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table id="table" class="table table-striped table-sm">
                                                            <tr>
                                                                <th>№ остановки</th>
                                                                <th>Станция</th>
                                                                <th>Прибытие</th>
                                                                <th>Отправление</th>
                                                            </tr>
                                                            <c:forEach var="station" items="${stationsOfRoute}" >
                                                            <tr>
                                                                <td>
                                                                    <c:out value="${station.getIndexInRoute()}" />
                                                                </td>
                                                                <td>
                                                                    <c:out value="${station.getStation().getName()}" />
                                                                </td>
                                                                <td>
                                                                    <c:out value="${station.getArrivalTimeToString()}" />
                                                                </td>
                                                                <td>
                                                                    <c:out value="${station.getDepartureTimeToString()}" />
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