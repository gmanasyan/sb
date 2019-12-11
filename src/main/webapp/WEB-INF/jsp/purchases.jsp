<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Покупки</title>

    <link rel="stylesheet" href="/assets/css/style.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="jumbotron pt-4">
    <h2><a href="/purchases">Трекер покупок</a></h2>
    <br/>
    <a href="#" onclick="reportWeek();return false;" class="btn btn-outline-info">
        Покупки за последнюю неделю</a>
    <a href="/purchases/report?action=/api/report/bestseller/month" class="btn btn-outline-info">
        Бестселлер за последний месяц</a>
    <a href="/purchases/report?action=/api/report/bestbuyer/halfyear" class="btn btn-outline-info">
        Лучший покупатель за полгода</a>
    <a href="/purchases/report?action=/api/report/bestseller/18" class="btn btn-outline-info">
        Бестселлер для людей 18 лет</a>
    <br/>
    <br/>
    <table class="table table-striped" id="datatable">

    </table>
    <a href="/purchases/add" class="btn btn-primary">Добавить новую покупку</a>
</div>

<div id="logger"> </div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>