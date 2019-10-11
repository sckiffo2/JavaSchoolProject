<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Stations</title>
    </head>
    <body>
        <h3>Enter The Station name</h3>
        <form method="post">
            <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
            <input type="text" name="name" placeholder="Введите название станции"/>
            <button type="submit">Добавить</button>
        </form>

<%--
<form method="POST" action="/station/addStation">
    <label path="name">Name</label>
    <input path="name"/>
    <input type="submit" value="Submit"/>
</form>
--%>

    </body>
</html>