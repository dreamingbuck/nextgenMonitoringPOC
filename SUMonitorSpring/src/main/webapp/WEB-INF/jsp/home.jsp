<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Notify QA -&gt; SUMonitorSpring</title>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.3.min.js"></script>

</head>
<body>

<form method="get" >
 <label><b>Use this Search</b></label>
 <input id="q" name="q" value="${param.q}"/>
</form>

<div id="content" style="width:800px">
<datatables:table   cdn="true" id="results" data="${results}" filter="true" row="row" appear="fadein,800">
   <datatables:column title="Id" >
   <c:url var="link" value="/details/${row.id }"/>
    <a href="${link }"><c:out value="${row.id }"/></a>
   </datatables:column>
   <datatables:column title="Event" property="event" />
   <datatables:column title="Name" property="name" filterable="true" filterType="select" />
</datatables:table>
</div>

</body>
</html>