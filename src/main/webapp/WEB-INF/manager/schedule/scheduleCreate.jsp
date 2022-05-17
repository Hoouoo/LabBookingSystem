<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>관리자 페이지 입니다.</title>
</head>
<body>

<div align="center">
    <h4>시간표 입력</h4>
    <hr/>
    <form action="/admin/schedule" method="post">
        <label>
            교 과 목 : &nbsp;
            <input type="text" name="subject" required/>

            담당 교수 :
            <input type="text" name="professor" required/><br/>
        </label>
        <label>
            시작 시간 : <input type="time" name="scheduleStartTime"/>
            월&nbsp;&nbsp;화&nbsp;&nbsp;수&nbsp;&nbsp;목&nbsp;&nbsp;금&nbsp;&nbsp;토&nbsp;&nbsp;일<br/>
            종료 시간 : <input type="time" name="scheduleEndTime"/>
            <input type="radio" name="scheduleDay" value="Mon"/>
            <input type="radio" name="scheduleDay" value="Tue"/>
            <input type="radio" name="scheduleDay" value="Wed"/>
            <input type="radio" name="scheduleDay" value="Thu"/>
            <input type="radio" name="scheduleDay" value="Fri"/>
            <input type="radio" name="scheduleDay" value="Sat"/>
            <input type="radio" name="scheduleDay" value="Sun"/>
            <br/>
        </label>
        <label>

        </label>
        <button type="submit" name="scheduleCreateBtn">입력</button>
    </form>
    <hr/>
    <table border="3">
        <tr>
            <th align="center">시간</th>
            <th align="center">월</th>
            <th align="center">화</th>
            <th align="center">수</th>
            <th align="center">목</th>
            <th align="center">금</th>
        </tr>
        <% for(int j=9; j<=16; j++){%>
        <tr>
            <td align="center"><%= (j)%></td>
            <% for(int i=0; i<5; i++){%>
            <td align="center"> </td>
            <% }%>
        </tr>
        <%} %>
    </table>

    <hr/>
    <c:if test="${0 < scheduleCnt }">
        등록된 시간표 개수 : <%= request.getAttribute("scheduleCnt") %>개
        <hr/>
        <c:forEach var="schedule" items="${scheduleList}" varStatus="status">
            <p>[${status.count}]번째 시간표</p>
            <p>과목명 : <c:out value="${schedule.subject}"/></p>
            <p>교수님 성함  : <c:out value="${schedule.professor}"/></p>
            <p>수업 요일 : <c:out value="${schedule.day}"/></p>
            <p> 수업 시작 시간 : <c:out value="${schedule.startTime}"/></p>
            <p>수업 종료 시간 : <c:out value="${schedule.endTime}"/></p>
            <br/>
            <hr/>
        </c:forEach>
    </c:if>
</div>
</body>
</html>