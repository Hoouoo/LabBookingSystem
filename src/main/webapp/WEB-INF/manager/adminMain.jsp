<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>관리자</title>
</head>

<jsp:include page="../fragment/adminHeader.jsp"/>
<body>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <button type="button" onClick="location.href='adminAccountModifyPage'">회원정보 수정</button> <br/>
    <button type="button" onClick="location.href='admin/schedule'">시간표 추가</button> <br/>
    <button type="button" onClick="location.href=''">실습실 관리(승인/취소)</button> <br/>
    <button type="button" onClick="location.href='announcePage'">공지사항 등록</button> <br/>
    <button type="button" onClick="location.href='confirmReportPage'">신고 및 문의사항 확인</button> <br/>
    <button type="button" onClick="location.href='logout'">로그아웃</button> <br/>

    <form action="/manager" method="post">
        <button type="submit" name="keyRole" value="STUDENT">일반 사용자 암호 생성</button>
        <c:if test="${null ne sessionScope.keyStudent}">
            <c:out value="${sessionScope.keyStudent}"/>
        </c:if>
        <button type="submit" name="keyRole" value="PROFESSOR">교수 사용자 암호 생성</button>
        <c:if test="${null ne sessionScope.keyProfessor}">
            <c:out value="${sessionScope.keyProfessor}"/>
        </c:if>
<%--        <c:if test="${null ne sessionScope.keyMsg}">--%>
<%--            <script>--%>
<%--                alert("${sessionScope.keyMsg}");--%>
<%--            </script>--%>
<%--        </c:if>--%>
    </form>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
