# Тестовое задание
<br/><br/><br/>

## Задача

Спроектировать API для задачи можно двумя способами в зависемости от
того, рассматриваем ли мы сервис как для самообслуживания (зарегистрироваться,
добавить данные, посмотреть отчеты по своим и чужим покупкам) и или как сервис для администрации 
(администратор авторизируется, добавляет покупки других людей, смотрит отчеты)

Так же есть третий подход, где пользователи сами добавляют данные, 
а администратор может посмотреть отчеты на основе их данных.

Пока решаю задачу для первого варинта. 



<br/><br/><br/>


## 01. Управления своими покупками

### 01.1. Посмотреть свои покупки
- Пользователь может посмотреть свои покупки.
GET http://localhost:8080/sb/api/purchases

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/purchases"</pre>


### 01.2. Добавить покупку
- Пользователь может добавить покупку.
POST http://localhost:8080/sb/api/purchases

Request:
 <pre>curl -X POST -s --user alex@gmail.com:password "http://localhost:8080/sb/api/purchases"</pre>


### 01.3. Редактировать покупку
- Пользователь может удалить покупку.
PUT http://localhost:8080/sb/api/purchases

Request:
 <pre>curl -X PUT -s --user  alex@gmail.com:password "http://localhost:8080/sb/api/purchases/{id}"</pre>


### 01.4. Удалить покупку 
- Пользователь может удалить покупку.
DELETE http://localhost:8080/sb/api/purchases

Request:
 <pre>curl -X DELETE -s --user alex@gmail.com:password "http://localhost:8080/sb/api/purchases/{id}"</pre>




<br/><br/><br/>

## 02. Просмотр отчетов

### 02.1. Посмотреть все покупки за неделю
GET http://localhost:8080/sb/api/report/week

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/week"</pre>


### 02.2. Посмотреть самый покупаемый товар за последний месяц
GET http://localhost:8080/sb/api/report/bestseller/month

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/bestseller/month"</pre>


### 02.3. Вывести имя и фамилию человека, совершившего больше всего покупок за полгода
GET http://localhost:8080/sb/api/report/bestbuyer/halfyear

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/bestbuyer/halfyear"</pre>


### 02.4. Что чаще всего покупают люди в возрасте 18 лет
GET http://localhost:8080/sb/api/report/bestseller/{age}

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/bestseller/{age}"</pre>

<br/><br/><br/>



## 03. Регистрация пользователя

### 03.1. Регистрация пользователся
POST http://localhost:8080/sb/api/profile/register

Request: 
<pre>
curl -s -X POST -d '{"name":"Name", "lastname":"LastName", "age":32, "email":"alex@gmail","password":"password"}' -H 
'Content-Type:application/json;charset=UTF-8' http://localhost:8080/sb/api/profile/register
</pre>


### 03.2. Просмотр своего профайла 
GET http://localhost:8080/sb/api/profile/

Request:
<pre>curl -s --user alex@gmail.com:password http://localhost:8080/sb/api/profile/
</pre>


### 03.3. Изменение своего профайла
PUT http://localhost:8080/sb/api/profile/

Request: 
<pre>
curl -s --user alex@gmail.com:password -X PUT -d '{"name":"Name", "lastname":"LastName", "age":32, "password":"password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteapp/rest/profile
</pre>

<br/><br/><br/>





