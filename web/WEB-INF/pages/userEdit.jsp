<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Edit Page</title>
</head>
<body>
<h1>User Edit Page</h1>
<form method="POST" action="updateUser">
    <label for="id">Id</label><br>
    <input type="text" name="id" id="id" value="${user.id}" readonly/>
    <br>
    <label for="username">Имя пользователя</label><br>
    <input type="text" name="username" id="username" value="${user.username}" />
    <br>
    <label for="password">Пароль</label><br>
    <input type="text" name="password" id="password" value="${user.password}" />
    <br>
    <label for="mail">Электронная почта</label><br>
    <input type="text" name="mail" id="mail" value="${user.mail}" />
    <br>
    <label for="active">Активация\деактивация аккаунта</label>
    <input type="checkbox" name="active" id="active" <c:if test="${user.active == true}">checked="checked"</c:if>>
    <br>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <input type="submit" value="Изменить"/>
</form>
</body>
</html>
