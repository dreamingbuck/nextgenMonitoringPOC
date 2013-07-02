<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.*"%>

<html>
<head>
<meta charset="UTF-8">
	<title>Stanford NextGen Monitoring</title>
	<style>
/* css comments */
body {
	font-size: small;
	font-family: Arimo, Helvetica, Arial, sans-serif;
}

h1,h2,td.severity {
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
	width: 95%;
	border-collapse: collapse;
	margin: 25px
}

tr {
	background-color: #8c1515;
}

tr.header {
	background-color: transparent;
}

tr.sev1 {
	background-color: red;
}

tr.sev2 {
	background-color: orange;
}

tr.sev3 {
	background-color: yellow;
}

tr.sev4 {
	background-color: aqua;
}

tr.sev5 {
	background-color: green;
}
</style>
<!-- 	<link href="https://www.stanford.edu/su-identity/css/su-identity.css" -->
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
	<!-- Brandbar snippet start -->
	<div id="brandbar">
		<div class="container">
			<a href="http://www.stanford.edu"> <img
				src="https://www.stanford.edu/su-identity/images/brandbar-stanford-logo@2x.png"
				alt="Stanford University" width="152" height="23"></a>
		</div>
		<!-- .container end -->
	</div>
	<!-- #brandbar end -->
	<!-- Brandbar snippet end -->
	<img
		src="http://identity.stanford.edu/overview/images/emblems/SU_BlockStree_2color.png"
		alt="Stanford University" />
	<h1>Stanford NextGen Monitoring (JSP) ${pageContext.request.servletPath}</h1>

	<jsp:useBean id="now" class="java.util.Date" />
	<h2>${now}</h2>
	<table border=1>
		<tr class="header">
			<th>Sev</th>
			<th>Owner</th>
			<th>Name</th>
			<th>Event</th>
			<th>Headline</th>
			<th>Source</th>
			<th>Timestamp</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${alertList}" var="alert">
			<tr class="sev${alert.severity}">
				<td>${alert.severity}</td>
				<td>${alert.owner}</td>
				<td>${alert.name}</td>
				<td>${alert.event}</td>
				<td>${alert.text}</td>
				<td>${alert.source}</td>
				<td>${alert.timestamp}</td>
				<td><select name="action" size="1">
						<option value="details">Get details</option>
						<option value="audit">Audit Log...</option>
						<option value="inMaint">Put In Maintenance</option>
						<option value="ack">Acknowledge</option>
						<option value="clear">Clear Symptoms</option>
						<option value="own">Take Ownership</option>
				</select></td>
			</tr>
		</c:forEach>
	</table>
	<form method="get" action="form.html">
		<input type="submit" value="Clickable Button">
	</form>
</body>
</html>