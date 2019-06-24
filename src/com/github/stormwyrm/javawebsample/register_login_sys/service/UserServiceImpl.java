package com.github.stormwyrm.javawebsample.register_login_sys.service;


import com.github.stormwyrm.javawebsample.register_login_sys.dao.IUserDao;
import com.github.stormwyrm.javawebsample.register_login_sys.dao.UserDaoImp;
import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;
import com.github.stormwyrm.javawebsample.register_login_sys.exceptioin.UserExistException;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImp();
    }

    @Override
    public boolean registerUser(User user) throws UserExistException {
        if (userDao.findUser(user.getUsername()) != null) {
            throw new UserExistException();
        } else {
           return userDao.add(user);
        }
    }

    @Override
    public User loginUser(String userName, String userPwd) {
        return userDao.findUser(userName, userPwd);
    }
}
