<%@ page import="deu.team.jsp.book.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="myBookList" value="${myBookList}"/>

<%
    int cnt=0;
    List<Book> bookList= (List<Book>) pageContext.getAttribute("myBookList");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <thead>
    <th>번호</th>
    <th>학번</th>
    <th>행</th>
    <th>열</th>
    <th>실습실</th>
    <th>시작 시간</th>
    <th>종료 시간</th>
    <th>삭제/취소</th>
    </thead>
    <%
        for(Book mybooks: bookList){
            cnt++;
    %>
    <tr>
        <td><%=cnt%></td>
        <td><%=mybooks.getStudentId()%></td>
        <td><%=mybooks.getSeatY()%></td>
        <td><%=mybooks.getSeatX()%></td>
        <td><%=mybooks.getLabNo()%></td>
        <td><%=mybooks.getStartTime()%></td>
        <td><%=mybooks.getEndTime()%></td>
        <td>
            <form action="/control" method="post">
                <button type="submit" name="remove" value="<%=mybooks.getId()%>">삭제</button>
                <button type="submit" name="cancel" value="<%=mybooks.getId()%>">취소</button>
            </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>