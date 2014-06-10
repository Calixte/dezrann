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
		document.getElementById('console').innerHTML += '<br>' + messageEvent.data;
	}
}
