<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="finishTime" value="${finishTime}"/>
<c:set var="myFinishTime" value="${myFinishTime}"/>


<%
    LocalDateTime myFinishTime = (LocalDateTime) pageContext.getAttribute("myFinishTime");
    LocalDateTime finishTime = (LocalDateTime) pageContext.getAttribute("finishTime");
%>

<html>
<head>
    <title>실습실 연장</title>
    <link href="../../css/schedule.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="../fragment/studentHeader.jsp"/>
<body>
<div class="schedule-box">
    <div class="schedule-title mb-3">실습실 연장</div>
    <button type="button" class="btn btn-primary btn-sm"  onClick="location.href='/checkExtend'">자리 확인하기</button>
    <br/>
    <%
        System.out.println("---------------");
        System.out.println(myFinishTime);
    %>
    <%
        if (Objects.nonNull(myFinishTime)) {
            if (Objects.isNull(finishTime)) { //뒷사람 예약 없음
                finishTime = myFinishTime.plusDays(1);
            }
    %>
    <div class="schedule-sub-title">최대 연장 가능시간 : <%=finishTime.minusMinutes(1)%></div>

    <form action="/extendSeat" method="post">
        <div class="schedule-sub-title"> 시작 시간</div>
        <input class="schedule-input-box mb-3" type="datetime-local" name="startTime" value="<%=myFinishTime%>" readonly><br/>
        <div class="schedule-sub-title"> 종료 시간</div>
        <input class="schedule-input-box mb-4" type="datetime-local" name="endTime" min="<%=myFinishTime%>" max="<%=finishTime.minusMinutes(1)%>"
               required>
        <br/>
        <input type="submit" class="btn btn-secondary btn-block schedule-btn-fw" value="연장하기">
    </form>
    <%}%>
</div>

</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>