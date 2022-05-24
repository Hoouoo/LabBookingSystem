<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>신고 및 문의사항</title>
    <link href="../../css/announce.css" rel="stylesheet" type="text/css">
</head>

<jsp:include page="../fragment/studentHeader.jsp"/>
<body>

<div class="announce-box">

    <div class="announce-title">신고 및 문의사항</div>

    <form action="/reportPost" method="post">

        <div class="announce-sub-title"> 제목</div>
        <input type="text" name="reportTitle" class="announce-input-box"/>
        <%--            <tr>--%>
        <%--                <td>제목 <input type="text" name="reportTitle"/></td>--%>
        <%--            </tr>--%>

        <div class="announce-sub-title"> 내용</div>
        <textarea name="reportContent" class="announce-textarea-2"></textarea>
        <%--                <td>내용 <textarea cols="20" rows="2" name="reportContent"></textarea></td>--%>

        <%--        </table>--%>

        <div class="form-group mb-3">
            <input type="submit" class="btn btn-secondary btn-block announce-button" value="작성하기">
        </div>
    </form>
</div>
</body>

<jsp:include page="../fragment/footer.jsp"/>
</html>

