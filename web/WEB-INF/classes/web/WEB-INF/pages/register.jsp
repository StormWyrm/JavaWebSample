<%--
  Created by IntelliJ IDEA.
  User: 李青峰
  Date: 2019/6/20
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body style="text-align: center;">
<form action="${pageContext.request.contextPath}/RegisterControllerServlet" method="post">
    <table width="60%" border="1">
        <tr>
            <td>用户名</td>
            <td>
                <%--使用EL表达式${}提取存储在request对象中的formbean对象中封装的表单数据(formbean.userName)以及错误提示消息(formbean.errors.userName)--%>
                <input type="text" name="username" value="${formbean.userName}">${formbean.errors.userName}
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="password" value="${formbean.userPwd}">${formbean.errors.userPwd}
            </td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td>
                <input type="password" name="confirmPassword" value="${formbean.confirmPwd}">${formbean.errors.confirmPwd}
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" value="${formbean.email}">${formbean.errors.email}
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="清空">
            </td>
            <td>
                <input type="submit" value="注册">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
