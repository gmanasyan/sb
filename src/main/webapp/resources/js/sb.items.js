$(document).ready(function () {
});

function save() {
  //var formjson = $('#addItem').serializeArray();

  var formxml = '<purchaseRequest><itemName>'+$("form#addItem option:selected").val()+ '</itemName>' +
      '<count>'+$("form#addItem input[name ='count']").val()+ '</count>' +
      '</purchaseRequest>';

  //alert(formxml);
  console.log(formxml);

  var check = $('html');

  $.ajax({
    url: "./",
    data: formxml,
    type: 'POST',
    contentType: "application/xml",
    dataType: "text",
    success: function(response)
    {check.html(response);},
    error : function (xhr, ajaxOptions, thrownError){
      console.log(xhr.status);
      console.log(thrownError);
    }
  });


}