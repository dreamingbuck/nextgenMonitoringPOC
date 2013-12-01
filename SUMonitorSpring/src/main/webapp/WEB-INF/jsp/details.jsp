<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<meta charset="UTF-8">
	<title>Stanford NextGen Monitoring</title>
	<style>
/* css comments */
body {
	font-size: small;
	font-family: Arimo, Helvetica, Arial, sans-serif;
}

h1,h2 {
	text-align: center;
}

h1 {
	font-size: 150%;
}

h2 {
	font-size: 120%;
	color: purple;
}

table,th,td {
	border: thin solid black;
}

table {
	width: 100%;
	border-collapse: collapse;
	table-layout:fixed;
}

table.outer {
	width: 95%;
	margin: 25px;
}

tr {
	background-color: transparent;
}

tr.header {
	background-color: transparent;
}

td.sev1 {
	background-color: red;
}

td.sev2 {
	background-color: orange;
}

td.sev3 {
	background-color: yellow;
}

td.sev4 {
	background-color: aqua;
}

td.sev5 {
	background-color: green;
}

th.row {
	text-align: right;
	width: 7em;
}

th.when,td.when {
	width: 10em;	
}

th.who,td.who {
	width: 5em;
}

th.what,td.what {
	
}
</style>
	<link href="https://www.stanford.edu/su-identity/css/su-identity.css"
		rel="stylesheet">
		<link
			href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700"
			rel="stylesheet" type="text/css">
			<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
			<!--[if IE 8]>
  <link rel="stylesheet" type="text/css" href="https://www.stanford.edu/su-identity/css/ie/ie8.css" />
<![endif]-->
			<!--[if IE 7]>
  <link rel="stylesheet" type="text/css" href="https://www.stanford.edu/su-identity/css/ie/ie7.css" />
<![endif]-->
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min.js"></script>
</head>
<body>
	<img
		src="http://identity.stanford.edu/overview/images/emblems/SU_BlockStree_2color.png"
		alt="Stanford University" />
	<h1>Stanford NextGen Monitoring (JSP)
		${pageContext.request.servletPath}</h1>
	<!-- Use c:out to display messages. This will avoid XSS attacks by escaping the XML -->
	<!-- probably need to do mod_jk stuff see http://webauth.stanford.edu/manual/mod/mod_webauth.html -->
	 WEBAUTH_USER is
	<%
		out.print(request.getAttribute("WEBAUTH_USER"));
	%>
	<table class=outer border="1">
		<tr>
			<th class="row">ID</th>
			<td><c:out value="${alert.id}" /></td>
		</tr>
		<tr>
			<th class="row">Severity</th>
			<td class=sev${alert.severity}><c:out value="${alert.severity}" /></td>
		</tr>
		<tr>
			<th class="row">Owner</th>
			<td><c:out value="${alert.owner}" /></td>
		</tr>
		<tr>
			<th class="row">Ack</th>
			<td><c:out value="${alert.ack}" /></td>
		</tr>
		<tr>
			<th class="row">Class</th>
			<td><c:out value="${alert.alertClass}" /></td>
		</tr>
				<tr>
			<th class="row">Name</th>
			<td><c:out value="${alert.name}" /></td>
		</tr>
		<tr>
			<th class="row">Event</th>
			<td><c:out value="${alert.event}" /></td>
		</tr>
		<tr>
			<th class="row">Source</th>
			<td><c:out value="${alert.source}" /></td>
		</tr>
		<tr>
			<th class="row">Timestamp</th>
			<td><c:out value="${alert.printableTimestamp}" /></td>
		</tr>
		<tr>
			<th class="row">Text</th>
			<td>${alert.text}</td>
		</tr>
		<tr>
			<th class="row">Audit Log</th>
			<td>
				<table border="1">
					<tr>
						<th class="when">When</th>
						<th class="who">Who</th>
						<th class="what">What</th>
					</tr>
					<c:forEach var="auditEntry" items="${alert.auditLog}">
						<tr>
							<td class="when">${auditEntry.printableWhen}</td>
							<td class="who">${auditEntry.who}</td>
							<td class="what">${auditEntry.what}</td>
						</tr>

					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<th class="row">Text2</th>
			<td><textarea cols="100" rows="15" disabled="disabled">
					${alert.text}
				</textarea></td>
		</tr>
	</table>
</body>
</html>