<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit stations of route Page</title>
</head>
<body>
    <h1>Edit stations of route Page</h1>
    <br>
    <div>
        <form method="POST" action="editStations">
            <label>Добавление станции в маршрут</label>
            <br>
            <input list="stations" id="station" name="station" />
            <datalist id="stations">
                <c:forEach var="station" items="${stations}" >
                    <option value="${station.getName()}"></option>
                </c:forEach>
            </datalist>
            <br>
            <label for="arrival"> Время прибытия</label>
            <input type="time" name="arrival" id="arrival">
            <br>
            <label for="departure">Время отправления</label>
            <input type="time" name="departure" id="departure">
            <br>
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
                    <c:out value="${station.getArrivalTime()}" />
                </td>
                <td>
                    <c:out value="${station.getDepartureTime()}" />
                </td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>
