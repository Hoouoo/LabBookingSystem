<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원정보 수정</title>
    <link href="../../css/account.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/studentHeader.jsp"/>
<body>
<div class="account-second-box">
    <div class="account-long-title">
        회원정보 수정
    </div>

    <div class="account-mb-2"></div>

    <%--<form action="/accountSearch" method="post">--%>
    <%--    <c:choose>--%>
    <%--        <c:when test="${empty account}">--%>
    <%--            학번 : <input type="text" name="studentId" required/>--%>
    <%--        </c:when>--%>
    <%--        <c:otherwise>--%>
    <%--            학번 : <input type="text" name="studentId" value="${account.studentId}" />--%>
    <%--        </c:otherwise>--%>
    <%--    </c:choose>--%>
    <%--    검색 : <input type="submit" value="검색"/> <br/><br/>--%>
    <%--</form>--%>

    <form action="/studentAccountModify" method="post">
        <c:choose>
            <c:when test="${empty account}">
                <div class="mb-3">
                    <div class="account-sub-title mb-1">이름</div>
                    <input class="account-input-box" type="text" name="userName" placeholder="  이름" required/>
                </div>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">전화번호</div>
                    <input class="account-input-box" type="text" name="phoneNo" placeholder="  전화번호" required/>
                </div>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">이메일</div>
                    <input class="account-input-box" type="email" name="email" placeholder="  이메일" required/>
                </div>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">비밀번호</div>
                    <input class="account-input-box" type="text" name="userPassword" placeholder="  비밀번호" required/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">이름</div>
                    <input class="account-input-box" type="text" name="userName" value="${account.userName}"/>
                </div>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">전화번호</div>
                    <input class="account-input-box" type="text" name="phoneNo" value="${account.phoneNo}"/>
                </div>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">이메일</div>
                    <input class="account-input-box" type="email" name="email" value="${account.email}"/>
                </div>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">비밀번호</div>
                    <input class="account-input-box" type="text" name="userPassword" value="${account.userPassword}"/>
                </div>
            </c:otherwise>
        </c:choose>
        <input type="hidden" name="studentId" value="${account.studentId}">
        <div class="form-group mb-3">
            <input type="submit" class="btn btn-dark btn-block account-button" name="modify" value="수정">
        </div>
    </form>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
