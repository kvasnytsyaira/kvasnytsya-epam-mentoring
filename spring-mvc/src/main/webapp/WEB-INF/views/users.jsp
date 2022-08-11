<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>User</title>
</head>
<body>

<div class="jumbotron">
    <h1 class="display-4">User</h1>
    <p class="lead">This pages generates an account data for you.</p>
    <hr class="my-4">
    <p>${user.name}</p>
    <p>${user.email}</p>
    <a class="btn btn-primary btn-lg" href="/accounts/generate" role="button">Generate</a>
    <a class="btn btn-secondary btn-lg" href="/accounts/add" role="button">Enter data</a>
</div>
</body>
</html>
