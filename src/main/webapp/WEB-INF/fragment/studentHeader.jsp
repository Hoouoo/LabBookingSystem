<%--
  Created by IntelliJ IDEA.
  User: Hoouoo
  Date: 2022-05-06
  Time: 오후 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String[] url = request.getRequestURI().split("/");
    String headerIndex = url[url.length - 1];       // list.jsp 반환
%>

<html>

<head>
    <link href="../../css/header.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>

</head>
<body>
<header class="p-5 d-flex flex-wrap justify-content-center py-2 border-bottom shadow-sm">

    <span class="header-logo d-flex"></span>
    <a href="/studentPage" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <%--        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>--%>
        <span class="fs-4 header-title">Lab Booking System</span>
<%--            <%=headerIndex%>--%>
    </a>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <div class="header-dropdown header-dropdown-margin">
                    <li>
                        <a class="nav-item header-nav-link<%if (headerIndex.equals("bookPage.jsp")||headerIndex.equals("seat.jsp")||headerIndex.equals("bookExtendPage.jsp")) {%> header-nav-active <%}%>"
                           href="/bookPage">실습실 이용</a>
                        <ul>
                            <li><a href="/nowLabStatusPage"
                                   class="nav-link <%if (headerIndex.equals("nowLabStatus.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>"
                                   aria-current="page">실습실 사용 현황</a></li>
                            <li><a href="/bookPage"
                                   class="nav-link <%if (headerIndex.equals("bookPage.jsp")||headerIndex.equals("seat.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>">실습실
                                예약</a></li>
                            <li><a href="/bookExtendPage"
                                   class="nav-link <%if (headerIndex.equals("bookExtendPage.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>">실습실
                                연장</a>
                            </li>
                        </ul>
                    </li>
                </div>

                <a class="nav-item header-nav-link <%if (headerIndex.equals("myBookStatus.jsp")) {%> header-nav-active <%}%>"
                   href="/myBookStatusPage">예약 확인</a>
                <%--                    <a class="nav-item header-nav-link <%if (headerIndex.equals("studentModifyAccount.jsp")) {%> header-nav-active <%}%>"--%>
                <%--                       href="/studentAccountModifyPage">회원정보 관리</a>--%>
                <a class="nav-item header-nav-link <%if (headerIndex.equals("reportPage.jsp")) {%> header-nav-active <%}%>"
                   href="/reportPage">신고 및 문의</a>
                <%--                <a class="nav-item header-nav-link <%if (headerIndex.equals("bookPage.jsp")||headerIndex.equals("seat.jsp")) {%> header-nav-active <%}%>" href="/bookPage">실습실 예약</a>--%>
                <%--                <a class="nav-item header-nav-link <%if (headerIndex.equals("bookExtendPage.jsp")) {%> header-nav-active <%}%>" href="/bookExtendPage">실습실 연장</a>--%>
                <%--                <a class="nav-item header-nav-link <%if (headerIndex.equals("myBookStatus.jsp")) {%> header-nav-active <%}%>" href="/myBookStatusPage">예약 정보 확인</a>--%>
                <%--                <a class="nav-item header-nav-link <%if (headerIndex.equals("reportPage.jsp")) {%> header-nav-active <%}%>" href="/reportPage">신고 및 문의</a>--%>
                <%--                <a class="nav-item header-nav-link <%if (headerIndex.equals("studentModifyAccount.jsp")) {%> header-nav-active <%}%>" href="/studentAccountModifyPage">회원정보 관리</a>--%>
                <%--                <a class="nav-item header-nav-link" href="logout">로그아웃</a>--%>
            </div>
        </div>
    </nav>
    <div class="header-dropdown">
        <li><a class="header-user-icons"></a>
            <ul>
                <li><a href="/studentAccountModifyPage">회원정보 수정</a></li>
                <li><a href="logout">로그아웃</a></li>
            </ul>
        </li>
    </div>
</header>

<c:if test="${null ne AlertMsg911}">
    <nav class="py-2 bg-light border-bottom d-flex shadow-sm">
        <div class="container">
            <ul class="nav justify-content-center header-nav-height-2">
                <c:out value="${AlertMsg911}"/>
            </ul>
        </div>
    </nav>
</c:if>
<c:if test="${null ne AlertMsg915}">
    <nav class="py-2 bg-light border-bottom d-flex shadow-sm">
        <div class="container">
            <ul class="nav justify-content-center header-nav-height-2">
                <c:out value="${AlertMsg915}"/>
            </ul>
        </div>
    </nav>
</c:if>

<c:if test="${null ne AlertMsg916}">
    <nav class="py-2 bg-light border-bottom d-flex shadow-sm">
        <div class="container">
            <ul class="nav justify-content-center header-nav-height-2">
                <c:out value="${AlertMsg916}"/>
            </ul>
        </div>
    </nav>
</c:if>

<c:if test="${null ne AlertMsg918}">
    <nav class="py-2 bg-light border-bottom d-flex shadow-sm">
        <div class="container">
            <ul class="nav justify-content-center header-nav-height-2">
                <c:out value="${AlertMsg918}"/>
            </ul>
        </div>
    </nav>
</c:if>

<%if (headerIndex.equals("bookPage.jsp") || headerIndex.equals("seat.jsp") || headerIndex.equals("bookExtendPage.jsp") || headerIndex.equals("nowLabStatus.jsp") || headerIndex.equals("searchSchedule.jsp")) {%>
<nav class="py-2 bg-light border-bottom d-flex shadow-sm">
    <div class="container">
        <ul class="nav justify-content-center header-nav-height-4 ">
            <li class="nav-item header-nav-height-2"><a href="/searchSchedule" class="nav-link <%if (headerIndex.equals("searchSchedule.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>"
                                                        aria-current="page">실습실 시간표 조회</a></li>
            <li class="nav-item border-end align-self-center header-nav-height-2"/>
            <li class="nav-item header-nav-height-2"><a href="/nowLabStatusPage" class="nav-link <%if (headerIndex.equals("nowLabStatus.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>"
                                                        aria-current="page">실습실 사용 현황</a></li>
            <li class="nav-item border-end align-self-center header-nav-height-2"/>
            <li class="nav-item header-nav-height-2"><a href="/bookPage"
                                                        class="nav-link <%if (headerIndex.equals("bookPage.jsp")||headerIndex.equals("seat.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>">실습실
                예약</a></li>
            <li class="nav-item border-end align-self-center header-nav-height-2"/>
            <li class="nav-item header-nav-height-2"><a href="/bookExtendPage"
                                                        class="nav-link <%if (headerIndex.equals("bookExtendPage.jsp")) {%> header-nav-active <%} else {%>link-dark <%}%>">실습실
                연장</a>
            </li>
        </ul>
    </div>
</nav>
<%}%>

</body>
</html>

