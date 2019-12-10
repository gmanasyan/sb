# Тестовое задание
<br/>

## Задача

Спроектировать API для задачи.
- Администратор авторизируется 
- Добавляет покупки других людей
- Смотрит отчеты

<br/>

## 01. Менеджер покупкок

- Для всех запросов используется basic авторизация пользователь admin, пароль admin

### 01.1.1  Посмотреть последние добавленные покупки
GET http://localhost:8080/api/purchases

Request:
<pre>curl -X GET http://localhost:8080/api/purchases/ \
  -s --user admin:admin
</pre>

Response:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<SrvGetPurchaseListRs>
    <Purchase>
        <id>100013</id>
        <name>Константин</name>
        <lastName>Константинов</lastName>
        <age>18</age>
        <purchase_item>Juicer</purchase_item>
        <count>1</count>
        <amount>560000</amount>
        <purchaseDate>2019-12-04T00:00:00.000+03:00</purchaseDate>
    </Purchase>
    <Purchase>
        <id>100012</id>
        <name>Алексей</name>
        <lastName>Алексеев</lastName>
        <age>18</age>
        <purchase_item>Keyboard</purchase_item>
        <count>3</count>
        <amount>120000</amount>
        <purchaseDate>2019-12-03T00:00:00.000+03:00</purchaseDate>
    </Purchase>
    <Purchase>
        <id>100011</id>
        <name>Константин</name>
        <lastName>Константинов</lastName>
        <age>18</age>
        <purchase_item>Headphone</purchase_item>
        <count>2</count>
        <amount>780000</amount>
        <purchaseDate>2019-12-03T00:00:00.000+03:00</purchaseDate>
    </Purchase>
    <Purchase>
        <id>100010</id>
        <name>Алексей</name>
        <lastName>Алексеев</lastName>
        <age>18</age>
        <purchase_item>Juicer</purchase_item>
        <count>1</count>
        <amount>410000</amount>
        <purchaseDate>2019-12-02T00:00:00.000+03:00</purchaseDate>
    </Purchase>
    <Purchase>
        <id>100009</id>
        <name>Иван</name>
        <lastName>Иванов</lastName>
        <age>32</age>
        <purchase_item>Smartphone</purchase_item>
        <count>1</count>
        <amount>2340000</amount>
        <purchaseDate>2019-12-02T00:00:00.000+03:00</purchaseDate>
    </Purchase>
    <Purchase>
        <id>100008</id>
        <name>Иван</name>
        <lastName>Иванов</lastName>
        <age>32</age>
        <purchase_item>Tv</purchase_item>
        <count>1</count>
        <amount>1160000</amount>
        <purchaseDate>2019-12-09T12:04:15.436+03:00</purchaseDate>
    </Purchase>
</SrvGetPurchaseListRs>
```


<br/><br/>
### 01.1.2  Посмотреть покупку по номеру
GET http://localhost:8080/api/purchases/{id}

Request:
<pre>curl -X GET http://localhost:8080/api/purchases/100008 \
  -s --user admin:admin
</pre>

Response:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<SrvGetPurchaseRs>
    <Purchase>
        <id>100008</id>
        <name>Иван</name>
        <lastName>Иванов</lastName>
        <age>32</age>
        <purchase_item>Tv</purchase_item>
        <count>1</count>
        <amount>1160000</amount>
        <purchaseDate>2019-12-10T09:25:25.109+03:00</purchaseDate>
    </Purchase>
</SrvGetPurchaseRs>
```

<br/><br/>
### 01.2. Добавить покупку
Пользователь может добавить покупку <br/>
POST http://localhost:8080/api/purchases

Request:
```xml
curl -X POST http://localhost:8080/api/purchases/ \
   -H 'Content-Type: application/xml' \
   -s --user admin:admin \
   -d '<SrvCreatePurchaseRq>
     <name>Ivan</name>
     <lastName>Ivanov</lastName>
     <age>30</age>
     <purchase_item>Tv</purchase_item>
     <count>1</count>
     <amount>3</amount>
     <purchaseDate>2019-12-08T12:00:00</purchaseDate>
 </SrvCreatePurchaseRq>'
 ```

Response<br/>
При успешном создание возвращает статус 201 и адрес ресурса.

