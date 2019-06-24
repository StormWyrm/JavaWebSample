package com.github.stormwyrm.javawebsample.register_login_sys.service;

import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;
import com.github.stormwyrm.javawebsample.register_login_sys.exceptioin.UserExistException;

public interface IUserService {
    /**
     * 提供注册服务
     * @param user
     * @throws UserExistException
     */
    boolean registerUser(User user) throws UserExistException;

    /**
     * 提供登录服务
     * @param userName
     * @param userPwd
     * @return
     */
    User loginUser(String userName, String userPwd);
}
