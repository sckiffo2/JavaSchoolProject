<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

    <c:set var="title" value="Рейсы" />
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
                            <li class="breadcrumb-item active">Рейсы</li>
                        </ul>
                    </div>
                </div>
                <section class="forms">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card">
                                    <div class="card-header d-flex align-items-center">
                                        <h1>Рейсы</h1>
                                    </div>
                                    <div class="card-body">
                                        <form method="POST" action="trip\add" class="form-horizontal">
                                            <div class="form-group row">
                                            <div class="col-sm-10">
                                            <input list="routes" id="route" name="routeNumber" class="form-control" placeholder="Выберите поезд" required/>
                                            <datalist id="routes">
                                                <c:forEach var="route" items="${routes}" >
                                                    <option value="${route.number}">${route.name}</option>
                                                </c:forEach>
                                            </datalist>
                                            </div>
                                            </div>
                                            <div class="form-group row">
                                            <label for="date">Дата</label>
                                            <div class="col-sm-10">
                                            <input type="date" class="form-control" name="date" id="date" required>
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
                                        <label for="table" class="col-sm-2"><h4>Рейсы</h4></label><br>
                                        <table id="table" class="table table-striped table-sm">
                                            <tr>
                                                <th>Номер</th>
                                                <th>Название поезда</th>
                                                <th>Дата отправления</th>
                                            </tr>
                                            <c:forEach var="trip" items="${trips}" >
                                                <tr>
                                                    <td>
                                                        <c:out value="${trip.getRoute().getNumber()}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${trip.getRoute().getName()}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${trip.getStartDate()}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${route.getSchedulePattern()}" />
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