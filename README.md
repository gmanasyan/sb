# Тестовое задание
<br/><br/><br/>

## Задача

Спроектировать API для задачи.
- Администратор авторизируется 
- Добавляет покупки других людей
- Смотрит отчеты

<br/><br/><br/>


## 01. Аунтентификация 

### 01.1. Аунтентификация
Пользователь может пройти аунтентификацию.
POST http://localhost:8080/sb/api/register


## 02. Добавить покупки других людей

### 02.1. Посмотреть последние добавленные покупки
GET http://localhost:8080/sb/api/purchases

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/purchases"</pre>


### 02.2. Добавить покупку
- Пользователь может добавить покупку.
POST http://localhost:8080/sb/api/purchases

Request:
 <pre>curl -X POST -s --user alex@gmail.com:password "http://localhost:8080/sb/api/purchases"</pre>


### 02.3. Редактировать покупку
- Пользователь может удалить покупку.
PUT http://localhost:8080/sb/api/purchases

Request:
 <pre>curl -X PUT -s --user  alex@gmail.com:password "http://localhost:8080/sb/api/purchases/{id}"</pre>


### 02.4. Удалить покупку 
- Пользователь может удалить покупку.
DELETE http://localhost:8080/sb/api/purchases

Request:
 <pre>curl -X DELETE -s --user alex@gmail.com:password "http://localhost:8080/sb/api/purchases/{id}"</pre>


<br/><br/><br/>

## 03. Просмотр отчетов

### 03.1. Посмотреть все покупки за неделю
GET http://localhost:8080/sb/api/report/week

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/week"</pre>


### 03.2. Посмотреть самый покупаемый товар за последний месяц
GET http://localhost:8080/sb/api/report/bestseller/month

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/bestseller/month"</pre>


### 03.3. Вывести имя и фамилию человека, совершившего больше всего покупок за полгода
GET http://localhost:8080/sb/api/report/bestbuyer/halfyear

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/bestbuyer/halfyear"</pre>


### 03.4. Что чаще всего покупают люди в возрасте 18 лет
GET http://localhost:8080/sb/api/report/bestseller/{age}

Request:
<pre>curl -s --user alex@gmail.com:password "http://localhost:8080/sb/api/report/bestseller/{age}"</pre>

<br/><br/><br/>



