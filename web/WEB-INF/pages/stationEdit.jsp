<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Station Edit Page</title>
</head>
<body>
    <h1>Station Edit Page</h1>

    <form method="POST" action="updateStation">
        <label for="id">id станции</label><br>
        <input type="text" name="id" id="id" value="${station.getId()}" readonly/>
        <br>
        <label for="name">Название станции</label><br>
        <input type="text" name="name" id="name" value="${station.getName()}" />
        <br>
        <input type="submit" value="Изменить"/>
    </form>
</body>
</html>
