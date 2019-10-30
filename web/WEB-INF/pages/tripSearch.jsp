
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stations</title>
    <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Поиск рейса</h1>
<form method="POST" action="getTrip">
    <input list="stations1" id="station1" name="departureStation" placeholder="Выберите станцию отправления"/>
    <datalist id="stations1">
        <c:forEach var="station" items="${stations}" >
            <option value="${station.getName()}"></option>
        </c:forEach>
    </datalist>
    <input list="stations2" id="station2" name="arrivalStation" placeholder="Выберите станцию прибытия"/>
    <datalist id="stations2">
        <c:forEach var="station" items="${stations}" >
            <option value="${station.getName()}"></option>
        </c:forEach>
    </datalist>
    <br>
    <label for="date"> Дата отправления</label>
    <input type="date" name="stringDate" id="date">
    <br>
    <br>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <input type="submit" value="Поиск"/>
</form>
<br>

<table id="table">
    <!-- here should go some titles... -->
    <tr>
        <th>№ Поезда</th>
        <th>Название поезда</th>
        <th>Отправление</th>
        <th>Прибытие</th>
    </tr>
    <c:forEach var="dto" items="${TicketScheduleDTO}" >
        <tr>
            <td>
                <c:out value="${dto.routeNumber}" />
            </td>
            <td>
                <c:out value="${dto.routeName}" />
            </td>
            <td>
                <c:out value="${dto.getDepartureTimeString()}" />
            </td>
            <td>
                <c:out value="${dto.getArrivalTimeString()}" />
            </td>
            <td>
                <a href="freeplaces/<c:out value="${dto.tripId}" />">Забронировать билет</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>