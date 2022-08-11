<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://thymeleaf.org">
<head>
  <title>Events List</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
<h1>Events List</h1>
<br/><br/>
<div>
  <table border="1">
    <tr>
      <th>Id</th>
      <th>Title</th>
      <th>Date</th>
    </tr>
    <tr th:each="event: ${events}">
      <td th:utext="${event.id}">Text ...</td>
      <td th:utext="${event.title}">Text ...</td>
      <td th:utext="${event.date}">Text ...</td>
    </tr>
  </table>
</div>
<br/><br/>
</body>
</html>