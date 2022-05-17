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
            강 의 실 : &nbsp;
            <select name="labNo">
                <option value="none">=== 선택 ====</option>
                <option value="911">911</option>
                <option value="915">915</option>
                <option value="916">916</option>
                <option value="918">918</option>
            </select>
        </label>
        <label>
            교 과 목 : &nbsp;
            <input type="text" name="subject" required/>

            담당 교수 :
            <input type="text" name="professor" required/><br/>
        </label>
        <label>
            시작 시간 : <input type="time" name="scheduleStartTime"/>
            월&nbsp;&nbsp;화&nbsp;&nbsp;수&nbsp;&nbsp;목&nbsp;&nbsp;금&nbsp;&nbsp;<br/>
            종료 시간 : <input type="time" name="scheduleEndTime"/>
            <input type="radio" name="scheduleDay" value="월" required/>
            <input type="radio" name="scheduleDay" value="화" required/>
            <input type="radio" name="scheduleDay" value="수" required/>
            <input type="radio" name="scheduleDay" value="목" required/>
            <input type="radio" name="scheduleDay" value="금" required/>
            <%--            <input type="radio" name="scheduleDay" value="토" required/>--%>
            <%--            <input type="radio" name="scheduleDay" value="일" required/>--%>
            <br/>
        </label>
        <label>

        </label>
        <button type="submit" name="scheduleCreateBtn">입력</button>
    </form>
    <hr/>
    <%--    시간표 출력 시작--%>
    <c:forEach var="k" begin="0" end="3">
        <c:choose>
            <c:when test="${k eq 0}">911</c:when>
            <c:when test="${k eq 1}">915</c:when>
            <c:when test="${k eq 2}">916</c:when>
            <c:when test="${k eq 3}">918</c:when>
        </c:choose>
        <table border="3">
            <tr>
                <th align="center">시간</th>
                <th align="center">월</th>
                <th align="center">화</th>
                <th align="center">수</th>
                <th align="center">목</th>
                <th align="center">금</th>
            </tr>
            <c:forEach var="j" begin="9" end="16">
                <tr>
                    <td align="center"><c:out value="${j}"/></td>
                    <c:forEach var="i" begin="0" end="4">
                        <c:set var="flag" value="false"/>
                        <c:forEach var="scheduleTime" items="${scheduleTimeList}" varStatus="status">
                            <c:if test="${i eq scheduleTime.day and k eq scheduleTime.labNo}">

                                <c:if test="${j >= scheduleTime.startTime and j<= scheduleTime.endTime}">
                                    <td align="center"><c:out value="${scheduleTime.subject}"/></td>
                                    <c:set var="flag" value="true"/>
                                </c:if>
                            </c:if>
                        </c:forEach>
                        <c:if test="${not flag}">
                            <td align="center">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                        </c:if>
                    </c:forEach>

                </tr>
            </c:forEach>
        </table>
    </c:forEach>
    <%--    시간표 출력 끝--%>


    <hr/>
    <c:if test="${0 < scheduleCnt }">
        등록된 시간표 개수 : <%= request.getAttribute("scheduleCnt")%>개
        <hr/>
        <c:forEach var="schedule" items="${scheduleList}" varStatus="status">
            <p>[${status.count}]번째 시간표</p>
            <p>강의실 : <c:out value="${schedule.labNo}"/></p>
            <p>과목명 : <c:out value="${schedule.subject}"/></p>
            <p>교수님 성함 : <c:out value="${schedule.professor}"/></p>
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