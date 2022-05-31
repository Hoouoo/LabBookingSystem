<%@ page import="deu.team.jsp.account.domain.Account" %>
<%@ page import="deu.team.jsp.account.domain.Role" %>
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
    <link href="../../css/schedule.css" rel="stylesheet" type="text/css">
</head>
<%--<link href="../../../css/schedule.css" rel="stylesheet" type="text/css">--%>
<%
    Account account = (Account) session.getAttribute("account");
    if (account.getRole().equals(Role.ADMIN)) { %>
<c:set var="url" value="/admin/"/>
<c:set var="urlHeader" value="admin"/>
<%
} else if (account.getRole().equals(Role.STUDENT)) {%>
<c:set var="url" value="/"/>
<c:set var="urlHeader" value="student"/>
<%
} else if (account.getRole().equals(Role.PROFESSOR)) {%>
<c:set var="url" value="/prof/"/>
<c:set var="urlHeader" value="prof"/>
<%
    }
%>

<jsp:include page="../fragment/${urlHeader}Header.jsp"/>
<body>
<body>
<div class="schedule-box <c:if test="${urlHeader eq 'admin'}">schedule-pt-14</c:if>">
    <form action="/book" method="post">

        <div class="schedule-title"><%=labNo%>
        </div>
        <div class="schedule-sub-title-3">SCREEN</div>
        <br/>
        &nbsp;&nbsp;&nbsp;
        <% for (int x = 1; x <= labSizeY; x++) {%>
        <small>
            <%--            <%=x%>--%>
        </small> &nbsp;
        <%}%>
        <br/>

        <% for (int y = 1; y <= labSizeX; y++) {%>
        <%--        <%=y%>--%>
        <%
            for (int x = 1; x <= labSizeY; x++) {
                if (seatList[y - 1][x - 1] == 1) {
        %>
        <input type="radio" name="seat" value="<%=x %>-<%=y %>" disabled><label
            class="insert-full-seat"></label></input>
        <%} else {%>
        <input type="radio" name="seat" value="<%=x %>-<%=y %>"><label class="insert-seat"></label></input>
        <%}%>
        <%}%>
        <br/>
        <%}%>
        <br/>
    </form>

    <form method="get" action="${url}nowLabStatusPage">
        <button class="btn btn-block
        <c:choose>
            <c:when test="${labNo eq '915'}">
                btn-primary
            </c:when>
            <c:otherwise>
                btn-secondary
            </c:otherwise>
        </c:choose>" type="submit" name="labNo" value="915">실습실 915
        </button>
        <button class="btn btn-block
        <c:choose>
            <c:when test="${labNo eq '916'}">
                btn-primary
            </c:when>
            <c:otherwise>
                btn-secondary
            </c:otherwise>
        </c:choose>" type="submit" name="labNo" value="916">실습실 916</button>
        <button class="btn btn-block
        <c:choose>
            <c:when test="${labNo eq '918'}">
                btn-primary
            </c:when>
            <c:otherwise>
                btn-secondary
            </c:otherwise>
        </c:choose>" type="submit" name="labNo" value="918">실습실 918</button>
        <button class="btn
        <c:choose>
            <c:when test="${labNo eq '911'}">
                btn-primary
            </c:when>
            <c:otherwise>
                btn-secondary
            </c:otherwise>
        </c:choose>
        btn-block" type="submit" name="labNo" value="911">실습실 911</button>
    </form>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>