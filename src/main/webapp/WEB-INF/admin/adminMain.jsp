<%--
  Created by IntelliJ IDEA.
  User: Hoouoo
  Date: 2022-05-06
  Time: 오후 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>관리자 페이지 입니다.</title>
</head>
<body>

    <form action="/administrator" method="post">
        <button type="submit" name="keyRole" value="STUDENT">일반 사용자 암호 생성</button>
        <c:if test="${null ne sessionScope.keyStudent}">
            <c:out value="${sessionScope.keyStudent}"/>
        </c:if>
        <button type="submit" name="keyRole" value="PROFESSOR">교수 사용자 암호 생성</button>
        <c:if test="${null ne sessionScope.keyProfessor}">
            <c:out value="${sessionScope.keyProfessor}"/>
        </c:if>
        <c:if test="${null ne sessionScope.keyMsg}">
            <script>
                alert("${sessionScope.keyMsg}");
            </script>
        </c:if>
    </form>

    <
    <br/>


<%--    <%--%>
<%--        String oneTimeKey = (String) request.getSession().getAttribute("key");--%>
<%--        out.println(oneTimeKey);--%>
<%--    %>--%>
<%--    <form action="/administrator" method="post">--%>

<%--        <input type="submit" value="일반 사용자 암호키 생성" onclick="alert(${sessionScope.keyMsg}"/>--%>
<%--        <%--%>
<%--            request.getSession().setAttribute("keyRole", "STUDENT");--%>
<%--        %>--%>
<%--        <c:if test="${null ne sessionScope.key}">--%>
<%--            <c:out value="${sessionScope.key}"/>--%>
<%--        </c:if>--%>
<%--&lt;%&ndash;        <script>&ndash;%&gt;--%>
<%--&lt;%&ndash;            alert("${sessionScope.keyMsg}");&ndash;%&gt;--%>
<%--&lt;%&ndash;        </script>&ndash;%&gt;--%>
<%--        <br/>--%>
<%--    </form>--%>

<%--    <input type="submit" name="keyRole" value="PROFESSOR" onclick="alert(${sessionScope.keyMsg}">--%>
<%--    <form action="/administrator" method="post">--%>


<%--        <c:if test="${null ne sessionScope.key}">--%>
<%--            <c:out value="${sessionScope.key}"/>--%>
<%--        </c:if>--%>
<%--        <%--%>
<%--            request.getSession().setAttribute("keyRole", "PROFESSOR");--%>
<%--        %>--%>
<%--    <br/>--%>
<%--    </form>--%>


</body>
</html>
