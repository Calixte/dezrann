<jsp:useBean id="recordingId" scope="request" type="java.lang.String"/>
<jsp:useBean id="records" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/monitor.css"/>
	<script src="js/monitoring.js"></script>
	<script src="js/replay.js"></script>
</head>
<body>
<p style="display:none;" id ="jsonContainer">${records}</p>
<h1>You are replaying recording ${recordingId}</h1>
<button onclick="location='${pageContext.request.contextPath}'">&#8962;</button>
<div id="cursor"></div>
<div id="frame">
	<iframe id="iframe"></iframe>
	<div id="filter"></div>
</div>
</body>
</html>