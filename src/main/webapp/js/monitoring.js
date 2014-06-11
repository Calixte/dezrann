var frame, cursor;
document.addEventListener("DOMContentLoaded", function(event) {
	frame = document.getElementById('frame');
	cursor = document.getElementById('cursor');
});
function checkConfig(config) {
	if (config == undefined) {
		config = {}
	}
	if (config.host == undefined) {
		config.host = 'localhost';
	}
	if (config.port == undefined) {
		config.port = '8080';
	}
	if (config.path == undefined) {
		config.path = '/echo';
	}
	if (config.secure == undefined) {
		config.secure = false;
	}
	return config;
}
function watcherInit(id, c) {
	var config = checkConfig(c);
	var url = (config.secure ? 'wss' : 'ws') + '://' + config.host + ':' + config.port + config.path;
	var ws = new WebSocket(url);
	ws.onopen = function() {
		ws.send(id);
	};
	ws.onmessage = function (messageEvent) {
		if (messageEvent.data == 'kenavo') {
			alert('Client disconnected\nYou will be redirected');
			location = '/';
			return;
		}
		var msg = JSON.parse(messageEvent.data);
		switch (msg.action) {
			case 'move':
				//20 is the frame border size
				cursor.style.left = (msg.x + 20 - 5) + 'px';
				cursor.style.top = (msg.y + 20 - 5) + 'px';
				break;
			case 'click':
				cursor.style.backgroundColor = 'blue';
				setTimeout(function() {
					cursor.style.backgroundColor = 'red';
				}, 400);
				break;
			case 'resize':
				frame.style.width = msg.x + 'px';
				frame.style.height = msg.y + 'px';
				break;
			case 'content':
				frame.contentWindow.document.documentElement.innerHTML = msg.data;
				break;
			case 'out':
				cursor.style.backgroundColor = 'silver';
				break;
			case 'over':
				cursor.style.backgroundColor = 'red';
				break;
		}
	};
}