<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>AnyWayTicket</title>
        <link href="resources\css\index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <div class="header">
        <div>AnyWayTicket - any day, any way.</div>
        <div>login/logout will be here. --->>>></div>
        <div>
            <a href="<c:url value="/logout" />">Logout</a>
        </div>
    </div>
    <div class="sidebar">
        <div class="side-menu">
            <ul class="side-menu-ul">
                <li>
                    <a href="tripSearch">Поиск рейса</a>
                </li>
                <li>
                    <a href="scheduleOfStation">Расписание</a>
                </li>
                <li class="active">
                    <a href="station">Станции</a>
                </li>
                <li>
                    <a href="route">Маршруты</a>
                </li>
                <li>
                    <a href="trip">Рейсы</a>
                </li>
                <li>
                    <a href="passenger">Пассажиры рейса</a>
                </li>
                <li>
                    <a href="user">Пользователи</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="content">
        <h1>Main Page</h1>
    </div>
    </body>
</html>