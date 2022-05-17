<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h3>로그인</h3> <br/>
    <form action="/login" method="post">
        학번 : <input type="text" name="studentId" required/><br/>
        비밀번호 : <input type="password" name="userPassword" required><br/>
        <input type="submit" value="로그인"><br/>
        <button type="button"  onClick="location.href='signUpPage'"> 회원가입</button><br/>
    </form>
</body>
</html>
