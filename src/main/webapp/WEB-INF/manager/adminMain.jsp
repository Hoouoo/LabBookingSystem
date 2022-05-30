<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>관리자</title>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/adminHeader.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
    <div class="main-box main-mt-10">
        <div class="main-in-box-grid">
            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/admin/schedule'">
                    <a>
                        <div class="main-account"></div>
                        일정 추가</a>
                </div>

            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/announcePage'">
                    <a>
                        <div class="main-question"></div>
                        공지사항 등록</a>
                </div>

            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='#'">
                    <a>
                        <div class="main-insert-seat"></div>
                        사용 현황</a>
                </div>
            </div>


            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/admin/managelab#manage_table'">
                    <a>
                        <div class="main-insert-full-seat"></div>
                        예약 관리</a>
                </div>
            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/admin/managelab#approve_table'">
                    <a>
                        <div class="main-booking"></div>
                        승인 목록</a>
                </div>
            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/admin/managelab#reject_table'">
                    <a>
                        <div class="main-full-booking"></div>
                        거절 목록</a>
                </div>
            </div>
        </div>
    </div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
