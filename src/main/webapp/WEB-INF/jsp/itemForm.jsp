<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Добавить покупку</title>

    <link rel="stylesheet" href="resources/css/style.css">
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<head>
</head>
<body>

<div class="jumbotron pt-4">
    <h2>Добавить новую покупку</h2>

    <br/>
    <br/>
    <form method="post" action="/">

        <dl>
            <dt>Название покупки</dt>
            <dd><input type="text" value="Клавиатура" size=40 name="name" required></dd>
        </dl>
        <dl>
            <dt>Количество:</dt>
            <dd><input type="number" value="1" name="count" required></dd>
        </dl>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="button">Отмена</button>
    </form>
</div>


<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>