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
    <title>실습실 연장</title>
<body>
    <button type="button" onClick="location.href='/checkExtend'">자리 확인하기</button> <br/>
    <%
        System.out.println("---------------");
        System.out.println(myFinishTime);
    %>
    <%
        if(Objects.nonNull(myFinishTime)){
            if(Objects.isNull(finishTime)) { //뒷사람 예약 없음
                finishTime = myFinishTime.plusDays(1);
            }
    %>
    <h3>최대 연장 가능시간 : <%=finishTime.minusMinutes(1)%></h3>

    <form action="/extendSeat" method="post">
        <input type="datetime-local" name="startTime" value="<%=myFinishTime%>" readonly><br/>
        <input type="datetime-local" name="endTime" min="<%=myFinishTime%>" max="<%=finishTime.minusMinutes(1)%>" required>
        <br/>
        <input type="submit" value="연장하기">
    </form>
    <%}%>
</body>
</html>