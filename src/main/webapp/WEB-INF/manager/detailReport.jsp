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
    <link href="../../css/announce.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/adminHeader.jsp"/>
<body>

<div class="announce-box-2 announce-pt-10">
    <div class="announce-title-left">제목:  &nbsp;<label class="announce-title-left-2"><%=report.getReportTitle()%></label></div>
<%--    <h3>제목 : <%=report.getReportTitle()%></h3>--%>
    <div class="announce-sub-title-2">작성자 : &nbsp;<label class="announce-sub-title-3"> <%=report.getAccount().getUserName()%></label></div>
    <div class="announce-sub-title-2">작성 시간 : &nbsp;<label class="announce-sub-title-3"> <%=report.getReportTime().toString().substring(0, 19)%></label></div>
    <textarea class="announce-textarea-3" cols="20" rows="2" readonly><%=report.getReportContent()%></textarea>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
