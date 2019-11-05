<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Редактирование маршрута"/>
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
                    <li class="breadcrumb-item active">Редактирование маршрута</li>
                </ul>
            </div>
        </div>

        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Редактирование маршрута</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="updateRoute">
                                    <input type="hidden" name="id" id="id" value="${route.getId()}" readonly/>
                                    <div class="form-group row">
                                        <label for="name">Номер поезда</label><br>
                                        <input type="text" name="number" class="form-control" id="number"
                                               value="${route.getNumber()}" required/>
                                    </div>
                                    <div class="form-group row">
                                        <label for="name">Название поезда</label><br>
                                        <input type="text" name="name" class="form-control" id="name" value="${route.getName()}" required/>
                                    </div>
                                    <div class="form-group row">
                                        <label for="name">Периодичность рейсов</label><br>
                                        <input type="text" name="pattern" class="form-control" id="pattern"
                                               value="${route.getSchedulePattern()}"/>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="submit" class="btn btn-primary" value="Изменить"/>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>
</html>