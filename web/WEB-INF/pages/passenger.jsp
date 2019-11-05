<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Пассажиры"/>
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
                    <li class="breadcrumb-item active">Пассажиры</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Пассажиры</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="getPassengers" class="form-horizontal">
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <input list="routes" id="route" name="number" class="form-control" placeholder="Выберите поезд" required/>
                                            <datalist id="routes">
                                                <c:forEach var="route" items="${routes}">
                                                    <option value="${route.number}">${route.name}</option>
                                                </c:forEach>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="date" class="col-sm-2"> Время прибытия</label>
                                        <div class="col-sm-10">
                                            <input type="date" name="date" class="form-control" id="date" required>
                                        </div>
                                    </div>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="submit" class="btn btn-primary" value="Поиск"/>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <c:if test="${passengers != null}">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <label for="table"><h4>Пассажиры зарегистрированные на поезд:</h4></label><br>
                                                        <table id="table" class="table table-striped table-sm">
                                                            <tr>
                                                                <th>Имя</th>
                                                                <th>Фамилия</th>
                                                            </tr>
                                                            <c:forEach var="passenger" items="${passengers}">
                                                                <tr>
                                                                    <td>
                                                                        <c:out value="${passenger.firstName}"/>
                                                                    </td>
                                                                    <td>
                                                                        <c:out value="${passenger.lastName}"/>
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