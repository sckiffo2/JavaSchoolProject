<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route Edit Page</title>
    <link href="resources\css\index.css" rel="stylesheet" type="text/css"/>
    <link href="resources\css\stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Route Edit Page</h1>

    <form method="POST" action="updateRoute">
        <label for="id">id поезда</label><br>
        <input type="hidden" name="id" id="id" value="${route.getId()}" readonly/>
        <br>
        <label for="name">Номер поезда</label><br>
        <input type="text" name="number" id="number" value="${route.getNumber()}" required />
        <br>
        <label for="name">Название поезда</label><br>
        <input type="text" name="name" id="name" value="${route.getName()}" required/>
        <br>
        <label for="name">Периодичность рейсов</label><br>
        <input type="text" name="pattern" id="pattern" value="${route.getSchedulePattern()}"/>
        <br>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="Изменить"/>
    </form>
</body>
</html>