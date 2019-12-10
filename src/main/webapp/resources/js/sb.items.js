let ajaxUrl = "/purchases/";

$(function () {

  $.datetimepicker.setLocale('ru');
  $('#dateSelector').datetimepicker({
    timepicker: false,
    format: 'Y-m-d'
  });
  $('#timeSelector').datetimepicker({
    datepicker: false,
    format: 'H:i'
  });
});


function save(id) {
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

  console.log(amount);
  console.log(formxml);
  var check = $('html');

  if (id == 0) {
    $.ajax({
      url: ajaxUrl,
      data: formxml,
      type: 'POST',
      contentType: "application/xml",
      dataType: "text",
      success: function (response) {
        check.html(response);
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log(xhr.status);
      }
    });
  }
  if (id != 0) {
    $.ajax({
      url: ajaxUrl+id,
      data: formxml,
      type: 'PUT',
      contentType: "application/xml",
      dataType: "text",
      success: function (response) {
        //check.html(response);
        window.location.href = ajaxUrl;
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log(xhr.status);
        console.log(thrownError);
      }
    });
  }

}

function updateRow(id) {
  window.location.href = ajaxUrl + id;
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

function toRub(kop) {
  document.write((kop/100).toFixed(2));
}