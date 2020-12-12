<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Audit</title>
</head>
<body>
<h3>
    Audit
</h3>
<table>
    <c:forEach items="${audit}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>