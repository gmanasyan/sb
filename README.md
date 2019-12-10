# Тестовое задание
<br/>

## Задача

Спроектировать API для задачи.
- Администратор авторизируется 
- Добавляет покупки других людей
- Смотрит отчеты

<br/><br/>

### Спецификация Open API 3 (Swagger) доступна по адресу
http://localhost:8080/swagger-ui.html/
<br/><br/>
Посмотреть можно в редакторе http://editor.swagger.io (скопировать и вставить JSON)
<pre>
{"openapi":"3.0.1","info":{"title":"The purchase controller","description":"My API","version":"0.5"},"servers":[{"url":"http://localhost:8080","description":"Generated server url"}],"tags":[{"name":"Purchases","description":"Purchases Operations"},{"name":"Reports","description":"Reports Operations"}],"paths":{"/api/purchases/{id}":{"get":{"tags":["Purchases"],"summary":"Get purchases by id","operationId":"getOne","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"integer","format":"int32"}}],"responses":{"200":{"description":"Requested purchase","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvGetPurchaseRs"}}}},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}},"put":{"tags":["Purchases"],"summary":"Update purchase","operationId":"update","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"integer","format":"int32"}}],"requestBody":{"description":"Update purchase","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvCreatePurchaseRq"}}},"required":true},"responses":{"201":{"description":"Created successfully","content":{"application/text":{}}},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}},"422":{"description":"XML validation error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}},"delete":{"tags":["Purchases"],"summary":"Delete purchase","operationId":"delete","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"integer","format":"int32"}}],"responses":{"204":{"description":"Delete completed"},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}}},"/api/purchases/":{"get":{"tags":["Purchases"],"summary":"List of purchases by date","operationId":"purchases","responses":{"200":{"description":"List of purchases by date","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvGetPurchaseListRs"}}}},"401":{"description":"You are not authorized to view the resource"}}},"post":{"tags":["Purchases"],"summary":"Create purchase","operationId":"createItem","requestBody":{"description":"Created purchase","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvCreatePurchaseRq"}}},"required":true},"responses":{"201":{"description":"Created successfully"},"401":{"description":"You are not authorized to view the resource"},"422":{"description":"XML validation error","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}}},"/api/report/week":{"get":{"tags":["Reports"],"summary":"List of purchases by last week","operationId":"purchases_1","responses":{"200":{"description":"List of purchases","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvGetPurchaseListRs"}}}},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}}},"/api/report/bestseller/month":{"get":{"tags":["Reports"],"summary":"Popular product of last month","operationId":"bestsellerOfMonth","responses":{"200":{"description":"List of purchases","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvGetPurchaseItemRs"}}}},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}}},"/api/report/bestbuyer/halfyear":{"get":{"tags":["Reports"],"summary":"Best buyer of last 6 months","operationId":"bestbuyerOfHalfYear","responses":{"200":{"description":"List of purchases","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvGetUserRs"}}}},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}}},"/api/report/bestseller/{age}":{"get":{"tags":["Reports"],"summary":"Popular product for people if given age","operationId":"bestsellerByAge","parameters":[{"name":"age","in":"path","required":true,"schema":{"type":"integer","format":"int32"}}],"responses":{"200":{"description":"List of purchases","content":{"application/xml":{"schema":{"$ref":"#/components/schemas/SrvGetPurchaseItemRs"}}}},"401":{"description":"You are not authorized to view the resource"},"404":{"description":"Purchase by this id not found","content":{"application/json":{"schema":{"$ref":"#/components/schemas/ErrorInfo"}}}}}}},"/":{"get":{"operationId":"home","responses":{"200":{"description":"default response","content":{"*/*":{"schema":{"type":"string"}}}}}}}},"components":{"schemas":{"ErrorInfo":{"type":"object","properties":{"url":{"type":"string"},"errorMessage":{"type":"string"}}},"SrvCreatePurchaseRq":{"required":["lastName","name","purchaseDate","purchaseItem"],"type":"object","properties":{"name":{"type":"string"},"lastName":{"type":"string"},"age":{"type":"integer","format":"int32"},"purchaseItem":{"type":"string","xml":{"name":"purchase_item"},"enum":["TV","SMARTPHONE","JUICER","HEADPHONE","KEYBOARD"]},"count":{"type":"integer","format":"int32"},"amount":{"type":"integer","format":"int32"},"purchaseDate":{"type":"string","format":"date-time"}},"xml":{"name":"SrvCreatePurchaseRq"}},"PurchaseInfo":{"required":["lastName","name","purchaseDate","purchaseItem"],"type":"object","properties":{"id":{"type":"integer","format":"int32"},"name":{"type":"string"},"lastName":{"type":"string"},"age":{"type":"integer","format":"int32"},"purchaseItem":{"type":"string","xml":{"name":"purchase_item"},"enum":["TV","SMARTPHONE","JUICER","HEADPHONE","KEYBOARD"]},"count":{"type":"integer","format":"int32"},"amount":{"type":"integer","format":"int32"},"purchaseDate":{"type":"string","format":"date-time"}}},"SrvGetPurchaseRs":{"required":["purchase"],"type":"object","properties":{"purchase":{"$ref":"#/components/schemas/PurchaseInfo"}},"xml":{"name":"SrvGetPurchaseRs"}},"SrvGetPurchaseListRs":{"type":"object","properties":{"purchase":{"type":"array","xml":{"name":"Purchase"},"items":{"$ref":"#/components/schemas/PurchaseInfo"}}},"xml":{"name":"SrvGetPurchaseListRs"}},"SrvGetPurchaseItemRs":{"required":["description","purchaseItem"],"type":"object","properties":{"purchaseItem":{"type":"string","xml":{"name":"purchase_item"},"enum":["TV","SMARTPHONE","JUICER","HEADPHONE","KEYBOARD"]},"description":{"type":"string"}},"xml":{"name":"SrvGetPurchaseItemRs"}},"SrvGetUserRs":{"required":["description","user"],"type":"object","properties":{"user":{"$ref":"#/components/schemas/UserInfo"},"description":{"type":"string"}},"xml":{"name":"SrvGetUserRs"}},"UserInfo":{"required":["lastName","name"],"type":"object","properties":{"id":{"type":"integer","format":"int32"},"name":{"type":"string"},"lastName":{"type":"string"},"age":{"type":"integer","format":"int32"}}}}}}
</pre>

<br/><br/>
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

Если нет результата возвращается JSON с описанием ошибки

<pre>
{
    "url": "/api/report/bestseller/77",
    "errorMessage": "Нет товара купленного людьми в возрасте 77"
}
</pre>