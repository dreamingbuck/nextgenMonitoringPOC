<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Test Template</title>


</head>
<body>

<form:form method="post" modelAttribute="templateForm">
    <table>
    <tr>  
        <td><form:label path="service">Service</form:label></td>  
        <td><form:input path="service"></form:input></td>  
        <td><form:errors cssStyle="color:red" path="service" /></td>
    </tr>
    <tr>  
        <td><form:label path="template">Template Name</form:label></td>  
        <td><form:input path="template"></form:input></td>  
        <td><form:errors cssStyle="color:red" path="template" /></td>
    </tr>
    <tr>  
        <td><form:label path="to">to address</form:label></td>  
        <td><form:input path="to"></form:input></td>  
        <td><form:errors cssStyle="color:red" path="to" /></td>
    </tr>  
    </table>  
        <br/>
    
    <form:label path="variables">Comma separated key=value parings</form:label>
    <br/>
    <form:errors cssStyle="color:red" path="variables" /><br/>
    <form:textarea path="variables" rows="5" cols="30" />
<br/>
 
<input type="submit" value="Send" />
     
</form:form>

</body>
</html>