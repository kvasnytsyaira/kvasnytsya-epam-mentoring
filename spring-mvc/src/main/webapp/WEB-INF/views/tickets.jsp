<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Tickets List</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/style.css}"/>
</head>
<body>
<h1>Tickets List</h1>
<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>eventId</th>
            <th>place</th>
            <th>userId</th>
            <th>category</th>
        </tr>
        <tr th:each="ticket : ${tickets}">
            <td th:text="${ticket.id}">[...]</td>
            <td th:text="${ticket.eventId}">[...]</td>
            <td th:text="${ticket.place}">[...]</td>
            <td th:text="${ticket.userId}">[...]</td>
            <td th:text="${ticket.category}">[...]</td>
        </tr>
    </table>
</div>
<br/><br/>
</body>
</html>