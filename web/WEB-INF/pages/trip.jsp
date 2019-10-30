
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
        <th>id</th>
        <th>Номер</th>
        <th>Название поезда</th>
        <th>Периодичность</th>
        <th> </th>
        <th> </th>
    </tr>
    <c:forEach var="route" items="${}" >
        <tr>
            <td>
                <c:out value="${route.getId()}" />
            </td>
            <td>
                <c:out value="${route.getNumber()}" />
            </td>
            <td>
                <c:out value="${route.getName()}" />
            </td>
            <td>
                <c:out value="${route.getSchedulePattern()}" />
            </td>
            <td>
                <a href="route\edit\<c:url value="${route.getId()}"/>">Редактировать</a>
            </td>
            <td>
                <a href="route\editStations\<c:url value="${route.getId()}"/>">Добавить станции</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>