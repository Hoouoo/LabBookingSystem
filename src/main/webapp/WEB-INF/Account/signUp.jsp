<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <form action="/signUp" method="post">
        학번 : <input type="text" name="studentId" required/> <br/>
        이름 : <input type="text" name="userName" required/> <br/>
        비밀번호 : <input type="password" name="userPassword" value=" " required/> <br/>
        인증키 : <input type="text" name="passKey" required/>
        <input type="submit" value="회원가입"/>
    </form>
</body>
</html>
