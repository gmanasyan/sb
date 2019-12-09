<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Отчет</title>

    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<head>
</head>
<body>

<div class="jumbotron pt-4">
    <h2>${reportTitle}</h2>

    <br/>
    <br/>
    <h3>${reportMessage}<h3>
    <br/>
    <br/>

    <button onclick="window.history.back()" type="button">Назад</button>
</div>


<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>