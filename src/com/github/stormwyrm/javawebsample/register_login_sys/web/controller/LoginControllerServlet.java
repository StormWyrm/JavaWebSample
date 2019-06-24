package com.github.stormwyrm.javawebsample.register_login_sys.web.controller;


import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;
import com.github.stormwyrm.javawebsample.register_login_sys.service.IUserService;
import com.github.stormwyrm.javawebsample.register_login_sys.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户登录用户名/密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            String message = String.format("用户名或者密码为空，请重新登录!!! 2秒后自动跳转到登录页面！！<meta http-equiv='refresh' content='2;url=%s'",
                    request.getContextPath() + "/LoginUIServlet");
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
            return;
        }

       IUserService userService = new UserServiceImpl();
        User user = userService.loginUser(username, password);
        if (user == null) {
            String message = String.format(
                    "用户不存在！！请注册后登录！2秒后为您自动跳到注册页面！！<meta http-equiv='refresh' content='2;url=%s'",
                    request.getContextPath() + "/RegisterUIServlet");
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user",user);//保存登录成功消息到session中
        String message = String.format(
                "恭喜：%s,登陆成功！本页将在3秒后跳到首页！！<meta http-equiv='refresh' content='3;url=%s'",
                user.getUsername(),
                request.getContextPath()+"/index.jsp");
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
