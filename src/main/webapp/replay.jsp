<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="monitor.css"/>
	<script src="js/monitoring.js"></script>
	<script>watcherInit('${recordingId}', {path: '/socket/replay'})</script>
</head>
<body>
<h1>You are replaying recording ${recordingId}</h1>
<div id="cursor"></div>
<div id="frame">
	<iframe id="iframe"></iframe>
</div>
</body>
</html>