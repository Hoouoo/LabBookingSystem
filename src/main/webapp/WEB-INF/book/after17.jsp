<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%
    Date now=new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    String today=sdf.format(now);

    cal.add(Calendar.DATE,7);
    String after7=sdf.format(cal.getTime());
%>

<html>
<head>
    <title>17시 이후 강의실 예약</title>
</head>
<body>
    <div align="center">
        <form action="/seat" method="post">
            <input type="date" name="date" min="<%=today%>" max="<%=after7%>" required>
            <br/>
            시작 시간 : <input type="time" name="startTime" value="17:00" min="17:00" max="24:00" required>
            종료 시간 : <input type="time" name="endTime" value="17:00" min="17:00" max="24:00" required>
            <br/>
            <input type="submit" value="자리 확인하기"/>
        </form>
    </div>
</body>
</html>
