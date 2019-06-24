<%--
  Created by IntelliJ IDEA.
  User: 李青峰
  Date: 2019/6/19
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>首页</title>
        <script type="text/javascript">
            function logout() {
                window.location.href = "${pageContext.request.contextPath}/LogoutControllerServlet"
            }
        </script>
    </head>
    <body>
        <h1>StormWyrm的个人网站</h1>
        <hr/>
        <c:if test="${user == null}">
            <a href="${pageContext.request.contextPath}/RegisterUIServlet" target="_blank">注册</a>
            <a href="${pageContext.request.contextPath}/LoginUIServlet">登陆</a>
        </c:if>
        <c:if test="${user != null}">
            欢迎：${user.getUsername()}
            <input type="button" value="退出登录" onclick="logout()">
        </c:if>
    </body>
</html>
