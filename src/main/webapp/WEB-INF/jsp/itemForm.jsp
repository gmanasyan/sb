<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Добавить покупку</title>

    <link rel="stylesheet" href="/assets/css/style.css">
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" >
    <link rel="stylesheet" href="/webjars/datetimepicker/2.5.20/jquery.datetimepicker.css">

</head>
<body>

<div class="jumbotron pt-4">

        <h2 id="editTitle" class="hide">Редактировать покупку</h2>
        <h2 id="addTitle">Добавить новую покупку</h2>

    <br/>
    <br/>
    <form method="post" id="addItem">
         <input type="hidden" name="id" >

        <dl><dt>Имя:</dt>
            <dd><input type="text" name="name" ></dd>
        </dl>
        <dl><dt>Фамилия:</dt>
            <dd><input type="text" name="lastname" ></dd>
        </dl>
        <dl><dt>Возраст:</dt>
            <dd><input type="text" name="age"></dd>
        </dl>
        <dl>
            <dt>Покупка</dt>
            <dd>
                <select name="purchaseItem" id="purchaseItem">
                    <option value="Tv">Tv</option>
                    <option value="Smartphone">Smartphone</option>
                    <option value="Juicer">Juicer</option>
                    <option value="Headphone">Headphone</option>
                    <option value="Keyboard">Keyboard</option>
                </select>
        </dl>
        <dl><dt>Количество:</dt>
            <dd><input type="text" value="1" name="count" ></dd>
        </dl>
        <dl><dt>Сумма:</dt>
            <dd><input type="text"  name="amount"></dd>
        </dl>
        <dl><dt>Дата покупки:</dt>
            <dd><input id="dateSelector" type="text" name="date"></dd>
        </dl>
        <dl><dt>Время покупки:</dt>
            <dd><input id="timeSelector" type="text" name="time"></dd>
        </dl>
    </form>


    <button type="button" onclick="save();">Сохранить</button>

    <button onclick="window.history.back()" type="button">Отмена</button>

</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>