<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="sessions" scope="request" type="java.util.HashMap"/>
<jsp:useBean id="recordings" scope="request" type="java.util.List"/>
<!DOCTYPE html>
<html>
<head>
	<title>Dezrann - User behaviour analysis</title>
	<meta charset="utf-8" />
	<script src="js/index.js"></script>
</head>
<body>
	<h1>Dezrann - User behaviour analysis</h1>
	<p>User connected: ${sessions.size()}</p>
	<table>
		<caption>Sessions</caption>
		<tr>
			<th>ID</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${sessions}" var="session">
			<tr>
				<td>${session.key}</td>
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