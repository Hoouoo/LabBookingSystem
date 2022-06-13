<%@ page import="deu.team.jsp.account.domain.Account" %>
<%@ page import="deu.team.jsp.account.domain.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Account account = (Account) session.getAttribute("account");
    if (account.getRole().equals(Role.ADMIN)){ %>
<c:set var="url" value="/admin/"/>
<c:set var="urlHeader" value="admin"/>
<%
} else if(account.getRole().equals(Role.PROFESSOR)){%>
<c:set var="url" value="/prof/"/>
<c:set var="urlHeader" value="prof"/>
<%
    }
%>
<html>
<head>
    <meta charset="utf-8">
    <title>관리자 페이지 입니다.</title>
    <link href="../../../css/schedule.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../../fragment/${urlHeader}Header.jsp"/>
<body>

<div class="schedule-box schedule-pt-12">
    <div class="schedule-title">시간표 입력</div>
    <form action="${url}schedule" method="post">

        <div class="schedule-in-box">
            <div class="schedule-sub-title"> 강의실</div>
            <label>
                <select class="form-select form-select-sm schedule-select" name="labNo">
                    <option value="none" selected>=== 선택 ====</option>
                    <option value="911">911</option>
                    <option value="915">915</option>
                    <option value="916">916</option>
                    <option value="918">918</option>
                </select>
            </label>
        </div>
        <br/>
        <label>
            <div class="schedule-in-box-2">
                <div class="schedule-sub-title"> 교과목</div>
                <input class="schedule-input-box" type="text" name="subject" placeholder="  교과목 전체 이름 작성"
                       required/>
            </div>
            <div class="schedule-in-box-2">
                <div class="schedule-sub-title"> 담당 교수</div>
                <input class="schedule-input-box" type="text" name="professor" placeholder="  담당 교수 이름 작성"
                       required/>
            </div>
        </label>
        <br/>
        <div class="schedule-in-box">
            <label>
                <div class="schedule-in-box-2 schedule-mt-m2">
                    <div class="schedule-sub-title"> 시작 시간</div>
                    <input class="schedule-input-box" type="time" name="scheduleStartTime"
                           required/>
                </div>
                <div class="schedule-in-box-2 schedule-mt-m2">
                    <div class="schedule-sub-title"> 종료 시간</div>
                    <input class="schedule-input-box" type="time" name="scheduleEndTime"
                           required/>
                </div>
                <br/>

                <div class="schedule-in-box">
                    <div class="schedule-sub-title"> 요일</div>
                    <label>
                        <select class="form-select form-select-sm schedule-select" name="scheduleDay">
                            <option value="월" selected>월</option>
                            <option value="화">화</option>
                            <option value="수">수</option>
                            <option value="목">목</option>
                            <option value="금">금</option>
                        </select>
                    </label>
                </div>
                <br/>

            </label>

            <button type="submit" class="btn btn-primary btn-block mt-4" name="scheduleCreateBtn"> 입력
            </button>
        </div>
        <hr/>
    </form>
    <hr/>
    <%--    시간표 출력 시작--%>
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
    <%--    시간표 출력 끝--%>

    <c:if test="${0 < scheduleCnt }">
        <h3>등록된 시간표 개수 : <%= request.getAttribute("scheduleCnt")%>개</h3>
        <hr/>
        <div class="schedule-in-box-grid">
            <c:forEach var="schedule" items="${scheduleList}" varStatus="status">

                <div class="schedule-in-box-sub-grid mb-5">
                    <div class="schedule-box-2">
                        <h4>[${status.count}]번째 시간표</h4>
                        <br/>
                        <div class="schedule-box-2-sub-text">
                            <p><b>강의실 :</b> <c:out value="${schedule.labNo}"/></p>
                            <p><b>과목명 :</b> <c:out value="${schedule.subject}"/></p>
                            <p><b>교수님 성함 :</b> <c:out value="${schedule.professor}"/></p>
                            <p><b>수업 요일 :</b> <c:out value="${schedule.day}"/></p>
                            <p><b>수업 시작 시간 :</b> <c:out value="${schedule.startTime}"/></p>
                            <p><b>수업 종료 시간 :</b> <c:out value="${schedule.endTime}"/></p>
                        </div>
                        <br/>
                        <form action="${url}schedule" method="post">
                            <button type="submit" class="btn btn-secondary btn-block schedule-btn-w" name="delete" value="${schedule.id}"> 삭제
                            </button>
                      </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
<%--</div>--%>
</body>

<jsp:include page="../../fragment/footer.jsp"/>
</html>