<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원정보 수정</title>
</head>
<body>
<h3>회원정보 수정</h3> <br/>

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
            이름 : <input type="text" name="userName" required/> <br/>
            전화번호 : <input type="text" name="phoneNo" required/> <br/>
            이메일 : <input type="email" name="email" required/> <br/>
            비밀번호 : <input type="text" name="userPassword" value=" " required/> <br/>
        </c:when>
        <c:otherwise>
            이름 : <input type="text" name="userName" value="${account.userName}"/> <br/>
            전화번호 : <input type="text" name="phoneNo" value="${account.phoneNo}"/> <br/>
            이메일 : <input type="email" name="email" value="${account.email}"/> <br/>
            비밀번호 : <input type="text" name="userPassword" value="${account.userPassword}"/><br/>
        </c:otherwise>
    </c:choose>
    <input type="hidden" name="studentId" value="${account.studentId}">
    <input type="submit" name="modify" value="수정">
</form>
</body>
</html>
