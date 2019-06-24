package com.github.stormwyrm.javawebsample.register_login_sys.dao;

import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;

public interface IUserDao {
    User findUser(String username, String password);

    User findUser(String username);

    boolean add(User user);
}
