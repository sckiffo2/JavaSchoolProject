
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Routes</title>
    <link href="resources\css\index.css" rel="stylesheet" type="text/css"/>
    <link href="resources\css\stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Routes</h1>
    <form method="POST" action="route\save">
        <label for="name">Добавление нового поезда</label><br>
        <input type="text" name="number" id="number" placeholder="Введите номер поезда"/><br>
        <input type="text" name="name" id="name" placeholder="Введите название поезда"/><br>
        <input type="text" name="pattern" id="pattern" placeholder="Введите тип расписания"/><br>
        <input type="submit" value="Добавить"/>
    </form>
    <br>
    <label for="table">Список поездов</label><br>
    <table id="table">
        <!-- here should go some titles... -->
        <tr>
            <th>id</th>
            <th>Номер</th>
            <th>Название поезда</th>
            <th>Периодичность</th>
            <th> </th>
            <th> </th>
        </tr>
        <c:forEach var="route" items="${routes}" >
        <tr>
            <td>
                <c:out value="${route.getId()}" />
            </td>
            <td>
                <c:out value="${route.getRouteNumber()}" />
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