<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원정보 수정</title>
    <link href="../../css/account.css" rel="stylesheet" type="text/css">
</head>


<jsp:include page="../fragment/adminHeader.jsp"/>

<body>
<div class="account-second-box">
    <div class="account-long-title">
        회원정보 수정</div>
    <br/>
    <div class="account-mb-2"></div>

    <form action="/accountSearch" method="post">
        <c:choose>
            <c:when test="${empty findAccount}">

                <div class="mb-3">
                    <input class="account-input-box" type="text" name="studentId" placeholder="  학번" required/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="mb-3">
                    <input class="account-input-box" type="text" name="studentId" placeholder="  학번"
                           value="${findAccount.studentId}" required/>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="form-group mb-3">
            <input type="submit" class="btn btn-primary btn-block account-button" value="검색">
        </div>
        <hr/>

    </form>

    <form action="/adminAccountModify" method="post">

        <c:choose>
            <c:when test="${empty findAccount}">
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
                    <input class="account-input-box" type="text" name="userPassword" value=" " placeholder="  비밀번호"
                           required/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="mb-3">
                    <div class="account-sub-title mb-1">이름</div>
                    <input class="account-input-box" type="text" name="userName" value="${findAccount.userName}"
                           required/>
                </div>

                <div class="mb-3">
                    <div class="account-sub-title mb-1">전화번호</div>
                    <input class="account-input-box" type="text" name="phoneNo" value="${findAccount.phoneNo}"
                           required/>
                </div>


                <div class="mb-3">
                    <div class="account-sub-title mb-1">이메일</div>
                    <input class="account-input-box" type="email" name="email" value="${findAccount.email}" required/>
                </div>

                <div class="mb-3">
                    <div class="account-sub-title mb-1">비밀번호</div>
                    <input class="account-input-box" type="text" name="userPassword" value="${findAccount.userPassword}"
                           required/>
                </div>

            </c:otherwise>
        </c:choose>
        <input type="hidden" name="studentId" value="${findAccount.studentId}">

        <div class="form-group mb-3">
            <input type="submit" class="btn btn-secondary btn-block account-button" name="modify" value="수정">
        </div>

        <div class="form-group mb-3">
            <input type="submit" class="btn btn-primary btn-block account-button" name="remove" value="삭제">
        </div>

    </form>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
