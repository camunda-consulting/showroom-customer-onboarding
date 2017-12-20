
$( document ).ready(function() {
	autofillWorkaround();
	
	var lang = "en";
	
	// fill fields from URL and remember language
	(new URL(window.location.href)).searchParams.forEach((x, y) => {
		if (y == "lang") {
			lang = x;
		}
		var field = document.getElementById(y);
		if (field) {
			document.getElementById(y).value = x;
	}});

// // set URL to reach UI
// if ($('#uiUrl')) {
// var url = window.location;
// var paths = url.pathname.split('/');
// var guiUrl = url .protocol + "//" + url.host + "/";
// paths.slice(0,paths.length -1).forEach((x) => guiUrl += x + "/");
// $('#uiUrl').val(guiUrl);
// }

  var preisindikationInCent = 0;

  // $('#birthdate').on('change', function() {
  // console.log($('#birthdate').val());
  // });

  // Removed Auth
	// beforeSend = function(xhr) {
	// xhr.setRequestHeader('Authorization', 'Basic ' +
	// window.btoa(unescape(encodeURIComponent('${camunda.rest.username}' + ':'
	// + '${camunda.rest.password}'))))
	// };


	var pathArray = window.location.pathname.split( '/' );
	var baseUrl = window.location.protocol + "//" + window.location.host + "/" + pathArray[1] + "/api";

  // Start single Process Instance
  $('#triggerStartApplication').click(function() {
        var neuantrag = {
          "applicant" : {
               "name":         $('#applicant').val(),
               "sex":   $('#selectSex').val(),
               "birthday": $('#birthdate').val() + "T00:00:00",
               "email":        $('#email').val()
              },
          "vehicleManufacturer": $('#vehicleManufacturer').val(),
          "vehicleType":       $('#vehicleType').val(),
          "product": "Camundanzia Vollkasko Plus",
          "priceIndicationInCent": getPrice() * 100
          // ,
          // "uiUrl": $('#uiUrl').val()
          };

        var data = JSON.stringify(neuantrag);
       
        console.log( data );

        $.ajax({
              type: 'POST',
              url: baseUrl + "/new-application/" + lang,
              data: data,
              contentType: 'application/json; charset=utf-8',
              success: function(result) {
            	  	  $('#applicationId').text(result);
                  $('#applicationReceived').toggle();
                  $('#fieldsetForm').toggle();
              },
              crossDomain: true,
          });

  });

  // correlate message for Antrag
  $('#triggerUploadDocuments').click(function() {
	  debugger;
	      var fileUpload = $('#documentToUpload').get(0);

     	  var fileVar = {};
	      if(typeof FileReader === 'function' && fileUpload.files.length > 0) {		        
	        var reader = new FileReader();
	        reader.onloadend = (function(fileUpload) {
	          return function(e) {
	            var binary = '';
	            var bytes = new Uint8Array( e.target.result );
	            var len = bytes.byteLength;
	            for (var j = 0; j < len; j++) {
	                binary += String.fromCharCode( bytes[ j ] );
	            }
	            fileVar.value = btoa(binary);
	
	            // set file metadata as value info
	            fileVar.type = 'File';
	            fileVar.valueInfo = {
	                filename: fileUpload.files[0].name,
	                mimeType: fileUpload.files[0].type
	            };
	            
	
	            callCallback();
	          };
	        })(fileUpload);
	        reader.readAsArrayBuffer(fileUpload.files[0]);
		};
	    
	    var callCallback = function() {
		   var data = JSON.stringify(fileVar);
			var url = baseUrl + "/document/" + $('#referenceId').val();
		    $.ajax({
		             type : 'POST',
		             url: url,
		             data: data,
		             contentType: 'application/json; charset=utf-8',
		             dataType: 'json',
		             success: function (result) {
		                $('#documentsReceived').toggle();
		                $('#fieldsetForm').toggle();
		             },
		             crossDomain: true,
		    });
		}; 
    	
      
    
  });


  // Dynamic stuff to fill data into car selection
  $('#vehicleManufacturer').on('change', function() {
      if ($('#vehicleManufacturer').val() == "VW") {
        $('#vehicleType').children()[0].value = 'Beatle';  $('#vehicleType').children()[0].text = 'Beatle';
        $('#vehicleType').children()[1].value = 'Golf IV'; $('#vehicleType').children()[1].text = 'Golf IV';
        $('#vehicleType').children()[2].value = 'Golf V';  $('#vehicleType').children()[2].text = 'Golf V';
        $('#vehicleType').children()[3].value = 'Passat';  $('#vehicleType').children()[3].text = 'Passat';
      }
      if ($('#vehicleManufacturer').val() == "BMW") {
        $('#vehicleType').children()[0].value = '318i';  $('#vehicleType').children()[0].text = '318i';
        $('#vehicleType').children()[1].value = '525i';  $('#vehicleType').children()[1].text = '525i';
        $('#vehicleType').children()[2].value = '735i';  $('#vehicleType').children()[2].text = '735i';
        $('#vehicleType').children()[3].value = 'X3';    $('#vehicleType').children()[3].text = 'X3';
      }
      if ($('#vehicleManufacturer').val() == "Porsche") {
        $('#vehicleType').children()[0].value = '911';       $('#vehicleType').children()[0].text = '911';
        $('#vehicleType').children()[1].value = '925';       $('#vehicleType').children()[1].text = '925';
        $('#vehicleType').children()[2].value = 'Boxster';   $('#vehicleType').children()[2].text = 'Boxster';
        $('#vehicleType').children()[3].value = 'Cayenne';  $('#vehicleType').children()[3].text = 'Cayenne';
      }
      if ($('#vehicleManufacturer').val() == "Audi") {
        $('#vehicleType').children()[0].value = 'A3';  $('#vehicleType').children()[0].text = 'A3';
        $('#vehicleType').children()[1].value = 'A4';  $('#vehicleType').children()[1].text = 'A4';
        $('#vehicleType').children()[2].value = 'A6';  $('#vehicleType').children()[2].text = 'A6';
        $('#vehicleType').children()[3].value = 'A8';  $('#vehicleType').children()[3].text = 'A8';
      }
      calculatePrice(preisindikationInCent);
  });

  $('#vehicleType').on('change', function() {
      calculatePrice(preisindikationInCent);
  });

function calculatePrice(preisindikationInCent) {
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Beatle") { preisindikationInCent = 120}
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Golf IV") {preisindikationInCent = 160}
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Golf V") {preisindikationInCent = 150}
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Passat") {preisindikationInCent = 150}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="318i") {preisindikationInCent = 190}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="525i") {preisindikationInCent = 210}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="735i") {preisindikationInCent = 240}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="X3") {preisindikationInCent = 280}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="911") {preisindikationInCent = 310}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="925") {preisindikationInCent = 300}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="Boxster") {preisindikationInCent = 290}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="Cayenne") {preisindikationInCent = 300}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A3") {preisindikationInCent = 180}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A4") {preisindikationInCent = 180}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A6") {preisindikationInCent = 200}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A8") {preisindikationInCent = 280}

   $('#priceIndicationInCent').val(preisindikationInCent + ",00 EUR");
}

