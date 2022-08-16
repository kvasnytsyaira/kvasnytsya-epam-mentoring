<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Tickets</title>
</head>
<body>
<div class="jumbotron">
    <h1 class="display-4">${title}</h1>
    <hr class="my-4">
    <div>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>eventId</th>
                <th>place</th>
                <th>userId</th>
                <th>category</th>
            </tr>
            <c:forEach items="${tickets}" var="ticket">
                <tr>
                    <td><c:out value="${ticket.id}"/></td>
                    <td><c:out value="${ticket.eventId}"/></td>
                    <td><c:out value="${ticket.place}"/></td>
                    <td><c:out value="${ticket.userId}"/></td>
                    <td><c:out value="${ticket.category}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<br/><br/>
</body>
</html>