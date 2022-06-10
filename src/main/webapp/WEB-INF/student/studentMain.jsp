<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생</title>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<jsp:include page="../fragment/studentHeader.jsp"/>
<body>

<div class="main-m-box">
    <div class="main-background-2"/>
    <div class="main-box">
        <div class="main-in-box-grid">
            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/nowLabStatusPage'">
                    <a>
                        <div class="main-insert-seat"></div>
                        실습실 사용 현황</a>
                    <%--                <button type="button" onClick="location.href='/nowLabStatusPage'">실습실 사용 현황</button>--%>
                </div>

            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/myBookStatusPage'">
                    <a>
                        <div class="main-insert-full-seat"></div>
                        예약 정보 확인</a>
                    <%--                <button type="button" onClick="location.href='/myBookStatusPage'">예약 정보 확인</button>--%>
                </div>

            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/bookPage'">
                    <a>
                        <div class="main-booking"></div>
                        실습실 예약</a>
                    <%--                <button type="button" onClick="location.href='/bookPage'">실습실 예약</button>--%>
                </div>
            </div>


            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/bookExtendPage'">
                    <a>
                        <div class="main-full-booking"></div>
                        실습실 연장</a>
                    <%--                <button type="button" onClick="location.href='/bookExtendPage'">실습실 연장</button>--%>
                </div>
            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/studentAccountModifyPage'">
                    <a>
                        <div class="main-account"></div>
                        회원정보 수정</a>
                    <%--                <button type="button" onClick="location.href='/studentAccountModifyPage'">회원정보 수정</button>--%>

                </div>
            </div>

            <div class="main-in-box-sub-grid">
                <div class="main-in-box" onClick="location.href='/reportPage'">
                    <a>
                        <div class="main-question"></div>
                        신고 및 문의</a>
                    <%--                <button type="button" onClick="location.href='/reportPage'">신고 및 문의</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<%--    <div class="main-in-box-grid">--%>
<%--        <div class="main-in-box-sub-grid">--%>
<%--            <button type="button" onClick="location.href='/bookPage'">실습실 예약</button>--%>
<%--            <button type="button" onClick="location.href='/bookExtendPage'">실습실 연장</button>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--    <br/>--%>

<%--    <div class="main-in-box-grid">--%>
<%--        <div class="main-right-sub-box">--%>

<%--            <button type="button" onClick="location.href='/myBookStatusPage'">예약 정보 확인</button>--%>

<%--            <button type="button" onClick="location.href='/reportPage'">신고 및 문의</button>--%>
<%--        </div>--%>

<%--    </div>--%>
<br/>

<%--    <div class="main-in-box-grid">--%>
<%--    <button type="button" onClick="location.href='/logout'">로그아웃</button>--%>
<%--    <br/>--%>
<%--    </div>--%>
</div>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>
