<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="side-bar">
    <div class="train">
    </div>
    <ul class="menu-ul">
        <li>
            <a href="/AnyWayTicket/tripSearch">Поиск рейса</a>
        </li>
        <li>
            <a href="/AnyWayTicket/scheduleOfStation">Расписание</a>
        </li>
        <sec:authorize access="hasRole('MANAGER')">
            <li>
                <a href="/AnyWayTicket/station">Станции</a>
            </li>
            <li>
                <a href="/AnyWayTicket/route">Маршруты</a>
            </li>
            <li>
                <a href="/AnyWayTicket/trip">Рейсы</a>
            </li>
            <li>
                <a href="/AnyWayTicket/passenger">Пассажиры рейса</a>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <li>
                <a href="/AnyWayTicket/user">Пользователи</a>
            </li>
        </sec:authorize>
    </ul>
</div>