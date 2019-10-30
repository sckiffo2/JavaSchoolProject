
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Пассажиры</title>
    <link href="resources\css\index.css" rel="stylesheet" type="text/css"/>
    <link href="resources\css\stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Пассажиры</h1>

    <form method="POST" action="getPassengers">
        <input list="routes" id="route" name="number" placeholder="Выберите поезд" />
        <datalist id="routes">
            <c:forEach var="route" items="${routes}" >
                <option value="${route.number}">${route.name}</option>
            </c:forEach>
        </datalist>
        <br>
        <label for="date"> Время прибытия</label>
        <input type="date" name="date" id="date">
        <br>
        <br>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="Поиск"/>
    </form>
    <br>
    <label for="table">Пассажиры зарегистрированные на поезд:</label><br>
    <table id="table">
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th></th>
        </tr>
        <c:forEach var="passenger" items="${passengers}" >
            <tr>
                <td>
                    <c:out value="${passenger.firstName}" />
                </td>
                <td>
                    <c:out value="${passenger.lastName}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>