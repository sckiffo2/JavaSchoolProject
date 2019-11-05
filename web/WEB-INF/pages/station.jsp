<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Станции"/>
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
                    <li class="breadcrumb-item active">Станции</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h1>Станции</h1>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="station/save" class="form-horizontal">
                                    <div class="form-group row">
                                        <label for="name" class="col-sm-2">Добавление новой станции</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" id="name" class="form-control" placeholder="Введите название станции" required pattern="^.{3,30}$"/>
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
                                                    <label for="table" class="col-sm-2"><h4>Зарегистрированные станции</h4></label>
                                                    <table id="table" class="table table-striped table-sm">
                                                        <tr>
                                                            <th>id</th>
                                                            <th>Название</th>
                                                            <th></th>
                                                        </tr>
                                                        <c:forEach var="station" items="${stations}">
                                                            <tr>

                                                                <td>
                                                                    <c:out value="${station.getId()}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${station.getName()}"/>
                                                                </td>
                                                                <td>
                                                                    <a href="station\edit\<c:url value="${station.getId()}"/>">Изменить</a>
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