<%@ page import="deu.team.jsp.book.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>실습실 관리 페이지</title>
</head>
<body>

<p align="center">예약 관리</p>
<table border="1" align="center">
    <thead>
    <th>번호</th>
    <th>학번</th>
    <th>실습실</th>
    <th>좌석(행)</th>
    <th>좌석(열)</th>
    <th>시작 시간</th>
    <th>종료 시간</th>
    <th>승인 / 거절</th>
    </thead>
    <c:forEach var="targetBookList" items="${bookList}" varStatus="status">
        <tr>
            <c:if test="${'READY' eq targetBookList.approveStatus}">

                <td>${status.count}</td>
                <td>${targetBookList.studentId}</td>
                <td>${targetBookList.labNo}</td>
                <td>${targetBookList.seatX}</td>
                <td>${targetBookList.seatY}</td>
                <td>${targetBookList.startTime}</td>
                <td>${targetBookList.endTime}</td>
                <td>
                    <form action="/admin/managelab" method="post">
                        <button type="submit" name="approve" value="${targetBookList.id}" onclick=alert("승인되었습니다.")>승인
                        </button>
                        /
                        <button type="submit" name="cancel" value="${targetBookList.id}" onclick=alert("거절되었습니다.")>거절</button>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<p align="center">승인 목록</p>
<table border="1" align="center">
    <thead>
    <th>번호</th>
    <th>학번</th>
    <th>실습실</th>
    <th>좌석(행)</th>
    <th>좌석(열)</th>
    <th>시작 시간</th>
    <th>종료 시간</th>
    <th>상태 변경</th>
    </thead>

    <c:forEach var="targetApproveBookList" items="${bookApproveList}" varStatus="status">
        <tr>
            <c:if test="${'APPROVE' eq targetApproveBookList.approveStatus}">

                <td>${status.count}</td>
                <td>${targetApproveBookList.studentId}</td>
                <td>${targetApproveBookList.labNo}</td>
                <td>${targetApproveBookList.seatX}</td>
                <td>${targetApproveBookList.seatY}</td>
                <td>${targetApproveBookList.startTime}</td>
                <td>${targetApproveBookList.endTime}</td>
                <td>
                    <form action="/admin/managelab" method="post">
                        <button type="submit" name="cancel" value="${targetApproveBookList.id}" onclick=alert("거절되었습니다.")>거절</button>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<p align="center">거절 목록</p>
<table border="1" align="center">
    <thead>
    <th>번호</th>
    <th>학번</th>
    <th>실습실</th>
    <th>좌석(행)</th>
    <th>좌석(열)</th>
    <th>시작 시간</th>
    <th>종료 시간</th>
    <th>재승인</th>
    </thead>

    <c:forEach var="targetRejectList" items="${bookRejectList}" varStatus="status">
        <tr>
            <c:if test="${'REJECT' eq targetRejectList.approveStatus}">

                <td>${status.count}</td>
                <td>${targetRejectList.studentId}</td>
                <td>${targetRejectList.labNo}</td>
                <td>${targetRejectList.seatX}</td>
                <td>${targetRejectList.seatY}</td>
                <td>${targetRejectList.startTime}</td>
                <td>${targetRejectList.endTime}</td>
                <td>
                    <form action="/admin/managelab" method="post">
                        <button type="submit" name="approve" value="${targetRejectList.id}" onclick=alert("승인되었습니다.")>승인</button>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>