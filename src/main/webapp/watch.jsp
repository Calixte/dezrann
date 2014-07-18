<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/monitor.css"/>
	<script src="js/monitoring.js"></script>
	<script src="js/watch.js"></script>
	<script>watcherInit('${session.id}', {path: '${pageContext.request.contextPath}/socket/watch', host: '172.28.51.98'})</script>
</head>
<body>
	<h1>You are monitoring session ${session.id}</h1>
	<p>
		<span id="disconnected" style="display: none">User disconnected! Wait for him to visit a new page</span>
	</p>
	<button onclick="location='/dezrann'">&#8962;</button>
	<button id="toggleRecording">Start recording</button>
	<div id="cursor"></div>
	<div id="frame">
		<iframe id="iframe"></iframe>
		<div id="filter"></div>
	</div>
</body>
</html>