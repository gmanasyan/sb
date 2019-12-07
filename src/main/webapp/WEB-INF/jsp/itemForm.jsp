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
    <form method="post" id="addItem">
        <dl>
            <dt>Название покупки</dt>
            <dd>
                <select name="itemName">
                    <c:forEach items="${items}" var="item">
                        <jsp:useBean id="item" type="ru.smartsoft.model.Item"/>
                        <option value="${item.name}">${item.name}</option>
                    </c:forEach>
                </select>
<%--                <input type="text" value="Клавиатура" size=40 name="itemName" required></dd>--%>
        </dl>
        <dl>
            <dt>Количество:</dt>
            <dd><input type="number" value="1" name="count" required></dd>
        </dl>
    </form>
    <button type="button" onclick="save()">Сохранить</button>
    <button onclick="window.history.back()" type="button">Отмена</button>

</div>


<script type="text/javascript" src="resources/js/sb.items.js" defer></script>
<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>