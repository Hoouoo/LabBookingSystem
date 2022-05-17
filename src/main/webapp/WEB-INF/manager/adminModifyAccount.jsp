<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원정보 수정</title>
</head>


<jsp:include page="../fragment/adminHeader.jsp"/>

<body>
<h3>회원정보 수정</h3> <br/>

<form action="/accountSearch" method="post">
    <c:choose>
        <c:when test="${empty findAccount}">
            학번 : <input type="text" name="studentId" required/>
        </c:when>
        <c:otherwise>
            학번 : <input type="text" name="studentId" value="${findAccount.studentId}" />
        </c:otherwise>
    </c:choose>
    검색 : <input type="submit" value="검색"/> <br/><br/>
</form>

<form action="/adminAccountModify" method="post">

    <c:choose>
        <c:when test="${empty findAccount}">
            이름 : <input type="text" name="userName" required/> <br/>
            전화번호 : <input type="text" name="phoneNo" required/> <br/>
            이메일 : <input type="email" name="email" required/> <br/>
            비밀번호 : <input type="text" name="userPassword" value=" " required/> <br/>
        </c:when>
        <c:otherwise>
            이름 : <input type="text" name="userName" value="${findAccount.userName}"/> <br/>
            전화번호 : <input type="text" name="phoneNo" value="${findAccount.phoneNo}"/> <br/>
            이메일 : <input type="email" name="email" value="${findAccount.email}"/> <br/>
            비밀번호 : <input type="text" name="userPassword" value="${findAccount.userPassword}"/><br/>
        </c:otherwise>
    </c:choose>
    <input type="hidden" name="studentId" value="${findAccount.studentId}">
    <input type="submit" name="modify" value="수정">
    <input type="submit" name="remove" value="삭제">

</form>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>
