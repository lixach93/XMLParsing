<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="../fragments/header.jsp" />
<body>
    <c:if test="${requestScope.status}">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Soil</th>
                    <th>Name</th>
                    <th>Origin</th>
                    <th>Color leaf</th>
                    <th>Color stem</th>
                    <th>Length</th>
                    <th>Temperature</th>
                    <th>Watering</th>
                    <th>Lighting</th>
                    <th>Multiplying</th>
                    <th>First Mention</th>
                </tr>
            </thead>
        <c:forEach var="flower" items="${flowers}">
            <tbody>
                <tr>
                    <td><c:out value="${flower.id}"/></td>
                    <td><c:out value="${flower.soil}"/></td>
                    <td><c:out value="${flower.name}"/></td>
                    <td><c:out value="${flower.origin}"/></td>
                    <td><c:out value="${flower.visual.leafColor}"/></td>
                    <td><c:out value="${flower.visual.stemColor}"/></td>
                    <td><c:out value="${flower.visual.length}"/></td>
                    <td><c:out value="${flower.growingTip.temperature}"/></td>
                    <td><c:out value="${flower.growingTip.watering}"/></td>
                    <td><c:out value="${flower.growingTip.lighting}"/></td>
                    <td><c:out value="${flower.multiplying}"/></td>
                    <td><c:out value="${flower.firstMention}"/></td>
                </tr>
            </tbody>
        </c:forEach>
</table>
    </c:if>
    <c:if test="${!requestScope.status}">
        <h2 style="color:red"> This file is not valid !!! </h2>
        <h3> Please , press XMLParsing label</h3>
    </c:if>
    <jsp:include page="../fragments/footer.jsp" />
</body>
</html>
