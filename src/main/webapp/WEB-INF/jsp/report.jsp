<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Отчет</title>

    <link rel="stylesheet" href="/assets/css/style.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">

<head>
</head>
<body>

<div class="jumbotron pt-4">
    <h2 id="reportTitle"></h2>

    <br/>
    <br/>
    <h3 id="reportMessage"></h3>
    <br/>
    <br/>

    <button onclick="window.history.back()" type="button">Назад</button>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>