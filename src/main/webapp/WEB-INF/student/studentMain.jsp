<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생</title>
</head>
<jsp:include page="../fragment/studentHeader.jsp"/>
<body>
    <button type="button" onClick="location.href='/nowLabStatusPage'">실습실 사용 현황</button> <br/>
    <button type="button" onClick="location.href='/studentAccountModifyPage'">회원정보 수정</button> <br/>
    <button type="button" onClick="location.href='/bookPage'">실습실 예약</button> <br/>
    <button type="button" onClick="location.href='/bookExtendPage'">실습실 연장</button> <br/>
    <button type="button" onClick="location.href='/myBookStatusPage'">예약 정보 확인</button> <br/>
    <button type="button" onClick="location.href='/reportPage'">신고 및 문의</button> <br/>
    <button type="button" onClick="location.href='/logout'">로그아웃</button> <br/>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>
