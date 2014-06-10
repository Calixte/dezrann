<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<script src="js/monitoring.js"></script>
	<script>watcherInit(${session.id}, {path: 'watch'})</script>
</head>
<body>
<h1>You are monitoring session ${session.id}</h1>
<div id="console">

</div>
</body>
</html>