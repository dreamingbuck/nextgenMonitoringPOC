<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		<title>SUMonitorSpring</title> <script
			src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min.js"></script>
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
	<h1>Stanford NextGen Monitoring (JSP)
		${pageContext.request.servletPath}</h1>

	<jsp:useBean id="now" class="java.util.Date" />
	<h2>${now}</h2>
	<form method="get">
		<label><b>Use this Search</b></label> <input id="q" name="q"
			value="${param.q}" />
	</form>

	<div id="content">
		<datatables:table cdn="true" id="results" data="${results}"
			filter="true" row="row" appear="fadein,800">
			<datatables:column title="Sev" property="severity" filterable="true" />
			<datatables:column title="Owner" property="owner" filterable="true" />
			<datatables:column title="Ack" property="ack" filterable="true" />
			<datatables:column title="Class" property="alertClass"
				filterable="true" />
			<datatables:column title="Name" property="name" filterable="true">
				<c:choose>
					<c:when test="${fn:endsWith(row.name,'.stanford.edu')}">
						<c:url var="link"
							value="https://netdb.stanford.edu/InfoNode?handle=${row.name}" />
						<a href="${link }"><c:out value="${row.name}" /></a>
					</c:when>
					<c:otherwise>
						${row.name}
					</c:otherwise>
				</c:choose>
			</datatables:column>
			<datatables:column title="Event" property="event" filterable="true" />
			<datatables:column title="Headline" property="headline"
				filterable="true">
				<c:url var="link" value="/details/${row.id }" />
				<a href="${link }"><c:out value="${row.headline }" /></a>
			</datatables:column>
			<datatables:column title="Source" property="source" filterable="true" />
			<datatables:column title="Timestamp" property="printableTimestamp"
				filterable="true" />
			<datatables:column title="Name" property="name" filterType="select" />
			<datatables:column title="Action">
				<c:url var="link" value="/event/${row.id }" />
				<form action="${link}" method="post">
					<select name="action" size="1">
						<option value="details">Get details</option>
						<option value="audit">Audit Log...</option>
						<option value="inMaint">Put In Maintenance</option>
						<option value="ack">Acknowledge</option>
						<option value="clear">Clear Symptoms</option>
						<option value="own">Take Ownership</option>
					</select> <input type="submit">
				</form>
			</datatables:column>
		</datatables:table>
	</div>

</body>
</html>