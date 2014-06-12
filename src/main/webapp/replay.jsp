<jsp:useBean id="recordingId" scope="request" type="java.lang.String"/>
<jsp:useBean id="recordsGson" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="monitor.css"/>
	<script type="text/javascript">
		var records = '${records}';
	</script>
</head>
<body>
<h1>You are replaying recording ${recordingId}</h1>
<p style="display:none;" id ="abc">${recordsGson}</p>
<div id="cursor"></div>
<div id="frame">
	<iframe id="iframe"></iframe>
</div>
</body>
</html>