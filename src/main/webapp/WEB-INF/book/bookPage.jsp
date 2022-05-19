<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>

<%
    Date now=new Date();

    Date cutLine=new Date();
    cutLine.setHours(16);
    cutLine.setMinutes(30);

    Calendar cal = Calendar.getInstance();
    Calendar tom = Calendar.getInstance();
    cal.setTime(now);
    tom.setTime(now);

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    String today=sdf.format(now);

    cal.add(Calendar.DATE,7);
    tom.add(Calendar.DATE,1);
    String after7=sdf.format(cal.getTime());
    String tomorrow=sdf.format(tom.getTime());

    //요일 구하기
    Integer todayYear=Integer.valueOf(today.substring(0, 4));
    Integer todayMonth=Integer.valueOf(today.substring(5, 7));
    Integer todayDay=Integer.valueOf(today.substring(8, 10));

    LocalDate date = LocalDate.of(todayYear, todayMonth, todayDay);
    DayOfWeek dayOfWeek = date.getDayOfWeek();

    int todayDayOfWeek = dayOfWeek.getValue();
    //월요일부터 일요일까지 1~7의 숫자로 표현됩니다.

%>

<html>
<head>
    <title>실습실 예약</title>
</head>
<body>
    <div align="center">
        <form action="/seat" method="post">
            <%if(now.before(cutLine)){%>
                <select name="labNo">
                    <option value="915">915</option>
                    <option value="916">916</option>
                    <option value="918">918</option>
                    <option value="911">911</option>
                </select>
                <input type="date" name="date" min="<%=today%>" max="<%=after7%>" required>
                <br/>
            <%}else{%>
            <input type="date" name="date" min="<%=tomorrow%>" max="<%=after7%>" required>
            <br/>
            <%}%>
            시작 시간 : <input type="time" name="startTime"  max="24:00" required>
            종료 시간 : <input type="time" name="endTime"  max="24:00" required>
            <br/>
            <input type="hidden" name="todayDayOfWeek" value="<%=todayDayOfWeek%>">
            <input type="submit" value="자리 확인하기"/>
        </form>
    </div>
</body>
</html>
