
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация пассажина на рейс</title>
</head>
<body>
    <p>Билет забронирован на 5 минут, завершите регистрацию за отведенное время.</p>
    <h1>Пассажир</h1>
    <form method="POST" action="registerPassenger">
        <label for="firstName">Имя</label>
        <input type="text" name="firstName" id="firstName" pattern="[А-Яа-яA-Za-z-]{2,15}" required>
        <br>
        <label for="lastName">Фамилия</label>
        <input type="text" name="lastName" id="lastName" pattern="[А-Яа-яA-Za-z-]{2,15}" required>
        <br>
        <label for="date">Дата рождения</label>
        <input type="date" name="birthDate" id="date" required>
        <br>
        <input type="radio" name="gender" value="M" checked>Мужской
        <input type="radio" name="gender" value="F">Женский
        <br>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="Зарегистрировать"/>
    </form>
</body>
</html>