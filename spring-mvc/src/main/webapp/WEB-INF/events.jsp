<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <title th:utext="${title}"/>
  <link rel="stylesheet" type="text/css" th:href="@{/WEB-INF/templates/css/style.css}"/>
</head>
<body>
<h1 th:utext="${title}"></h1>
<b>Events</b>
<p>${event.id}</p>
<p>${event.title}</p>
<p>${event.date}</p>
<br/><br/>
<div> </div>
<b>hello</b>
</body>
</html>