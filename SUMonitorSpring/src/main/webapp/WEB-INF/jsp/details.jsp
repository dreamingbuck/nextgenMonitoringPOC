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
th {
	text-align: right;
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
	width: 95%;
	border-collapse: collapse;
	margin: 25px
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
			<script
				src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min.js"></script>
</head>
<body>
	<img
		src="http://identity.stanford.edu/overview/images/emblems/SU_BlockStree_2color.png"
		alt="Stanford University" />
	<h1>Stanford NextGen Monitoring (JSP) ${pageContext.request.servletPath}</h1>
	<!-- Use c:out to display messages. This will avoid XSS attacks -->
	<table border="1">
		<tr>
			<th>ID</th>
			<td><c:out value="${alert.id}" /></td>
		</tr>
		<tr>
			<th>Severity</th>
			<td class=sev${alert.severity}><c:out value="${alert.severity}" /></td>
		</tr>
		<tr>
			<th>Owner</th>
			<td><c:out value="${alert.owner}" /></td>
		</tr>
		<tr>
			<th>Name</th>
			<td><c:out value="${alert.name}" /></td>
		</tr>
		<tr>
			<th>Event</th>
			<td><c:out value="${alert.event}" /></td>
		</tr>
		<tr>
			<th>Source</th>
			<td><c:out value="${alert.source}" /></td>
		</tr>
		<tr>
			<th>Timestamp</th>
			<td><c:out value="${alert.timestamp}" /></td>
		</tr>
		<tr>
			<th>Text</th>
			<td><c:out value="${alert.text}" /></td>
		</tr>
				<tr>
			<th>AuditLog</th>
			<td><c:out value="${alert.auditLog}" /></td>
		</tr>
		<tr>
			<th>Text2</th>
			<td><textarea cols="100" rows="15" disabled="disabled">
					<c:out value="${alert.text}" />
				</textarea></td>
		</tr>
	</table>
</body>
</html>