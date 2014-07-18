<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:useBean id="sessions" scope="request" type="java.util.Map<java.lang.String, javax.websocket.Session>"/> --%>
<jsp:useBean id="recordings" scope="request" type="java.util.List<bzh.dezrann.recording.databean.Recording>"/>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/index.css"/>
	<script src="js/index.js"></script>
</head>
<body>
	<h1>Dezrann - User behaviour analysis</h1>
	<c:if test="${not empty sessionScope.error}">
		<p>Error: ${sessionScope.error}</p>
	</c:if>
	<c:if test="${not empty param.reason}">
		<p>The watching session has terminated (${param.reason})</p>
	</c:if>
	<table>
		<caption>Sessions (${sessions.size()})</caption>
		<tr>
			<th>ID</th>
			<th>UUID</th>
			<th>City</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${sessions}" var="session">
			<tr>
				<td>${session.key}</td>
				<td>${session.value.userProperties.cookie}</td>
				<td>${session.value.userProperties.userInfos.city}</td>
				<td><a href="<c:url value="/watch?id=${session.key}"/>">Watch</a></td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<caption>Recordings</caption>
		<tr>
			<th>ID</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${recordings}" var="recording">
			<tr>
				<td>${recording.id}</td>
				<td>
					<a href="<c:url value="/replay?id=${recording.id}" />">Replay</a>
					<a href="#" data-id="${recording.id}" class="delete-link">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>