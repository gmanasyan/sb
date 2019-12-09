<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Покупки</title>

    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<head>
</head>
<body>

<div class="jumbotron pt-4">
    <h2>Трекер покупок</h2>
    <br/>
    <a href="/report/week" class="btn btn-outline-info">Все покупки за последнюю неделю</a>
    <a href="/report/bestseller/month" class="btn btn-outline-info">Лучший товар за последнюй месяц</a>
    <a href="/report/bestbuyer/halfyear" class="btn btn-outline-info">Лучший покупатель за полгода</a>
    <a href="/report/bestseller/18" class="btn btn-outline-info">Лучшая покупка людей 18 лет</a>
    <br/>
    <br/>
    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th>#</th>
            <th>Дата</th>
            <th>Товар</th>
            <th>Количество</th>
            <th>Стоимость</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Возраст</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${purchases}" var="purchase">
            <jsp:useBean id="purchase" type="ru.smartsoft.dto.PurchaseTo"/>
            <tr>
                <td>${purchase.id}</td>
                <td>${fn:substring(purchase.date, 0, 16)}</td>
                <td>${purchase.item}</td>
                <td>${purchase.count}</td>
                <td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
                       groupingUsed = "false" value="${purchase.amount/100}"/></td>
                <td>${purchase.name}</td>
                <td>${purchase.lastName}</td>
                <td>${purchase.age}</td>

                <td><button type="button" onclick="updateRow(${purchase.id})">Изменить</button></td>
                <td><button type="button" onclick="deleteRow(${purchase.id})">Удалить</button></td>
            </tr>
        </c:forEach>
    </table>
    </button>
    <a href="/purchases/add" class="btn btn-primary">Добавить новую покупку</a>
</div>

<script type="text/javascript" src="/resources/js/sb.items.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>