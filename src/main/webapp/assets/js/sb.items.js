let baseUrl = "/purchases";
let ajaxUrl = "/api/purchases/";
let reportUrl = "/api/report/";

const urlParams = new URLSearchParams(window.location.search);
const idParam = urlParams.get('id');
const actionParam = urlParams.get('action');

$(function () {

  if (actionParam != null) {
    loadXMLDoc(actionParam);
  } else
  if (idParam != null) {
    loadXMLDoc(ajaxUrl+idParam);
  } else {
    if (window.location.pathname == baseUrl) {
      loadXMLDoc(ajaxUrl);
    }
  }

  $('#dateSelector').datetimepicker({
    timepicker: false,
    format: 'Y-m-d'
  });
  $('#timeSelector').datetimepicker({
    datepicker: false,
    format: 'H:i'
  });
});


function reportWeek() {
  loadXMLDoc(reportUrl + "week");
}

function loadXMLDoc(url) {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      if (actionParam != null) {
        renderReport(this);
      } else
      if (idParam != null) {
        renderForm(this);
      } else {

        renderTable(this);
      }
    }
  };
  xmlhttp.open("GET", url, true);
  xmlhttp.send();
}

function renderTable(xml) {
  var i;
  var xmlDoc = xml.responseXML;

  var x = xmlDoc.getElementsByTagName("Purchase");
  //document.getElementById("logger").innerHTML = x[1];
  var table ="<thead>\n"
      + "<tr>\n"
      + "<th>#</th>\n"
      + "<th>Дата</th>\n"
      + "<th>Товар</th>\n"
      + "<th>Количество</th>\n"
      + "<th>Стоимость</th>\n"
      + "<th>Имя</th>\n"
      + "<th>Фамилия</th>\n"
      + "<th>Возраст</th>\n"
      + "<th></th>\n"
      + "<th></th>\n"
      + "</tr>\n"
      + "</thead>";
  for (i = 0; i <x.length; i++) {

    id =  x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
    firstName = x[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
    lastName = x[i].getElementsByTagName("lastName")[0].childNodes[0].nodeValue;
    age = x[i].getElementsByTagName("age")[0].childNodes[0].nodeValue;
    purchase_item =x[i].getElementsByTagName("purchase_item")[0].childNodes[0].nodeValue;
    count = x[i].getElementsByTagName("count")[0].childNodes[0].nodeValue;
    amount = x[i].getElementsByTagName("amount")[0].childNodes[0].nodeValue;
    purchaseDate= x[i].getElementsByTagName("purchaseDate")[0].childNodes[0].nodeValue;

    table += '<tr><td>'+ id + '</td>' +
        '<td>' + purchaseDate.substring(0, 16).replace("T", " ") +'</td>' +
        '<td>' + purchase_item +'</td>' +
        '<td>' + count +'</td>' +
        '<td align="right">' + (amount/100).toFixed(2) +'</td>' +
        '<td>' + firstName +'</td>' +
        '<td>' + lastName +'</td>' +
        '<td>' + age +'</td>' +
        '<td><button type="button" onclick="updateRow('+ id + ')">Изменить</button></td>' +
        '<td><button type="button" onclick="deleteRow('+ id + ')">Удалить</button></td>' +
        '</tr>';
  }
  document.getElementById("datatable").innerHTML = table;

}

function renderForm(xml) {
  var xmlDoc = xml.responseXML;
  var x = xmlDoc.getElementsByTagName("Purchase");

  id = x[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
  firstName = x[0].getElementsByTagName("name")[0].childNodes[0].nodeValue;
  lastName = x[0].getElementsByTagName("lastName")[0].childNodes[0].nodeValue;
  age = x[0].getElementsByTagName("age")[0].childNodes[0].nodeValue;
  purchase_item = x[0].getElementsByTagName(
      "purchase_item")[0].childNodes[0].nodeValue;
  count = x[0].getElementsByTagName("count")[0].childNodes[0].nodeValue;
  amount = x[0].getElementsByTagName("amount")[0].childNodes[0].nodeValue;
  purchaseDate = x[0].getElementsByTagName(
      "purchaseDate")[0].childNodes[0].nodeValue;

  $('input[name ="id"]').val(id);
  $('input[name ="name"]').val(firstName);
  $('input[name ="lastname"]').val(lastName);
  $('input[name ="age"]').val(age);
  $('select[name ="purchaseItem"]').val(purchase_item);
  $('input[name ="count"]').val(count);
  $('input[name ="amount"]').val((amount/100).toFixed(2));
  $('input[name ="date"]').val(purchaseDate.substring(0, 10));
  $('input[name ="time"]').val(purchaseDate.substring(11, 16));

  $('#editTitle').css('display', 'block');
  $('#addTitle').css('display', 'none');

}

function renderReport(xml) {
  var xmlDoc = xml.responseXML;
  console.log(xml.responseText);

  var x = xmlDoc.getElementsByTagName("SrvGetPurchaseItemRs");
  if (x.length != 0) {
    purchase_item = x[0].getElementsByTagName("purchase_item")[0].childNodes[0].nodeValue;
    description = x[0].getElementsByTagName("description")[0].childNodes[0].nodeValue;

    document.getElementById("reportTitle").innerHTML = description;
    document.getElementById("reportMessage").innerHTML = purchase_item;
  }

  x = xmlDoc.getElementsByTagName("SrvGetUserRs");
  if (x.length != 0) {

    user =  xmlDoc.getElementsByTagName("user");
    userName = user[0].getElementsByTagName("name")[0].childNodes[0].nodeValue;
    userLast = user[0].getElementsByTagName("lastName")[0].childNodes[0].nodeValue;
    userAge = user[0].getElementsByTagName("age")[0].childNodes[0].nodeValue;

    description = x[0].getElementsByTagName("description")[0].childNodes[0].nodeValue;

    document.getElementById("reportTitle").innerHTML = description;
    document.getElementById("reportMessage").innerHTML =
        userName + " " + userLast + "<br/>Возраст: " + userAge;
  }
}


function save() {
  //var formjson = $('#addItem').serializeArray();

  var amount = Math.trunc($("form#addItem input[name ='amount']").val() * 100);

  var formxml = '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
      + '<SrvCreatePurchaseRq>' +
      '<name>'+$("form#addItem input[name ='name']").val()+ '</name>' +
      '<lastName>'+$("form#addItem input[name ='lastname']").val()+ '</lastName>' +
      '<age>'+$("form#addItem input[name ='age']").val()+ '</age>' +
      '<purchase_item>'+$("form#addItem #purchaseItem option:selected").val()+'</purchase_item>' +
      '<count>'+$("form#addItem input[name ='count']").val()+ '</count>' +
      '<amount>'+ amount + '</amount>' +
      '<purchaseDate>'+$("form#addItem input[name ='date']").val()+'T' +
      $("form#addItem input[name ='time']").val()+':00' + '</purchaseDate>' +
      '</SrvCreatePurchaseRq>';

  console.log(formxml);

  // if hidden id is exist this is update
  var id = $('input[name ="id"]').val();
  console.log("id - " + id);

  if (id == "") {
    $.ajax({
      url: ajaxUrl,
      data: formxml,
      type: 'POST',
      contentType: "application/xml",
      dataType: "text",
      success: function (response) {
        window.location.href = "/purchases";
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log(xhr.status);
        console.log(xhr.responseText);
        alert("Ошибка " + xhr.status + " \n\n" + getErrorMessage(xhr.responseText));
      }
    });
  }
  if (id > 0) {
    $.ajax({
      url: ajaxUrl+id,
      data: formxml,
      type: 'PUT',
      contentType: "application/xml",
      dataType: "text",
      success: function (response) {
        window.location.href = baseUrl;
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log(xhr.status);
        console.log(thrownError);
        alert("Ошибка " + xhr.status + " \n\n" + getErrorMessage(xhr.responseText));
      }
    });
  }

}

function getErrorMessage (xml) {
  console.log(xml);

  var xmlDoc = $.parseXML(xml);
  url = $(xmlDoc).find("url").text();
  errorMessage = $(xmlDoc).find("errorMessage").text();
  return "Адрес запроса: " + url + "\n\n " + errorMessage;
}

function updateRow(id) {
  window.location.href = baseUrl + "/add?id=" +id;
}

function deleteRow(id) {
  if (confirm("Удалить")) {
    $.ajax({
      url: ajaxUrl + id,
      type: "DELETE"
    }).done(function () {
      location.reload();
    });
  }
}
