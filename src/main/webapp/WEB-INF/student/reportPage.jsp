<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
</head>
<body>
<h3 align="center">신고 및 문의사항</h3>

<form action="/reportPost" method="post" >
    <table style="text-align: center; width: 100% " >
        <tr>
            <td>제목 <input type="text" name="reportTitle"/></td>
        </tr>
        <tr>
            <td>내용 <textarea cols="20" rows="2" name="reportContent"></textarea></td>
        </tr>
    </table>
    <div align="center">
        <input type="submit"value="작성하기"/>
    </div>
</form>
</body>
</html>
</html>

