
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Sorry  this content does not load

    <c:if test="${!requestScope.status}">
        <h2>sorry</h2>
        <a href="${pageContext.request.contextPath}/index">Back</a>
    </c:if>
</body>
</html>
