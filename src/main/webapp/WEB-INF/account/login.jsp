<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <link href="../../css/account.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/header.jsp"/>

<body class="text-center">

<%--<jsp:include page="../fragment/adminHeader.jsp"/>--%>


<div class="account-box">
    <div class="account-title">
        로그인
    </div>
    <br/>
    <form action="/login" method="post">
        <div class="mb-3">
            <input class="account-input-box" type="text" name="studentId" placeholder="  학번" required/>
        </div>
        <div class="mb-3">
            <input class="account-input-box" type="password" name="userPassword" placeholder="  비밀번호" required><br/>
        </div>

        <div class="form-group mb-3">
            <input type="submit" class="btn btn-primary btn-block account-button" value="로그인">
        </div>
        <button type="button" class="btn btn-secondary btn-block account-button" onClick="location.href='signUpPage'"> 회원가입
        </button>
    </form>
</div>

</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>
