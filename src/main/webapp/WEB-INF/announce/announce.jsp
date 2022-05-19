<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공지사항 등록</title>
</head>

<jsp:include page="../fragment/adminHeader.jsp"/>

<body>
    <h3>공지사항을 등록하세요</h3>
    <form action="/postAnnounce" method="post">
        <c:choose>
            <c:when test="${empty context}">
                <textarea name="announceContent"  rows="10" clos="10"></textarea>
            </c:when>
            <c:otherwise>
                <textarea name="announceContent" rows="10" clos="10">${context}</textarea>
            </c:otherwise>
        </c:choose>
        <input type="submit" value="등록">
    </form>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
