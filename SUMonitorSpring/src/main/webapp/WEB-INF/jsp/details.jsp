<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Notify QA</title>
</head>
<body>
    <!-- Use c:out to display messages. This will avoid XSS attacks -->
    <table border="1">
        <tr>
            <td>ID</td>
            <td><c:out value="${messageDetails.id}" /></td>
        </tr>
        <tr>
            <td>Sent</td>
            <td><c:out value="${messageDetails.at}" /></td>
        </tr>
         <tr>
            <td>From Server Process</td>
            <td><c:out value="${messageDetails.creator}" /></td>
        </tr>
        <tr>
            <td>Email template</td>
            <td><c:out value="${messageDetails.template}" /></td>
        </tr>
        <tr>
            <td>Message</td>
            <td><textarea cols="100" rows="15" disabled="disabled"><c:out value="${messageDetails.rawMessageText}" />
                </textarea></td>
        </tr>
    </table>
</body>
</html>