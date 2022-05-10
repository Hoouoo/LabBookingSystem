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
<%--    <%--%>
<%--        String oneTimeKey = (String) request.getSession().getAttribute("key");--%>
<%--        out.println(oneTimeKey);--%>
<%--    %>--%>
    <form action="/administrator" method="post">
        <input type="submit" value="암호키 생성">
        <c:if test="${null ne sessionScope.key}">
            <c:out value="${sessionScope.key}"/>
        </c:if>

        <br/>
    </form>
<script>
    alert("${sessionScope.keyMsg}");
</script>
</body>
</html>
