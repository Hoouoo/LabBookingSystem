<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공지사항 등록</title>
    <link href="../../css/announce.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/adminHeader.jsp"/>

<body>
<div class="announce-box announce-pt-12">
    <div class="announce-title">공지사항을 등록하세요</div>
    <div class="announce-title">해당 공지사항은 실습실 예약시 나타납니다.</div>
    <form action="/postAnnounce" method="post">
        <c:choose>
            <c:when test="${empty context}">
                <textarea name="announceContent" class="announce-textarea"></textarea>
            </c:when>
            <c:otherwise>
                <textarea name="announceContent" class="announce-textarea">${context}</textarea>
            </c:otherwise>
        </c:choose>
        <br/>
        <div class="form-group mb-3">
            <input type="submit" class="btn btn-primary btn-block announce-button" value="등록">
        </div>
    </form>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
