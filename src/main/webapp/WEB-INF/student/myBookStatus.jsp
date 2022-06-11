<%@ page import="deu.team.jsp.book.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="myBookList" value="${myBookList}"/>

<%
    int cnt = 0;
    List<Book> bookList = (List<Book>) pageContext.getAttribute("myBookList");
%>

<html>
<head>
    <title>Title</title>
    <link href="../../css/lab.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
</head>

<jsp:include page="../fragment/studentHeader.jsp"/>
<body>
<div class="lab-box">
    <div class="lab-box-2">
        <div class="lab-title">예약 정보 확인</div>
        <table id="mybookcheck" style="width:100%">
            <thead class="text-center">
            <tr>
                <th>번호</th>
                <th>학번</th>
                <th>행</th>
                <th>열</th>
                <th>실습실</th>
                <th>시작 시간</th>
                <th>종료 시간</th>
                <th>예약 취소 / 목록 삭제</th>
            </tr>
            </thead>
            <tbody class="text-center">

            <%
                for (Book mybooks : bookList) {
                    cnt++;
            %>
            <tr>
                <td><%=cnt%>
                </td>
                <td><%=mybooks.getStudentId()%>
                </td>
                <td><%=mybooks.getSeatY()%>
                </td>
                <td><%=mybooks.getSeatX()%>
                </td>
                <td><%=mybooks.getLabNo()%>
                </td>
                <td><%=mybooks.getStartTime()%>
                </td>
                <td><%=mybooks.getEndTime()%>
                </td>
                <td>

                    <form action="/control" method="post">
                        <% if(mybooks.getEndTime().isBefore(LocalDateTime.now())){%>
                        <button type="submit" name="remove" class="btn btn-warning  btn-sm  btn-block lab-button-m"
                                value="<%=mybooks.getId()%>">목록 삭제
                        </button>
                        <%}else{ %>
                        <button type="submit" name="cancel" class="btn btn-danger   btn-sm  btn-block lab-button-m"
                                value="<%=mybooks.getId()%>">예약 취소
                        </button>
                        <%}%>
                    </form>
                </td>
            </tr>

            <%}%>
            </tbody>
        </table>
        <script>
            $(document).ready(function () {
                $('#mybookcheck').DataTable();
            });
        </script>
    </div>
</div>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>