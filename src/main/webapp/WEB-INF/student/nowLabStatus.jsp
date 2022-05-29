<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String labNo = request.getParameter("labNo");
%>

<%!
    int labSizeX = 5;
    int labSizeY = 8;

    int[][] seatList = new int[labSizeX][labSizeY];
%>

<%--
    모델로 넘겨 받은 jstl -> jsp로 변환
--%>
<c:set var="seatList" value="${seats}"/>
<c:set var="returnLabNo" value="${labNo}"/>

<%
    int[][] seatList = (int[][]) pageContext.getAttribute("seatList");
    String returnLabNo = (String) pageContext.getAttribute("returnLabNo");
    for (int i = 0; i < labSizeX; i++) {
        for (int j = 0; j < labSizeY; j++) {
            System.out.print(seatList[i][j]);
        }
    }

    labNo = returnLabNo;

%>



<html>
<head>
    <title>현재 실습실 사용 현황</title>
    <link href="../../../css/schedule.css" rel="stylesheet" type="text/css">
</head>
<link href="../../../css/schedule.css" rel="stylesheet" type="text/css">

<body>
    <form method="get" action="nowLabStatusPage">
        <button type="submit" name="labNo" value="915">실습실 915</button>
        <button type="submit" name="labNo" value="916">실습실 916</button>
        <button type="submit" name="labNo" value="918">실습실 918</button>
        <button type="submit" name="labNo" value="911">실습실 911</button>
    </form>
    <div class="schedule-box">
        <form action="/book" method="post">

            <div class="schedule-title"><%=labNo%>
            </div>
            <b>칠판</b><br/>
            &nbsp;&nbsp;&nbsp;
            <% for (int x = 1; x <= labSizeY; x++) {%>
            <small><%=x%>
            </small> &nbsp;
            <%}%>
            <br/>

            <% for (int y = 1; y <= labSizeX; y++) {%>
            <%=y%>
            <%
                for (int x = 1; x <= labSizeY; x++) {
                    if (seatList[y - 1][x - 1] == 1) {
            %>
            <input type="radio" name="seat" value="<%=x %>-<%=y %>" disabled>
            <%} else {%>
            <input type="radio" name="seat" value="<%=x %>-<%=y %>">
            <%}%>
            <%}%>
            <br/>
            <%}%>
            <br/>
        </form>
    </div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>