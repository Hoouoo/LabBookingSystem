<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="deu.team.jsp.notification.entity.Notification" %>
<%@ page import="java.util.List" %>
<%@ page import="deu.team.jsp.notification.NotificationService" %>
<%@ page import="deu.team.jsp.account.domain.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <link href="../../css/lab.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
</head>
<jsp:include page="../fragment/studentHeader.jsp"/>

<c:set var="notificationList" value="${notificationList}"/>
<body>
<%
    int cnt = 0;
    List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");

    Account account = (Account) session.getAttribute("account");
    String accountId = account.getStudentId();

    NotificationService notificationService = (NotificationService) request.getAttribute(
            "notificationService");

    notificationService.setMessageConfirm(accountId);
%>
<div class="lab-box">
    <div class="lab-box-2">
        <div class="lab-title">알림 메시지</div>
        <table id="notificationsheck" style="width:100%">
            <thead class="text-center">
            <tr>
                <th>번호</th>
                <th>학번</th>
                <th>시간</th>
                <th>메시지 내용</th>
                <th>확인 여부</th>
            </tr>
            </thead>
            <tbody class="text-center">

            <%
                for (Notification notification : notifications) {
                    cnt++;
            %>
            <tr>
                <td><%=cnt%>
                </td>
                <td><%=notification.getAccountId()%>
                </td>
                <td><%=notification.getCreatedTime().toString().substring(0, 19)%>
                </td>
                <td><%=notification.getMessage().getContent()%>
                </td>
                <td><%=notification.getIsConfirm() ? "읽음" : "안 읽음"%>
                </td>
            </tr>

            <%}%>
            </tbody>
        </table>
        <script>
          $(document).ready(function () {
            $('#notificationsheck').DataTable();
          });
        </script>
    </div>
</div>


</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>
