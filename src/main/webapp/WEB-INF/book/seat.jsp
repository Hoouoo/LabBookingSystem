<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String date = request.getParameter("date");
    String startTime = request.getParameter("startTime");
    String endTime = request.getParameter("endTime");
    String labNo=request.getParameter("labNo");
%>

<%!
    int labSizeX=5;
    int labSizeY=8;

    int [][] seatList=new int[labSizeX][labSizeY];
%>

<%--
    모델로 넘겨 받은 jstl -> jsp로 변환
--%>
<c:set var="seatList" value="${seats}"/>
<c:set var="returnLabNo" value="${labNo}"/>

<%
    int [][] seatList= (int[][]) pageContext.getAttribute("seatList");
    String returnLabNo= (String) pageContext.getAttribute("returnLabNo");
    for(int i=0;i<labSizeX;i++){
        for(int j=0;j<labSizeY;j++){
            System.out.print(seatList[i][j]);
        }
    }

    labNo=returnLabNo;

%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <div align="center">
        <form action="/book" method="post">
            <h3><%=labNo%></h3>
            <b>칠판</b><br/>
            &nbsp;&nbsp;&nbsp;
            <% for(int a=1;a<=labSizeY;a++){%>
            <small><%=a%></small> &nbsp;
            <%}%>
            <br/>

            <% for(int i=1;i<=labSizeX;i++){%>
                <%=i%>
                <%for(int a=1;a<=labSizeY;a++){
                    if(seatList[i-1][a-1]==1){%>
                <input type="radio" name="seat" value="<%=a %>-<%=i %>" disabled>
                <%}else{%>
                    <input type="radio" name="seat" value="<%=a %>-<%=i %>">
                <%}%>
            <%}%>
            <br/>
            <%}%>
            <br/>

            <input type="hidden" name="date" value="<%=date%>">
            <input type="hidden" name="startTime" value="<%=startTime%>">
            <input type="hidden" name="endTime" value="<%=endTime%>">
            <input type="hidden" name="labNo" value="<%=labNo%>">

            <input type="submit" value="예약"/>
        </form>
    </div>
</body>
</html>
