<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Tickets List</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/style.css}"/>
</head>
<body>
<h1>Tickets List</h1>
<div>
    <nobr>
        <%--        <a th:href="@{/contacts/add}">Add Contact</a> |--%>
        <a th:href="@{/}">Back to Welcome</a>
    </nobr>
</div>
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
            <td th:utext="${ticket.id}">...</td>
            <td th:utext="${ticket.eventId}">...</td>
            <td th:utext="${ticket.place}">...</td>
            <td th:utext="${ticket.userId}">...</td>
            <td th:utext="${ticket.category}">...</td>
        </tr>
    </table>
</div>
<br/><br/>
</body>
</html>