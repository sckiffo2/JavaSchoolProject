<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление станций в маршрут</title>
</head>
<body>
    <h1>Добавление станций в маршрут</h1>
    <br>
    <div>
        <form method="POST" action="addStationToRoute">
            <label>Добавление станции в маршрут</label>
            <br>
            <input type="hidden" name="id" value="${id}"/>
            <input list="stations" id="station" name="station" />
            <datalist id="stations">
                <c:forEach var="station" items="${stations}" >
                    <option value="${station.name}"></option>
                </c:forEach>
            </datalist>
            <br>
            <label for="arrival"> Время прибытия</label>
            <input type="time" name="arrival" id="arrival">
            <br>
            <label for="arrivalDay">Дней в пути</label>
            <input type="text" name="arrivalDay" id="arrivalDay">
            <br>
            <label for="departure">Время отправления</label>
            <input type="time" name="departure" id="departure">
            <br>
            <label for="departureDay">Дней в пути</label>
            <input type="text" name="departureDay" id="departureDay">
            <br>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
            <input type="submit" value="Добавить"/>
        </form>
    </div>
    <%----%>
    <%----%>
    <%----%>
    <%----%>
    <%----%>
    <%----%>
    <label for="table">Список остановок поезда</label><br>

    <table id="table">
        <tr>
            <th>№ остановки</th>
            <th>Станция</th>
            <th>Прибытие</th>
            <th>Отправление</th>
        </tr>
        <c:forEach var="station" items="${stationsOfRoute}" >
            <tr>
                <td>
                    <c:out value="${station.getIndexInRoute()}" />
                </td>
                <td>
                    <c:out value="${station.getStation().getName()}" />
                </td>
                <td>
                    <c:out value="${station.getArrivalTimeToString()}" />
                </td>
                <td>
                    <c:out value="${station.getDepartureTimeToString()}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
