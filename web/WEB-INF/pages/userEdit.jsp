<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:set var="title" value="Редактирование аккаунта"/>
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
                    <li class="breadcrumb-item">Пользователи</li>
                    <li class="breadcrumb-item active">Редактирование аккаунта</li>
                </ul>
            </div>
        </div>
        <section class="forms">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header d-flex align-items-center">
                                <h4>Редактирование аккаунта</h4>
                            </div>
                            <div class="card-body">
                                <form method="POST" action="updateUser" class="form-horizontal">
                                    <div class="form-group row">
                                        <label for="username" class="col-sm-2">Пользователь</label>
                                        <div class="col-sm-10">
                                            <input type="text" name="username" class="form-control" id="username" value="${user.username}" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="mail" class="col-sm-2">Почта</label><br>
                                        <div class="col-sm-10">
                                            <input type="email" name="mail" class="form-control" id="mail" value="${user.mail}" required/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="active" class="col-sm-2">Аккаунт активен</label>
                                        <div class="col-sm-10">
                                            <input type="checkbox" name="active" class="checkbox-inline" id="active" <c:if test="${user.active == true}">checked="checked"</c:if>>
                                            <input type="hidden" name="active" value="false"/>
                                            <br>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-10">
                                            <input type="submit" class="btn btn-primary" value="Сохранить"/>
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
