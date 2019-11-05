<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Маршруты"/>
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
                    <li class="breadcrumb-item active">Маршруты</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Маршруты</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="route\save" class="form-horizontal">
                                    <div class="form-group row">
                                        <label for="name" class="col-sm-2">Добавление нового поезда</label><br>
                                        <div class="col-sm-10">
                                            <div class="form-group row">
                                                <input type="text" name="number" id="number" class="form-control"
                                                       placeholder="Введите номер поезда" required/>
                                            </div>
                                            <div class="form-group row">
                                                <input type="text" name="name" id="name" class="form-control"
                                                       placeholder="Введите название поезда" required
                                                       pattern="^.{3,50}$"/>
                                            </div>
                                            <div class="form-group row">
                                                <input type="text" name="pattern" id="pattern" class="form-control"
                                                       placeholder="Введите тип расписания"/>
                                            </div>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <input type="submit" class="btn btn-primary" value="Добавить"/>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="table-responsive">
                                                    <label for="table" class="col-sm-2"><h4>Список поездов</h4>
                                                    </label><br>
                                                    <table id="table" class="table table-striped table-sm">
                                                        <tr>
                                                            <th>id</th>
                                                            <th>Номер</th>
                                                            <th>Название поезда</th>
                                                            <th>Периодичность</th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                        <c:forEach var="route" items="${routes}">
                                                            <tr>
                                                                <td>
                                                                    <c:out value="${route.getId()}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${route.getNumber()}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${route.getName()}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${route.getSchedulePattern()}"/>
                                                                </td>
                                                                <td>
                                                                    <a href="route\edit\<c:url value="${route.getId()}"/>">Редактировать</a>
                                                                </td>
                                                                <td>
                                                                    <a href="route\editStations\<c:url value="${route.getId()}"/>">Добавить
                                                                        станции</a>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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