<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

    <c:set var="title" value="Регистрация пассажира" />
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
                            <li class="breadcrumb-item active">Регистрация пассажира</li>
                        </ul>
                    </div>
                </div>

                <section class="forms">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card">
                                    <div class="card-header d-flex align-items-center">
                                        <h4>Пассажир</h4>
                                    </div>
                                    <div class="card-body">
                                        <form method="POST" action="registerPassenger" class="form-horizontal">
                                            <h6>Билет забронирован на 10 минут, завершите регистрацию на рейс за отведенное время</h6>
                                            <div class="form-group row">
                                            <label for="firstName" class="col-sm-2">Имя</label>
                                            <div class="col-sm-10">
                                            <input type="text" name="firstName" id="firstName" pattern="[А-Яа-яA-Za-z-]{2,15}" required class="form-control form-control-success">
                                            </div>
                                            </div>
                                            <div class="form-group row">
                                            <label for="lastName" class="col-sm-2">Фамилия</label>
                                            <div class="col-sm-10">
                                            <input type="text" name="lastName" id="lastName" pattern="[А-Яа-яA-Za-z-]{2,15}" required class="form-control">
                                            </div>
                                            </div>
                                            <div class="form-group row">
                                            <label for="date" class="col-sm-2">Дата рождения</label>
                                            <div class="col-sm-10">
                                            <input type="date" name="birthDate" id="date" required class="form-control">
                                            </div>
                                            </div>
                                            <div class="form-group row">
                                            <div class="i-checks">
                                            <input type="radio" name="gender" value="M" checked>Мужской
                                            <input type="radio" name="gender" value="F">Женский
                                            </div>
                                            </div>
                                            <div class="form-group row">
                                            <div class="col-sm-10 offset-sm-2">
                                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                            <input type="submit" value="Зарегистрировать" class="btn btn-primary">
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