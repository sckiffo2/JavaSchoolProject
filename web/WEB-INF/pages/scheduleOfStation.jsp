
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Расписание по станции</title>
    <link href="resources\css\index.css" rel="stylesheet" type="text/css"/>
    <link href="resources\css\stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Расписание по станции</h1>
    <form method="POST" action="getSchedule">
        <input list="stations" id="station" name="name" placeholder="Выберите станцию" required/>
        <datalist id="stations">
            <c:forEach var="station" items="${stations}" >
                <option value="${station.getName()}"></option>
            </c:forEach>
        </datalist>
        <br>
        <label for="date">Дата</label>
        <input type="date" name="stringDate" id="date" required>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <br>
        <br>
        <input type="submit" value="Поиск"/>
    </form>
    <br>
    <label for="table">Расписание станции: ${station} на ${date}</label><br>
    <table id="table">
        <tr>
            <th>№ Поезда</th>
            <th>Название поезда</th>
            <th>Время прибытия</th>
            <th>Время отправления</th>
            <th></th>
        </tr>
        <c:forEach var="scheduleRow" items="${schedule}" >
            <tr>
                <td>
                    <c:out value="${scheduleRow.routeNumber}" />
                </td>
                <td>
                    <c:out value="${scheduleRow.routeName}" />
                </td>
                <td>
                    ${scheduleRow.getArrivalTimeString()}
                </td>
                <td>
                    ${scheduleRow.getDepartureTimeString()}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>