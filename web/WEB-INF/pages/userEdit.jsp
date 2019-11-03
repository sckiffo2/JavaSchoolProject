<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Редактирование аккаунта</title>
</head>
<body>
<h1>Редактирование аккаунта</h1>
<form method="POST" action="updateUser">
    <label for="username">Пользователь</label><br>
    <input type="text" name="username" id="username" value="${user.username}" readonly/>
    <br>
    <label for="mail">Почта</label><br>
    <input type="email" name="mail" id="mail" value="${user.mail}" required/>
    <br>

    <label for="active">Аккаунт активен</label>
    <input type="checkbox" name="active" id="active" <c:if test="${user.active == true}">checked="checked"</c:if>>
    <input type="hidden" name="active" value="false"/>
    <br>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <input type="submit" value="Сохранить"/>
</form>
</body>
</html>
