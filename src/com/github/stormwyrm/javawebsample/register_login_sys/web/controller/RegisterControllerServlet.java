package com.github.stormwyrm.javawebsample.register_login_sys.web.controller;


import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;
import com.github.stormwyrm.javawebsample.register_login_sys.exceptioin.UserExistException;
import com.github.stormwyrm.javawebsample.register_login_sys.service.IUserService;
import com.github.stormwyrm.javawebsample.register_login_sys.service.UserServiceImpl;
import com.github.stormwyrm.javawebsample.register_login_sys.web.formbean.RegisterFormBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterControllerServlet")
public class RegisterControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String comfirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");

        RegisterFormBean formBean = new RegisterFormBean();
        formBean.setConfirmPwd(comfirmPassword);
        formBean.setUserName(username);
        formBean.setUserPwd(password);
        formBean.setEmail(email);
        if (!formBean.validate()) {
            request.setAttribute("formbean", formBean);
            //校验失败就说明是用户填写的表单数据有问题，那么就跳转回register.jsp
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        User user = new User(username, password, email);
        IUserService userService = new UserServiceImpl();
        try {
            boolean isSuccess = userService.registerUser(user);
            String message;
            if (isSuccess) {
                message = String.format(
                        "注册成功！2秒后为您自动跳到主页面！！<meta http-equiv='refresh' content='2;url=%s'",
                        request.getContextPath() + "/index.jsp");
                request.getSession().setAttribute("user", user);
            } else {
                message = String.format(
                        "注册失败！2秒后为您自动跳到主页面！！<meta http-equiv='refresh' content='2;url=%s'",
                        request.getContextPath() + "/index.jsp");
            }
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
        } catch (UserExistException e) {
            e.printStackTrace();
            formBean.addUserErrorMsg("用户已存在");
            request.setAttribute("formbean", formBean);
            request.getRequestDispatcher("WEB-INF/pages/message.jsp").forward(request, response);
            return;
        }


        return;
    }
}
