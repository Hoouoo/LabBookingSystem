<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>관리자</title>
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/adminHeader.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="main-box main-mt-10">

    <form action="/onetimekey" method="post">
        <div class="main-in-box-grid">
            <%--        <div class="main-in-box-sub-grid main-in-box">--%>
            <button class="main-in-box-sub-grid main-in-box main-bg-white" type="submit" name="keyRole" value="STUDENT">

                <div class="main-user-security"></div>
                일반 사용자 암호 생성<br/>
                <div class="main-text-blue">
                    <c:if test="${null ne keyStudent}">
                        <c:out value="${keyStudent}"/>
                    </c:if></div>
            </button>
            <%--        </div>--%>

            <%--        <div class="main-in-box-sub-grid main-in-box">--%>

            <button class="main-in-box-sub-grid main-in-box main-bg-white" type="submit" name="keyRole"
                    value="PROFESSOR">

                <div class="main-admin-security"></div>
                교수 사용자 암호 생성<br/>
                <div class="main-text-blue">
                    <c:if test="${null ne keyProfessor}">
                        <c:out value="${keyProfessor}"/>
                    </c:if></div>
            </button>

            <%--        </div>--%>
            <%--        </form>--%>
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
    </form>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
