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
            <input type="radio" name="scheduleDay" value="scheduleMon"/>
            <input type="radio" name="scheduleDay" value="scheduleTue"/>
            <input type="radio" name="scheduleDay" value="scheduleWed"/>
            <input type="radio" name="scheduleDay" value="scheduleThu"/>
            <input type="radio" name="scheduleDay" value="scheduleFri"/>
            <input type="radio" name="scheduleDay" value="scheduleSat"/>
            <input type="radio" name="scheduleDay" value="scheduleSun"/>
            <br/>
        </label>
        <label>

        </label>
        <button type="submit" name="scheduleCreateBtn">입력</button>
    </form>
    <hr/>
    TEST : <%= request.getAttribute("scheduleCnt") %>
    <c:if test="${0 < scheduleCnt }">
        <c:forEach var="scheduleListView" items="${scheduleList }" varStatus="status">
            <p>${status.count} 과목명 : <c:out value="${scheduleListView}"/></p>
            <br/>
            <hr/>
        </c:forEach>
    </c:if>
</div>
</body>
</html>