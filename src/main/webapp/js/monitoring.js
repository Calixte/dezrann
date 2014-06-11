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
			alert('Client disconnected');
			return;
		}
		var msg = JSON.parse(messageEvent.data);
		switch (msg.action) {
			case 'move' :
				document.getElementById('cursor').style.left = msg.x + 'px';
				document.getElementById('cursor').style.top = msg.y + 'px';
				break;
			case 'click' :
				break;
			case 'resize' :
				document.getElementById('frame').style.width = msg.x + 'px';
				document.getElementById('frame').style.height = msg.y + 'px';
				break
		}
	}
}