<pre> 
Header:
Http status: 201 Created
Location: http://localhost:8080/api/purchases/100015

Body:
Purchase created
</pre>

При ошибке, например JAXB валидации, возвращает статус 422.
И в теле ответа JSON c параметрами 
- url: адрес запроса
- erorrMessage: Описаниее ошибкой  
<pre> 
Header:
Http status: 422 Unprocessable Entity

Body:
{
    "url": "/api/purchases/",
    "errorMessage": "cvc-datatype-valid.1.2.1: 'One' is not a valid value for 'integer'."
}
</pre>



<br/><br/>
### 01.3. Редактировать покупку
Пользователь может редактировать покупку.<br/>
PUT http://localhost:8080/api/purchases/{id}

Request:
```xml
curl -X PUT http://localhost:8080/api/purchases/100015 \
   -H 'Content-Type: application/xml' \
   -s --user admin:admin \
  -d '<SrvCreatePurchaseRq>
    <name>Ivan</name>
    <lastName>Ivanov</lastName>
    <age>45</age>
    <purchase_item>Tv</purchase_item>
    <count>1</count>
    <amount>2000</amount>
    <purchaseDate>2019-12-08T12:00:00</purchaseDate>
</SrvCreatePurchaseRq>'
```

Response<br/>
При успешном апдейте возвращает статус 204 No Content.




<br/><br/>
### 01.4. Удалить покупку 
Пользователь может удалить покупку.<br/>
DELETE http://localhost:8080/api/purchases/{id}

Request:
<pre>curl -X DELETE http://localhost:8080/api/purchases/100015 \
   -s --user admin:admin
</pre>

Response<br/>
При успешном удаление возвращает статус 204 No Content.

<br/><br/><br/>

## 02. Просмотр отчетов

### 02.1. Посмотреть все покупки за последнию неделю (текущую неделю)
GET http://localhost:8080/api/report/week

Request:
<pre>curl -X GET http://localhost:8080/api/report/week  \
   -s --user admin:admin
</pre>

Response:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<SrvGetPurchaseListRs>
    <Purchase>
        <id>100008</id>
        <name>Иван</name>
        <lastName>Иванов</lastName>
        <age>32</age>
        <purchase_item>Tv</purchase_item>
        <count>1</count>
        <amount>1160000</amount>
        <purchaseDate>2019-12-10T09:25:25.109+03:00</purchaseDate>
    </Purchase>
</SrvGetPurchaseListRs>
```



<br/><br/>
### 02.2. Посмотреть самый покупаемый товар за последний месяц
GET http://localhost:8080/api/report/bestseller/month

Request:
<pre>curl -X GET http://localhost:8080/api/report/bestseller/month  \
   -s --user admin:admin
</pre>

Response:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<SrvGetPurchaseItemRs>
    <purchase_item>Keyboard</purchase_item>
    <description>Самый покупаемый товар за последний месяц</description>
</SrvGetPurchaseItemRs>
```



<br/><br/>
### 02.3. Вывести имя и фамилию человека, совершившего больше всего покупок за полгода
GET http://localhost:8080/sb/api/report/bestbuyer/halfyear

Request:
<pre>curl -X GET http://localhost:8080/api/report/bestbuyer/halfyear  \
   -s --user admin:admin
</pre>

Response:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<SrvGetUserRs>
    <user>
        <id>100001</id>
        <name>Алексей</name>
        <lastName>Алексеев</lastName>
        <age>18</age>
    </user>
    <description>Человек совершивший больше всего покупок (по стоимости) за полгода</description>
</SrvGetUserRs>
```



<br/><br/>
### 02.4. Что чаще всего покупают люди в возрасте 18 лет
GET http://localhost:8080/sb/api/report/bestseller/{age}

Request:
<pre>curl -X GET http://localhost:8080/api/report/bestseller/18  \
   -s --user admin:admin
</pre>

Response:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<SrvGetPurchaseItemRs>
    <purchase_item>Keyboard</purchase_item>
    <description>Самый покупаемый товар для людей в возрасте 18</description>
</SrvGetPurchaseItemRs>
```

При отсутсвии результата возвращается JSON 

<pre>
{
    "url": "/api/report/bestseller/77",
    "errorMessage": "Нет товара купленного людьми в возрасте 77"
}
</pre>