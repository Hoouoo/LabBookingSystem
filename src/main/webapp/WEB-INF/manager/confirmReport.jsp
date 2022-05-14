<%@ page import="java.util.List" %>
<%@ page import="deu.team.jsp.report.domain.Report" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="reportList" value="${reportList}"/>

<%
    int cnt=0;
    List<Report> reportList= (List<Report>) pageContext.getAttribute("reportList");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <thead>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성 시간</th>
        </thead>
        <%
            for(Report reports: reportList){
                cnt++;
            %>
        <tr>
            <td><%=cnt%></td>
            <td><a href="/getReport/<%=reports.getId()%>"><%=reports.getReportTitle()%></a></td>
            <td><%=reports.getAccount().getUserName()%></td>
            <td><%=reports.getReportTime().toString().substring(0, 19)%></td>
        </tr>
        <%}%>
    </table>
</body>
</html>
