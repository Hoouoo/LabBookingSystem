<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="bookService" scope="page" class="deu.team.jsp.book.BookService"/>
<jsp:useBean id="bookStatus" scope="page" class="deu.team.jsp.book.BookStatus"/>
<%
    String date = request.getParameter("date");
    String startTime = request.getParameter("startTime");
    String endTime = request.getParameter("endTime");

    int year=Integer.valueOf(date.substring(0,4));
    int month=Integer.valueOf(date.substring(5,7));
    int day=Integer.valueOf(date.substring(8,10));

    int startHour=Integer.valueOf(startTime.substring(0,2));
    int endHour=Integer.valueOf(endTime.substring(0,2));

    LocalDateTime start= LocalDateTime.of(year, month, day, startHour, 0);
    LocalDateTime end= LocalDateTime.of(year, month, day, endHour, 0);
%>


<c:set target="${bookService}" property="roomNo" value="${param.roomNo}"/>
<c:set target="${bookService}" property="startTime" value="${param.start}"/>
<c:set target="${bookService}" property="roomNo" value="${param.end}"/>
<%bookStatus.checkSeat();%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <div align="center">
        <form action="/book" method="post">
            <b>칠판</b><br/>
            &nbsp;&nbsp;&nbsp;
            <% for(int a=1;a<=8;a++){%>
            <small><%=a%></small> &nbsp;
            <%}%>
            <br/>

            <% for(int i=1;i<=4;i++){%>
            <%=i%>
            <%for(int a=1;a<=8;a++){%>
            <input type="radio" name="seat" value="<%=a %>-<%=i %>">
            <%}%>
            <br/>

            <%}%>
            <br/>

            <input type="hidden" name="date" value="<%=date%>">
            <input type="hidden" name="startTime" value="<%=startTime%>">
            <input type="hidden" name="endTime" value="<%=endTime%>">

            <input type="submit" value="예약"/>
        </form>
    </div>
</body>
</html>
