
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Станции</title>
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Станции</h1>
    <form method="POST" action="station/save">
        <label for="name">Добавление новой станции</label><br>
        <input type="text" name="name" id="name" placeholder="Введите название станции" required pattern="^.{3,30}$"/>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="Добавить"/>
    </form>
    <br>
    <label for="table">Зарегистрированные станции</label><br>
    <table id="table">
        <!-- here should go some titles... -->
        <tr>
            <th>id</th>
            <th>Название</th>
            <th> </th>
        </tr>
        <c:forEach var="station" items="${stations}" >
            <tr>

                <td>
                    <c:out value="${station.getId()}" />
                </td>
                <td>
                    <c:out value="${station.getName()}" />
                </td>
                <td>
                    <a href="station\edit\<c:url value="${station.getId()}"/>">Изменить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>