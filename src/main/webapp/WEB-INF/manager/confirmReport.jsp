<%@ page import="java.util.List" %>
<%@ page import="deu.team.jsp.report.domain.Report" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="reportList" value="${reportList}"/>

<%
    int cnt = 0;
    List<Report> reportList = (List<Report>) pageContext.getAttribute("reportList");
%>

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
    <div class="announce-title">신고 및 문의</div>
    <div class="announce-top-m"></div>
    <table id="confirm" class="display">
        <thead class="text-center">
        <tr>
            <th class="announce-table-th">글번호</th>
            <th class="announce-table-th">제목</th>
            <th class="announce-table-th">작성자</th>
            <th class="announce-table-th">작성 시간</th>
        </tr>
        </thead>
        <tbody class="text-center">

        <%
            for (Report reports : reportList) {
                cnt++;
        %>
        <tr>
            <td><%=cnt%>
            </td>
            <td><a href="/getReport/<%=reports.getId()%>"><%=reports.getReportTitle()%>
            </a></td>
            <td><%=reports.getAccount().getUserName()%>
            </td>
            <td><%=reports.getReportTime().toString().substring(0, 19)%>
            </td>
        </tr>
        <%}%>
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
