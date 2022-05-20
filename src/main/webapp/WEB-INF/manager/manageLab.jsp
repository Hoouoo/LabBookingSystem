<%@ page import="deu.team.jsp.book.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>실습실 관리 페이지</title>
    <link href="../../css/lab.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
</head>
<jsp:include page="../fragment/adminHeader.jsp"/>
<body>

<div class="lab-box">
    <div class="lab-box-2" id="manage_table">
        <div class="lab-title">예약 관리</div>
        <span class="lab-title-group-m">
        <a href="#approve_table" class="lab-nav-title-border">
            승인 목록
        </a>
        <a href="#reject_table" class="lab-nav-title">
            거절 목록
        </a>
        </span>
        <table id="manage" style="width:100%">
            <thead class="text-center">
            <tr>

            <th>번호</th>
                <th>학번</th>
                <th>실습실</th>
                <th>좌석(행)</th>
                <th>좌석(열)</th>
                <th>시작 시간</th>
                <th>종료 시간</th>
                <th>승인 / 거절</th>
            </tr>
            </thead>
            <tbody class="text-center">

            <c:forEach var="targetBookList" items="${bookList}" varStatus="status">

                <c:if test="${'READY' eq targetBookList.approveStatus}">
                    <tr>
                        <td>${status.count}</td>
                        <td>${targetBookList.studentId}</td>
                        <td>${targetBookList.labNo}</td>
                        <td>${targetBookList.seatX}</td>
                        <td>${targetBookList.seatY}</td>
                        <td>${targetBookList.startTime}</td>
                        <td>${targetBookList.endTime}</td>
                        <td>
                            <form action="/admin/managelab" method="post">

                                <button type="submit" class="btn btn-secondary  btn-sm  btn-block lab-button-m" name="approve" value="${targetBookList.id}"
                                        onclick=alert("승인되었습니다.")>승인
                                </button>
                                <button type="submit" class="btn btn-dark   btn-sm  btn-block lab-button-m" name="cancel" value="${targetBookList.id}"
                                        onclick=alert("거절되었습니다.")>거절
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>

            </tbody>
        </table>
        <script>
            $(document).ready(function () {
                $('#manage').DataTable();
            });
        </script>
    </div>


    <div class="lab-box-2" id="approve_table">
        <div class="lab-title">승인 목록</div>
        <span class="lab-title-group-m">
        <a href="#manage_table" class="lab-nav-title-border">
            예약 관리
        </a>
        <a href="#reject_table" class="lab-nav-title">
            거절 목록
        </a>
        </span>
        <table id="approve" class="display" style="width:100%">
            <thead class="text-center">
            <tr>
                <th class="lab-table-box">번호</th>
                <th>학번</th>
                <th>실습실</th>
                <th>좌석(행)</th>
                <th>좌석(열)</th>
                <th>시작 시간</th>
                <th>종료 시간</th>
                <th>상태 변경</th>
            </tr>
            </thead>
            <tbody class="text-center">

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
                                <button type="submit" class="btn btn-dark btn-sm btn-block lab-button-m" name="cancel" value="${targetApproveBookList.id}"
                                        onclick=alert("거절되었습니다.")>거절
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script>
            $(document).ready(function () {
                $('#approve').DataTable();
            });
        </script>
    </div>

    <div class="lab-box-2" id="reject_table">
        <div class="lab-title">거절 목록</div>
        <span class="lab-title-group-m">
        <a href="#manage_table" class="lab-nav-title-border">
            예약 관리
        </a>
        <a href="#approve_table" class="lab-nav-title">
            승인 목록
        </a>
        </span>
        <table id="reject" class="display" style="width:100%">
            <thead class="text-center">
            <tr>
                <th>번호</th>
                <th>학번</th>
                <th>실습실</th>
                <th>좌석(행)</th>
                <th>좌석(열)</th>
                <th>시작 시간</th>
                <th>종료 시간</th>
                <th>재승인</th>
            </tr>
            </thead>
            <tbody class="text-center">


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
                                <button type="submit" class="btn btn-secondary btn-sm  btn-block lab-button-m" name="approve" value="${targetRejectList.id}"
                                        onclick=alert("승인되었습니다.")>승인
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script>
            $(document).ready(function () {
                $('#reject').DataTable();
            });
        </script>

    </div>
</div>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>