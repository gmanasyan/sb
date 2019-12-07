<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Мои Покупки</title>

    <link rel="stylesheet" href="resources/css/style.css">
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<head>
</head>
<body>

<div class="jumbotron pt-4">
    <h2>Вход в трекер продаж</h2>
    <br/>
    <br/>
    <form action="/login" method="post">
        <dl>
            <dt>Имя пользователя:</dt>
            <dd><input type="text" size=40 name="username" required></dd>
        </dl>
        <dl>
            <dt>Пароль:</dt>
            <dd><input type="password" name="password" required></dd>
        </dl>
        <input type="submit" value="Войти"/>
    </form>
</div>
<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>