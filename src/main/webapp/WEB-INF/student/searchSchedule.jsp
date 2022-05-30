<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>관리자 페이지 입니다.</title>
    <link href="../../css/schedule.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/studentHeader.jsp"/>
<body>

<div class="schedule-box schedule-pt-12">
    <div class="schedule-in-box-grid">
        <c:forEach var="k" begin="0" end="3">
            <div class="schedule-in-box-sub-grid mb-5">
                <c:choose>
                    <c:when test="${k eq 0}">
                        <div class="schedule-sub-title-2">911</div>
                    </c:when>
                    <c:when test="${k eq 1}">
                        <div class="schedule-sub-title-2">915</div>
                    </c:when>
                    <c:when test="${k eq 2}">
                        <div class="schedule-sub-title-2">916</div>
                    </c:when>
                    <c:when test="${k eq 3}">
                        <div class="schedule-sub-title-2">918</div>
                    </c:when>
                </c:choose>
                <table class="schedule-table-box">
                    <tr class="schedule-table-border">
                        <th class="schedule-table-border schedule-table-size">시간</th>
                        <th class="schedule-table-border schedule-table-size-2">월</th>
                        <th class="schedule-table-border schedule-table-size-2">화</th>
                        <th class="schedule-table-border schedule-table-size-2">수</th>
                        <th class="schedule-table-border schedule-table-size-2">목</th>
                        <th class="schedule-table-border schedule-table-size-2">금</th>
                    </tr>
                    <c:forEach var="j" begin="9" end="16">
                        <%!
                            int palette_cnt = 0;
                        %>
                        <tr class="schedule-table-border">
                            <td class="schedule-table-border schedule-table-size"><c:out value="${j}"/></td>
                            <c:forEach var="i" begin="0" end="4">
                                <c:set var="flag" value="false"/>
                                <c:forEach var="scheduleTime" items="${scheduleTimeList}" varStatus="status">
                                    <%
                                        palette_cnt += 1;
                                        if (palette_cnt == 5) palette_cnt = 0;
                                    %>
                                    <c:if test="${i eq scheduleTime.day and k eq scheduleTime.labNo}">

                                        <c:if test="${j >= scheduleTime.startTime and j<= scheduleTime.endTime}">
                                            <td class="schedule-table-border schedule-table-size-2 schedule-bg-gray-<%=palette_cnt%>">
                                                <c:out value="${scheduleTime.subject}"/></td>
                                            <c:set var="flag" value="true"/>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${not flag}">
                                    <td class="schedule-table-border schedule-table-size-2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </c:if>
                            </c:forEach>

                        </tr>
                    </c:forEach>
                </table>
            </div>

        </c:forEach>
    </div>
</div>
</body>
<jsp:include page="../fragment/footer.jsp"/>
</html>