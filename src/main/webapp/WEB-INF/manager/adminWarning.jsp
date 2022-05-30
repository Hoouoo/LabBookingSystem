<%@ page import="java.util.List" %>
<%@ page import="deu.team.jsp.report.domain.Report" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="reportList" value="${reportList}"/>

<html>
<head>
    <title>Title</title>
    <link href="../../css/announce.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
</head>


<jsp:include page="../fragment/adminHeader.jsp"/>
<body>
<div class="announce-box announce-pt-10">
    <div class="announce-title">사용자 관리</div>
    <div class="announce-top-m"></div>
    <table id="confirm" class="display">
        <thead class="text-center">
        <tr>
            <th class="announce-table-th">학번</th>
            <th class="announce-table-th">이름</th>
            <th class="announce-table-th">연락처</th>
            <th class="announce-table-th">누적 경고횟수</th>
            <th class="announce-table-th"> </th>
        </tr>
        </thead>
        <tbody class="text-center">
        <c:forEach var="targetStudent" items="${studentList}">
        <tr>
            <td><c:out value="${targetStudent.studentId}"/> </td>
            <td><c:out value="${targetStudent.userName}"/> </td>
            <td><c:out value="${targetStudent.phoneNo}"/> </td>
            <td><c:out value="${targetStudent.warning.warningCnt}"/> </td>
            <form action="/admin/warning" method="post">
            <td>
                <button type="submit" class="btn btn-primary  btn-sm  btn-block" name="warning" onclick="alert('${targetStudent.userName}학생에게 경고가 부여되었습니다.')" value="${targetStudent.studentId}">경고 주기</button>
                <button type="submit" class="btn btn-secondary  btn-sm  btn-block" name="reset" onclick="alert(('${targetStudent.userName}학생의 경고가 초기화되었습니다.'))" value="${targetStudent.studentId}">경고 초기화</button>
            </td>
            </form>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        $(document).ready(function () {
            $('#confirm').DataTable();
        });
    </script>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
