<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Пользователи"/>
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
                    <li class="breadcrumb-item active">Пользователи</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Пользователи</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="user/save" class="form-horizontal">
                                    <div class="form-group row">
                                        <label for="username" class="col-sm-2"><h4>Добавление нового пользователя</h4>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="form-group row">
                                                <input type="text" name="username" class="form-control" id="username" placeholder="Введите имя пользователя"
                                                       pattern="[A-Za-z1-9_]{6,20}" title="6-20 символов, латинские буквы, цыфры, подчеркивание"/>
                                            </div>
                                            <div class="form-group row">
                                                <input type="password" name="password" class="form-control" id="password" placeholder="Введите пароль"
                                                       pattern="^.{6,20}$" title="6-20 символов"/>
                                            </div>
                                            <div class="form-group row">
                                                <input type="text" name="mail" class="form-control" id="mail" placeholder="Введите eMail"
                                                       pattern="[^@]+@[^\.]+\..+" title="user@example.com"/>
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
                                                    <label for="table" class="col-sm-2"><h4>Имеющиеся пользователи</h4>
                                                    </label>
                                                    <table id="table" class="table table-striped table-sm">
                                                        <tr>
                                                            <th>id</th>
                                                            <th>Имя пользователя</th>
                                                            <th>mail</th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                        <c:forEach var="user" items="${users}">
                                                            <tr>
                                                                <td>
                                                                    <c:out value="${user.id}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${user.username}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${user.mail}"/>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${user.userRoles}"/>
                                                                </td>
                                                                <td>
                                                                    <a href="user\edit\<c:url value="${user.getId()}"/>">Редактировать пользователя</a>
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