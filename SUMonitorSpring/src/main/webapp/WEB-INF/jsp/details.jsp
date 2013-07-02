<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>NextGen Monitoring</title>
</head>
<body>
    <!-- Use c:out to display messages. This will avoid XSS attacks -->
    <table border="1">
        <tr>
            <td>ID</td>
            <td><c:out value="${Alert.id}" /></td>
        </tr>
        <tr>
            <td>Severity</td>
            <td><c:out value="${Alert.severity}" /></td>
        </tr>
         <tr>
            <td>Owner</td>
            <td><c:out value="${Alert.owner}" /></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><c:out value="${Alert.name}" /></td>
        </tr>
                <tr>
            <td>Event</td>
            <td><c:out value="${Alert.event}" /></td>
        </tr>
                <tr>
            <td>Source</td>
            <td><c:out value="${Alert.source}" /></td>
        </tr>
                <tr>
            <td>Timestamp</td>
            <td><c:out value="${Alert.timestamp}" /></td>
        </tr>
                <tr>
            <td>Text</td>
            <td><c:out value="${Alert.text}" /></td>
        </tr>
        <tr>
            <td>Text2</td>
            <td><textarea cols="100" rows="15" disabled="disabled"><c:out value="${alert.text}" />
                </textarea></td>
        </tr>
    </table>
</body>
</html>