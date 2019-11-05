<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

    <c:set var="title" value="Редактирование станции" />
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
                            <li class="breadcrumb-item">Станции</li>
                            <li class="breadcrumb-item active">Редактирование станции</li>
                        </ul>
                    </div>
                </div>
                <section class="forms">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card">
                                    <div class="card-header d-flex align-items-center">
                                        <h4>Изменение станции</h4>
                                    </div>
                                    <div class="card-body">
                                        <form method="POST" action="updateStation" class="form-horizontal">
                                            <div class="form-group row">
                                            <input type="hidden" name="id" id="id" value="${station.getId()}" readonly/>
                                            <label for="name" class="col-sm-2">Название станции</label>
                                            <div class="col-sm-10">
                                            <input class="form-control" type="text" name="name" id="name" value="${station.getName()}" required pattern="^.{3,30}$"/>
                                            </div>
                                            </div>
                                            <div class="form-group row">
                                            <div class="col-sm-10">
                                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                            <input type="submit" class="btn btn-primary" value="Изменить"/>
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
