<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stations</title>
    <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Users</h1>
<form method="POST" action="user/save">
    <label for="username">Добавление нового пользователя</label><br>
    <input type="text" name="username" id="username" placeholder="Введите имя пользователя"/><br>
    <input type="text" name="password" id="password" placeholder="Введите пароль"/><br>
    <input type="text" name="mail" id="mail" placeholder="Введите eMail"/><br>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <input type="submit" value="Добавить"/>
</form>
<br>
<label for="table">Имеющиеся пользователи</label><br>
<table id="table">
    <!-- here should go some titles... -->
    <tr>
        <th>id</th>
        <th>Имя пользователя</th>
        <th>mail</th>
        <th> </th>
        <th> </th>
    </tr>
    <c:forEach var="user" items="${users}" >
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.username}" />
            </td>
            <td>
                <c:out value="${user.mail}" />
            </td>
            <td>
                <a href="user\edit\<c:url value="${user.getId()}"/>">Изменить</a>
            </td>
            <td>
                <a href="user\delete\<c:url value="${user.getId()}"/>">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>