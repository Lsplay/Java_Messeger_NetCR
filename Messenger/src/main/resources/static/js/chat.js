var url = window.location.pathname;
var list = document.getElementById("list");
var ss = "/get"  + url;
var ss1 = "/gets" +   url;
document.forms.ourForm.onsubmit = function(e) {
	e.preventDefault();
	ms = document.forms.ourForm.message.value;
	s=document.getElementById('check');
	let a;
	if(s.checked){
		a="true";
	}else{
		a="false";
	}
	addMessage(ms,a)
}

let timerRefresh=setInterval(showMessage,3000);

$(document).ready(function() {
	showMessage();
});
function showMessage() {
	
	$.ajax({
		url: ss1,
		type: "GET",
		contentType: 'application/json; charset=utf-8',
		success: function(message) {
			console.log(message.length);
			var messageBlock = $(".list");
			messageBlock.empty();
			for (let i = 0; i < message.length; i++) {
				let sender = message[i].sender;
				let ms = message[i].message;
				let timeOfSend = message[i].timeOfSend;
				console.log(sender);
				console.log(ms);
				console.log(timeOfSend);
				let mess = $('<div class="alert alert-info mt-2">' +
					'<li>' + sender + '</li>' +
					'<li>' + ms + '</li>' +
					'<li>' + timeOfSend + '</li>' +
					'</div>');
				console.log(mess);
				messageBlock.append(mess);
			}
		},
		error: function(message_error) {
			console.log(message_error);

		},
		fail: function() {
			console.log("fail")
		}
	})
}

function addMessage(ms,s) {

	let messageText = ms;
	let Crypt=s;
	let message = JSON.stringify({
		'text': messageText,
		'crypt':s
	});

	$.ajax({
		url: ss,
		method: 'POST',
		contentType: 'application/json',
		dataType: 'json',
		data: message,

		success: function(message) {
			console.log(message);

		},
		error: function(message_error) {
			console.log(message_error);

		}
	})
}