function getPrice() {
  var preisindikationInCent;
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Beatle") { preisindikationInCent = 120}
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Golf IV") {preisindikationInCent = 160}
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Golf V") {preisindikationInCent = 150}
   if ($('#vehicleManufacturer').val() == "VW" && $('#vehicleType').val()=="Passat") {preisindikationInCent = 150}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="318i") {preisindikationInCent = 190}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="525i") {preisindikationInCent = 210}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="735i") {preisindikationInCent = 240}
   if ($('#vehicleManufacturer').val() == "BMW" && $('#vehicleType').val()=="X3") {preisindikationInCent = 280}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="911") {preisindikationInCent = 310}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="925") {preisindikationInCent = 300}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="Boxster") {preisindikationInCent = 290}
   if ($('#vehicleManufacturer').val() == "Porsche" && $('#vehicleType').val()=="Cayenne") {preisindikationInCent = 300}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A3") {preisindikationInCent = 180}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A4") {preisindikationInCent = 180}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A6") {preisindikationInCent = 200}
   if ($('#vehicleManufacturer').val() == "Audi" && $('#vehicleType').val()=="A8") {preisindikationInCent = 280}

   return preisindikationInCent;
}
});

function autofillWorkaround() {
	 /**
	  * Autofill event polyfill ##version:1.0.0##
	  * (c) 2014 Google, Inc.
	  * License: MIT
	  */
	 (function(window) {
	   var $ = window.jQuery || window.angular.element;
	   var rootElement = window.document.documentElement,
	     $rootElement = $(rootElement);

	   addGlobalEventListener('change', markValue);
	   addValueChangeByJsListener(markValue);

	   $.prototype.checkAndTriggerAutoFillEvent = jqCheckAndTriggerAutoFillEvent;

	   // Need to use blur and not change event
	   // as Chrome does not fire change events in all cases an input is changed
	   // (e.g. when starting to type and then finish the input by auto filling a username)
	   addGlobalEventListener('blur', function(target) {
	     // setTimeout needed for Chrome as it fills other
	     // form fields a little later...
	     window.setTimeout(function() {
	       findParentForm(target).find('input').checkAndTriggerAutoFillEvent();
	     }, 20);
	   });

	   window.document.addEventListener('DOMContentLoaded', function() {
	     // The timeout is needed for Chrome as it auto fills
	     // login forms some time after DOMContentLoaded!
	     window.setTimeout(function() {
	       $rootElement.find('input').checkAndTriggerAutoFillEvent();
	     }, 200);
	   }, false);

	   return;

	   // ----------

	   function jqCheckAndTriggerAutoFillEvent() {
	     var i, el;
	     for (i=0; i<this.length; i++) {
	       el = this[i];
	       if (!valueMarked(el)) {
	         markValue(el);
	         triggerChangeEvent(el);
	       }
	     }
	   }

	   function valueMarked(el) {
	     var val = el.value,
	          $$currentValue = el.$$currentValue;
	     if (!val && !$$currentValue) {
	       return true;
	     }
	     return val === $$currentValue;
	   }

	   function markValue(el) {
	     el.$$currentValue = el.value;
	   }

	   function addValueChangeByJsListener(listener) {
	     var jq = window.jQuery || window.angular.element,
	         jqProto = jq.prototype;
	     var _val = jqProto.val;
	     jqProto.val = function(newValue) {
	       var res = _val.apply(this, arguments);
	       if (arguments.length > 0) {
	         forEach(this, function(el) {
	           listener(el, newValue);
	         });
	       }
	       return res;
	     }
	   }

	   function addGlobalEventListener(eventName, listener) {
	     // Use a capturing event listener so that
	     // we also get the event when it's stopped!
	     // Also, the blur event does not bubble.
	     rootElement.addEventListener(eventName, onEvent, true);

	     function onEvent(event) {
	       var target = event.target;
	       listener(target);
	     }
	   }

	   function findParentForm(el) {
	     while (el) {
	       if (el.nodeName === 'FORM') {
	         return $(el);
	       }
	       el = el.parentNode;
	     }
	     return $();
	   }

	   function forEach(arr, listener) {
	     if (arr.forEach) {
	       return arr.forEach(listener);
	     }
	     var i;
	     for (i=0; i<arr.length; i++) {
	       listener(arr[i]);
	     }
	   }

	   function triggerChangeEvent(element) {
	     var doc = window.document;
	     var event = doc.createEvent("HTMLEvents");
	     event.initEvent("change", true, true);
	     element.dispatchEvent(event);
	   }

	 })(window);
}
