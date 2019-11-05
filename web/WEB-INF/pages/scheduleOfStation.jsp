<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Расписание по станции"/>
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
                    <li class="breadcrumb-item active">Расписание</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Расписание по станции</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="${pageContext.request.contextPath}/scheduleOfStation" class="form-horizontal">
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <input list="stations" id="station" name="name" class="form-control" placeholder="Выберите станцию" required/>
                                            <datalist id="stations">
                                                <c:forEach var="station" items="${stations}">
                                                    <option value="${station.getName()}"></option>
                                                </c:forEach>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="date" class="col-sm-2">Дата</label>
                                        <div class="col-sm-10">
                                            <input type="date" name="stringDate" class="form-control" id="date" required>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-10">
                                        <input type="submit" class="btn btn-primary" value="Поиск"/>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <c:if test="${schedule != null}">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <label for="table" class="col-sm-2">Расписание станции: ${station} на ${date}</label><br>
                                                        <table id="table" class="table table-striped table-sm">
                                                            <tr>
                                                                <th>№ Поезда</th>
                                                                <th>Название поезда</th>
                                                                <th>Время прибытия</th>
                                                                <th>Время отправления</th>
                                                                <th></th>
                                                            </tr>
                                                            <c:forEach var="scheduleRow" items="${schedule}">
                                                                <tr>
                                                                    <td>
                                                                        <c:out value="${scheduleRow.routeNumber}"/>
                                                                    </td>
                                                                    <td>
                                                                        <c:out value="${scheduleRow.routeName}"/>
                                                                    </td>
                                                                    <td>
                                                                            ${scheduleRow.getArrivalTimeString()}
                                                                    </td>
                                                                    <td>
                                                                            ${scheduleRow.getDepartureTimeString()}
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
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