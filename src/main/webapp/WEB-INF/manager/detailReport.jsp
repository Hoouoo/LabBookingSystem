<%@ page import="deu.team.jsp.report.domain.Report" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="detailReport" value="${detailReport}"/>

<%
    Report report= (Report) pageContext.getAttribute("detailReport");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>제목 : <%=report.getReportTitle()%></h3>
    <h3>작성자 : <%=report.getAccount().getUserName()%></h3>
    <h3>작성 시간 : <%=report.getReportTime().toString().substring(0, 19)%></h3>
    <textarea cols="20" rows="2" readonly><%=report.getReportContent()%></textarea>
</body>
</html>
