<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>교수</title>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/profHeader.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="main-box main-mt-10">
    <div class="main-in-box-grid">
        <div class="main-in-box-sub-grid">
            <div class="main-in-box" onClick="location.href='/prof/schedule'">
                <a>
                    <div class="main-account"></div>
                    시간표 등록</a>
            </div>
        </div>
        <div class="main-in-box-sub-grid">
            <div class="main-in-box" onClick="location.href='/prof/nowLabStatusPage'">
                <a>
                    <div class="main-insert-seat"></div>
                    좌석 조회</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
