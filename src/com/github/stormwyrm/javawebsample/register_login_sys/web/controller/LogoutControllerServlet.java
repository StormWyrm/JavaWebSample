package com.github.stormwyrm.javawebsample.register_login_sys.web.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogoutControllerServlet")
public class LogoutControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //移除存储在session中的user对象，实现注销功能
        request.getSession().removeAttribute("user");

        String message = String.format(
                "注销成功！2秒后为您自动跳到主页面！！<meta http-equiv='refresh' content='2;url=%s'",
                request.getContextPath() + "/index.jsp");
        request.setAttribute("message",message);
        request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
    }
}
