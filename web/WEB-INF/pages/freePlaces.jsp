
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Выбор места</title>
    <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/stations.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:forEach var="wagonPlcaes" items="${tripPlaces}" varStatus="loop" >
        <p>Вагон №<c:out value="${loop.count}" /> </p>
        <c:forEach var="place" items="${wagonPlcaes}" >
            <a href="bookTicket/<c:out value="${loop.count}/${place}" />">${place}</a>&nbsp;
        </c:forEach>
    </c:forEach>
</body>
</html>