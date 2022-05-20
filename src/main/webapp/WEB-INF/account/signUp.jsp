<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link href="../../css/account.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="account-second-box">
    <div class="account-long-title">
        회원가입
    </div>
    <form action="/signUp" method="post">
        <%--        <select name="roleType">--%>
        <%--            <option value="student">학생</option>--%>
        <%--            <option value="professor">교수</option>--%>
        <%--        </select>--%>

        <input id="toggle-on" class="toggle toggle-left" name="roleType" value="student" type="radio" checked>
        <label for="toggle-on" class="account-radio-btn">학생</label>
        <input id="toggle-off" class="toggle toggle-right" name="roleType" value="professor" type="radio">
        <label for="toggle-off" class="account-radio-btn">교수</label>

        <div class="mb-3 mt-3">
            <input class="account-input-box" type="text" name="studentId" placeholder="  학번" required/>
        </div>
        <div class="mb-3">
            <input class="account-input-box" type="text" name="userName" placeholder="  이름" required/>
        </div>
        <div class="mb-3">
            <input class="account-input-box" type="text" name="phoneNo" placeholder="  전화번호" required/>
        </div>
        <div class="mb-3">
            <input class="account-input-box" type="email" name="email" placeholder="  이메일" required/>
        </div>
        <div class="mb-3">
            <input class="account-input-box" type="password" name="userPassword" value=" " placeholder="  비밀번호"
                   required/>
        </div>
        <div class="mb-3">
            <input class="account-input-box" type="text" name="passKey" placeholder="  인증키" required/>
        </div>

        <input type="submit" class="btn btn-secondary btn-block account-button" value="회원가입"/>
    </form>

</div>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>
