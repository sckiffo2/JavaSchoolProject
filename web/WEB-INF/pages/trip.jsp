
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Рейсы</title>
    <link href="resources\css\index.css" rel="stylesheet" type="text/css"/>
    <link href="resources\css\stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Рейсы</h1>
<form method="POST" action="trip\add">
    <input list="routes" id="route" name="routeNumber" placeholder="Выберите поезд" required/>
    <datalist id="routes">
        <c:forEach var="route" items="${routes}" >
            <option value="${route.number}">${route.name}</option>
        </c:forEach>
    </datalist>
    <label for="date">Дата</label>
    <input type="date" name="date" id="date" required>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Добавить"/>
</form>
<br>
<label for="table">Рейсы</label><br>
<table id="table">
    <tr>

        <th>Номер</th>
        <th>Название поезда</th>
        <th>Дата отправления</th>
    </tr>
    <c:forEach var="trip" items="${trips}" >
        <tr>
            <td>
                <c:out value="${trip.getRoute().getNumber()}" />
            </td>
            <td>
                <c:out value="${trip.getRoute().getName()}" />
            </td>
            <td>
                <c:out value="${trip.getStartDate()}" />
            </td>
            <td>
                <c:out value="${route.getSchedulePattern()}" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>