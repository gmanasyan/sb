<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Добавить покупку</title>

    <link rel="stylesheet" href="/resources/css/style.css">
    <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

<head>
</head>
<body>

<div class="jumbotron pt-4">
    <c:if test="${purchase.id != null}">
        <h2>Редактировать покупку</h2>
    </c:if>
    <c:if test="${purchase.id == null}">
        <h2>Добавить новую покупку</h2>
    </c:if>


    <br/>
    <br/>
    <form method="post" id="addItem">

        <c:if test="${purchase.id != null}">
            <input type="hidden" name="id" value="${purchase.id}" >
        </c:if>
        <dl><dt>Имя:</dt>
            <dd><input type="text" name="name" value="${purchase.name}"></dd>
        </dl>
        <dl><dt>Фамилия:</dt>
            <dd><input type="text" name="lastname" value="${purchase.lastName}"></dd>
        </dl>
        <dl><dt>Возраст:</dt>
            <dd><input type="text" name="age" value="${purchase.age}"></dd>
        </dl>
        <dl>
            <dt>Покупка</dt>
            <dd>
                <select name="purchaseItem" id="purchaseItem">
                    <c:forEach items="${items}" var="item">
                        <jsp:useBean id="item" type="ru.smartsoft.model.Item"/>
                        <option value="${item.name}" <c:if test="${item.name == purchase.item}">selected</c:if>>
                                ${item.name}</option>
                    </c:forEach>
                </select>
        </dl>
        <dl><dt>Количество:</dt>
            <dd><input type="text" value="1" name="count" value="${purchase.count}"></dd>
        </dl>
        <dl><dt>Сумма:</dt>
            <dd><input type="text"  name="amount"
                       value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2"
                       groupingUsed = "false" value="${purchase.amount/100}"/>"></dd>
        </dl>
        <dl><dt>Дата покупки:</dt>
            <dd><input type="text"  name="date" value="${purchase.date}"></dd>
        </dl>
    </form>
    <c:if test="${purchase.id != null}">
        <button type="button" onclick="save(${purchase.id})">Изменить</button>
    </c:if>
    <c:if test="${purchase.id == null}">
        <button type="button" onclick="save(0)">Сохранить</button>
    </c:if>

    <button onclick="window.history.back()" type="button">Отмена</button>

</div>


<script type="text/javascript" src="/resources/js/sb.items.js"></script>
<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>