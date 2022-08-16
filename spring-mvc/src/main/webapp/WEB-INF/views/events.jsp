<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Events</title>
</head>
<body>
<div class="jumbotron">
    <h1 class="display-4">${title}</h1>
    <hr class="my-4">
    <div>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Date</th>
            </tr>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td><c:out value="${event.id}"/></td>
                    <td><c:out value="${event.title}"/></td>
                    <td><c:out value="${event.date}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
