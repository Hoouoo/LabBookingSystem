<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>

<%
    Date now = new Date();

    Date cutLine = new Date();
    cutLine.setHours(16);
    cutLine.setMinutes(30);

    Calendar cal = Calendar.getInstance();
    Calendar tom = Calendar.getInstance();
    cal.setTime(now);
    tom.setTime(now);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(now);

    cal.add(Calendar.DATE, 7);
    tom.add(Calendar.DATE, 1);
    String after7 = sdf.format(cal.getTime());
    String tomorrow = sdf.format(tom.getTime());

    //요일 구하기
    Integer todayYear = Integer.valueOf(today.substring(0, 4));
    Integer todayMonth = Integer.valueOf(today.substring(5, 7));
    Integer todayDay = Integer.valueOf(today.substring(8, 10));

    LocalDate date = LocalDate.of(todayYear, todayMonth, todayDay);
    DayOfWeek dayOfWeek = date.getDayOfWeek();

    int todayDayOfWeek = dayOfWeek.getValue();
    //월요일부터 일요일까지 1~7의 숫자로 표현됩니다.

%>

<html>
<head>
    <title>실습실 예약</title>
    <link href="../../../css/schedule.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="../fragment/studentHeader.jsp"/>
<body>
<div class="schedule-box">
    <div class="schedule-title">실습실 예약</div>
    <h3>자정 넘어 실습실 사용하실 분은 종료 시간을 11시59분으로 예약 하고 그 이후 연장해주세요</h3>
    <form action="/seat" method="post">
        <%if (now.before(cutLine)) {%>
        <div class="schedule-in-box">
            <div class="schedule-sub-title"> 강의실</div>
            <select class="form-select form-select-sm" name="labNo">
                <option value="915">915</option>
                <option value="916">916</option>
                <option value="918">918</option>
                <option value="911">911</option>
            </select>
        </div>

        <br/>
        <div class="schedule-in-box-2">
            <div class="schedule-sub-title"> 시작 시간</div>
            <input class="schedule-input-box" type="datetime-local" name="startTime" min="<%=today%>" max="<%=after7%>" required>
        </div>
        <%} else {%>

        <div class="schedule-in-box-2">
            <div class="schedule-sub-title"> 시작 시간</div>
            <input class="schedule-input-box" type="datetime-local" name="startTime" min="<%=tomorrow%>" max="<%=after7%>" required>
        </div>
        <%}%>
        <br/>
        <div class="schedule-in-box-2">
            <div class="schedule-sub-title"> 종료 시간</div>
            <input class="schedule-input-box" type="datetime-local" name="endTime" max="24:00" required>
        </div>
        <br/>
<%--        <div class="schedule-in-box-2 mb-4">--%>

<%--            <div class="schedule-sub-title"> 종료 시간</div>--%>
<%--            <input class="schedule-input-box" type="datetime-local" name="endTime" max="24:00" required>--%>
<%--        </div>--%>
        <br/>
        <input type="hidden" name="todayDayOfWeek" value="<%=todayDayOfWeek%>">

        <input type="submit" class="btn btn-secondary btn-block schedule-btn-fw" value="자리 확인하기"/>
    </form>
</div>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>
