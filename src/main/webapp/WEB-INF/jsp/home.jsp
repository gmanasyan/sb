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
    <h2>Добро пожаловать в Мои Покупки</h2>

    <br/>
    <br/>
    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th>#</th>
            <th>Дата</th>
            <th>Товар</th>
            <th>Стоимость</th>
            <th>Количество</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Возраст</th>
        </tr>
        </thead>
        <c:forEach items="${purchases}" var="purchase">
            <jsp:useBean id="purchase" type="ru.smartsoft.dto.PurchaseTo"/>
            <tr>
                <td>${purchase.id}</td>
                <td>${purchase.date}</td>
                <td>${purchase.name}</td>
                <td>${purchase.cost}</td>
                <td>${purchase.count}</td>
                <td>${purchase.buyerName}</td>
                <td>${purchase.buyerSecondName}</td>
                <td>${purchase.age}</td>

                <td><a href="purchases?action=update&id=${purchase.id}">Update</a></td>
                <td><a href="purchases?action=delete&id=${purchase.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </button>
    <a href="add" class="btn btn-primary">Добавить новую покупку</a>
</div>


<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>