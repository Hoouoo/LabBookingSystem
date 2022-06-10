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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

</head>
<body>
<header class="p-5 d-flex flex-wrap justify-content-center py-2 border-bottom">

    <span class="header-logo"></span>
    <a href="/professorPage" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <%--        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>--%>
        <span class="fs-4 header-title">Lab Booking System</span>
    </a>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="collapse navbar-collapse">
            <div class="navbar-nav">
                <a class="nav-item header-nav-link <%if (headerIndex.equals("scheduleCreate.jsp")) {%> header-nav-active <%}%>"
                   href="/prof/schedule">시간표 등록</a>
                <a class="nav-item header-nav-link <%if (headerIndex.equals("nowLabStatus.jsp")) {%> header-nav-active <%}%>"
                   href="/prof/nowLabStatusPage">좌석 조회</a>
            </div>
        </div>
    </nav>
    <div class="header-dropdown header-z-idx">
        <li><a class="header-user-icons"></a>
            <ul>
                <li><a href="/adminAccountModifyPage">회원정보 수정</a></li>
                <li><a href="/logout">로그아웃</a></li>
            </ul>
        </li>
    </div>

</header>

<%--<form action="/onetimekey" method="post">--%>
<%--    <nav class="py-2 bg-light border-bottom d-flex shadow-sm">--%>
<%--        <div class="container">--%>

<%--            <ul class="nav justify-content-end header-nav-height-4">--%>

<%--                <button class="header-nav-button" type="submit" name="keyRole" value="STUDENT">일반 사용자 암호 생성--%>
<%--                </button>--%>
<%--                <c:if test="${null ne keyStudent}">--%>
<%--                    <div class="header-nav-p me-4">--%>
<%--                        <c:out value="${keyStudent}"/>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <button class="header-nav-button" type="submit" name="keyRole" value="PROFESSOR">교수 사용자 암호 생성</button>--%>
<%--                <c:if test="${null ne keyProfessor}">--%>
<%--                    <div class="header-nav-p">--%>
<%--                        <c:out value="${keyProfessor}"/>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </nav>--%>
<%--</form>--%>

</body>
</html>

