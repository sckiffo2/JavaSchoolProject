<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Station Edit Page</title>
</head>
<body>
    <h1>Station Edit Page</h1>

    <form method="POST" action="updateStation">
        <input type="hidden" name="id" id="id" value="${station.getId()}" readonly/>
        <br>
        <label for="name">Название станции</label><br>
        <input type="text" name="name" id="name" value="${station.getName()}" />
        <br>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        <input type="submit" value="Изменить"/>
    </form>
</body>
</html>
