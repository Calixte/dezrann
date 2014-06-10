<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="monitor.css"/>
	<script src="js/monitoring.js"></script>
	<script>watcherInit('${session.id}', {path: '/socket/watch'})</script>
</head>
<body>
<h1>You are monitoring session ${session.id}</h1>
<div id="frame">
	<div id="cursor">
		<div></div>
	</div>
</div>
</body>
</html>