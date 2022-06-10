<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  var msg = "<c:out value='${msg}'/>";
  var url = "<c:out value='${url}'/>";
  alert(msg);
  location.href = url;
</script>